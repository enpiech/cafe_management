package fit.tdc.edu.vn.cafemanagement.data.viewmodel.payment

import androidx.lifecycle.*
import fit.tdc.edu.vn.cafemanagement.data.extension.*
import fit.tdc.edu.vn.cafemanagement.data.model.category.Category
import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.Payment
import fit.tdc.edu.vn.cafemanagement.data.model.material.Material
import fit.tdc.edu.vn.cafemanagement.data.model.order.Order
import fit.tdc.edu.vn.cafemanagement.data.repository.category.CategoryRepositoryAPI
import fit.tdc.edu.vn.cafemanagement.data.repository.material.MaterialRepositoryAPI
import fit.tdc.edu.vn.cafemanagement.data.repository.order.OrderRepositoryAPI
import fit.tdc.edu.vn.cafemanagement.data.repository.payment.PaymentRepositoryAPI

class OrderListViewModel(
    private val handle: SavedStateHandle,
    categoryRepository: CategoryRepositoryAPI,
    private val paymentRepository: PaymentRepositoryAPI,
    private val orderRepository: OrderRepositoryAPI,
    materialRepository: MaterialRepositoryAPI
) : ViewModel() {
    var saved: Map<String, Order>
        get() {
            return handle.get("currentOrder") ?: mapOf()
        }
        set(value) {
            handle.set("currentOrder", value)
        }

    private val isChanged = MutableLiveData<Boolean>(false)

    private val _sellableMaterial = materialRepository.getSellableMaterials()
    private var _currentCategoryId = MediatorLiveData<String?>()
    fun filterCategory(categoryId: String) {
        _currentCategoryId.value = categoryId
    }

    private val _orders = CombinedLiveData(
        _currentCategoryId,
        _sellableMaterial
    ) { categoryId: String?, materials: List<Material>? ->
        materials?.filter {
            if (categoryId != null) {
                it.categoryId == categoryId
            } else {
                true
            }
        }?.map { material ->
            saved[material.id]
                ?: Order(
                    materialId = material.id,
                    name = material.name,
                    unitName = material.unitName,
                    amount = 0,
                    price = material.price,
                    unitId = material.unitId,
                    state = Order.State.DOING
                )
        } ?: listOf()
    }
    val orders: LiveData<List<Order>> = _orders

    private val _categories = categoryRepository.getAllCategories()
    val categories: LiveData<FirestoreResource<List<Category>>> = _categories

    fun updateOrders(item: Order) {
        if (item.amount > 0) {
            val order = saved[item.materialId]
            if (order != null) {
                order.amount = item.amount
            } else {
                saved = saved + Pair(item.materialId!!, item)
            }
        } else {
            saved = saved.minus(item.materialId!!)
        }
        isChanged.value = true
    }

    private var _currentPaymentId = MediatorLiveData<String?>()

    private val _currentOrder = MediatorLiveData<List<Order>?>().apply {
        addSource(_currentPaymentId.switchMap {
            if (it.isNullOrBlank()) {
                MutableLiveData<FirestoreResource<List<Order>>>(FirestoreResource.success(null))
            } else {
                orderRepository.getWaiterOrders(it)
            }
        }) {
            if (it.status == Status.SUCCESS) {
                value = it.data
            }
        }
    }

    val completeOrder = MutableLiveData<TaskResult<String>>()

    fun createOrders(tableId: String?, paymentId: String?) {
        var orders = _currentOrder.value ?: listOf()
        if (paymentId.isNullOrBlank()) {
            paymentRepository.insert(
                Payment(
                    tableId = tableId,
                    orderList = saved.values.toList(),
                    state = Payment.State.ORDERED
                )
            ).observeUntil(Observer {
                if (it.status == TaskStatus.SUCCESS) {
                    completeOrder.value = TaskResult.success(it.data!!.id)
                }
            }) {
                it?.status != TaskStatus.RUNNING
            }
        } else {
            saved.values.forEach {
                it.paymentId = paymentId
            }
            paymentRepository.addOrder(saved.values.toList())
            completeOrder.value = TaskResult.success(paymentId)
        }
        orders.forEach {
            if (saved.containsKey(it.materialId)) {
                it.amount += saved[it.materialId]!!.amount
                saved = saved.minus(it.materialId!!)
            }
        }
        orders = orders.plus(saved.values.toList())
        saved = mapOf()
        _currentOrder.value = orders
    }
}