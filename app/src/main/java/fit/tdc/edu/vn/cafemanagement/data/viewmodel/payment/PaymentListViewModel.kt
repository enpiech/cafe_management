package fit.tdc.edu.vn.cafemanagement.data.viewmodel.payment

import android.util.Log
import androidx.lifecycle.*
import com.google.firebase.firestore.DocumentReference
import com.hadilq.liveevent.LiveEvent
import fit.tdc.edu.vn.cafemanagement.data.extension.*
import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.Payment
import fit.tdc.edu.vn.cafemanagement.data.model.material.Material
import fit.tdc.edu.vn.cafemanagement.data.model.order.Order
import fit.tdc.edu.vn.cafemanagement.data.repository.MaterialRepositoryAPI
import fit.tdc.edu.vn.cafemanagement.data.repository.OrderRepositoryAPI
import fit.tdc.edu.vn.cafemanagement.data.repository.PaymentRepositoryAPI
import fit.tdc.edu.vn.cafemanagement.data.repository.TableRepositoryAPI

class PaymentListViewModel(
    private val handle: SavedStateHandle,
    private val tableRepository: TableRepositoryAPI,
    private val paymentRepository: PaymentRepositoryAPI,
    private val orderRepository: OrderRepositoryAPI,
    materialRepository: MaterialRepositoryAPI
) : ViewModel() {
    private fun getOrders(paymentId: String) = orderRepository.getWaiterOrders(paymentId).map {
        if (it.status != Status.SUCCESS) {
            listOf()
        } else {
            it.data
        }
    }

    fun getCurrentTable(id: String?) {
        if (_currentTableId.value.isNullOrBlank()) {
            _currentTableId.value = id
        } else {
            Log.d("test2", _currentTableId.value.toString())
        }
    }

    private var _currentTableId = LiveEvent<String>()
    val currentTable = _currentTableId.switchMap {
        tableRepository.getTable(it)
    }

    fun getCurrentPayment(id: String?) {
        if (_currentPaymentId.value.isNullOrBlank()) {
            _currentPaymentId.value = id
        } else {
            Log.d("test2", _currentPaymentId.value.toString())
        }
    }

    var saved: Map<String, Order>
        get() {
            return handle.get("currentOrder") ?: mapOf()
        }
        set(value) {
            handle.set("currentOrder", value)
        }

    fun updateOrders(item: Order) {
        if (item.amount > 0) {
            val order = saved[item.materialId]
            if (order != null) {
//                _currentPayment.value = _currentPayment.value?.apply {
//                    total += item.amount * item.pricePerUnit
//                }
                order.amount = item.amount
            } else {
                saved = saved + Pair(item.materialId!!, item)
//                _currentPayment.value = _currentPayment.value?.apply {
//                    total += item.amount * item.pricePerUnit
//                }
            }
        } else {
            saved = saved.minus(item.materialId!!)
//            _currentPayment.value = _currentPayment.value?.apply {
//                total -= item.amount * item.pricePerUnit
//            }
        }
        isChanged.value = true
    }

    private val isChanged = MutableLiveData<Boolean>(false)

    private var _currentPaymentId = LiveEvent<String?>()
    private val _currentPayment = MediatorLiveData<Payment?>().apply {
        addSource(_currentPaymentId.switchMap {
            if (it.isNullOrBlank()) {
                MutableLiveData<FirestoreResource<Payment>>(FirestoreResource.success(null))
            } else {
                getCurrentPayment(it)
            }

        }) {
            value = it.data
        }
    }

    //    val currentPayment = _currentPayment
    val currentPayment = _currentPaymentId.switchMap {
        if (it.isNullOrBlank()) {
            MutableLiveData<FirestoreResource<Payment>>(FirestoreResource.error(null))
        } else {
            getCurrentPayment(it)
        }
    }

    private fun getCurrentPayment(id: String) = paymentRepository.get(id)

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

    val currentOrder = _currentOrder

    private val _sellableMaterial = materialRepository.getSellableMaterials()
    val sellableMaterial: LiveData<List<Material>> = _sellableMaterial

    private val _orders = MediatorLiveData<List<Order>>().apply {
        addSource(_sellableMaterial) { materials ->
            val list = materials?.map { material ->
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
            value = list
        }
    }

    val orders: LiveData<List<Order>> = _orders

    val completeOrder = MutableLiveData<TaskResult<DocumentReference>>()

    fun delete(item: Payment) = paymentRepository.complete(item)
    fun createOrders(tableId: String?, paymentId: String?) {
        var orders = currentOrder.value ?: listOf()
        if (paymentId.isNullOrBlank()) {
            paymentRepository.insert(
                Payment(
                    tableId = tableId,
                    orderList = saved.values.toList(),
                    state = Payment.State.ORDERED
                )
            ).observeUntil(Observer {
                if (it.status == TaskStatus.SUCCESS) {
                    completeOrder.value = TaskResult.success(it.data!!)
                }
            }) {
                it?.status != TaskStatus.RUNNING
            }
        } else {
            saved.values.toList().forEach {
                orderRepository.insert(it.apply {
                    this.paymentId = paymentId
                }).observeUntil(Observer {task ->
                    if (task.status == TaskStatus.SUCCESS) {
                        completeOrder.value = TaskResult.success(task.data!!)
                    }
                }) {task ->
                    task?.status != TaskStatus.RUNNING
                }
            }
        }
        orders.forEach {
            if (saved.containsKey(it.materialId)) {
                it.amount += saved[it.materialId]!!.amount
                saved = saved.minus(it.materialId!!)
            }
        }
        orders = orders.plus(saved.values.toList())
        saved = mapOf()
        currentOrder.value = orders
    }

    fun clearSaveState() {
//        handle.remove<String>("currentOrder")
        _currentOrder.value = null
        _currentPayment.value = null
        _currentPaymentId.value = null
    }

    val completeCheckout = MutableLiveData<TaskStatus>()

    fun checkout(payment: Payment) {
        paymentRepository.complete(payment).observeUntil(Observer {
            completeCheckout.value = it.status
        }) {
            it?.status == TaskStatus.SUCCESS
        }
    }
}