package fit.tdc.edu.vn.cafemanagement.data.viewmodel.payment

import androidx.lifecycle.*
import com.hadilq.liveevent.LiveEvent
import fit.tdc.edu.vn.cafemanagement.data.extension.Status
import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.Payment
import fit.tdc.edu.vn.cafemanagement.data.model.material.Material
import fit.tdc.edu.vn.cafemanagement.data.model.order.Order
import fit.tdc.edu.vn.cafemanagement.data.repository.MaterialRepositoryAPI
import fit.tdc.edu.vn.cafemanagement.data.repository.OrderRepositoryAPI
import fit.tdc.edu.vn.cafemanagement.data.repository.PaymentRepositoryAPI

class PaymentListViewModel(
    private val handle: SavedStateHandle,
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

    fun getCurrentPayment(id: String?) {
        _currentPaymentId.value = id
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

    private val _currentPaymentId = LiveEvent<String>()
    private val _currentPayment = MediatorLiveData<Payment>().apply {
        addSource(_currentPaymentId.switchMap {
            getCurrentPayment(it)
        }) {
            value = it.data ?: Payment()
        }
    }

    val currentPayment: LiveData<Payment> = _currentPayment

    private fun getCurrentPayment(id: String) = paymentRepository.get(id)

    private val _currentOrder = MediatorLiveData<List<Order>>().apply {
        addSource(_currentPaymentId.switchMap {
            orderRepository.getWaiterOrders(it)
        }) {
            if (value.isNullOrEmpty()) {
                value = it.data ?: listOf()
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

    fun delete(item: Payment) = paymentRepository.complete(item)
    fun createOrders(tableId: String?) {
        var orders = currentOrder.value ?: listOf()
        if (_currentPaymentId.value.isNullOrBlank()) {
            paymentRepository.insert(
                Payment(
                    tableId = tableId,
                    orderList = saved.values.toList(),
                    state = Payment.State.ORDERED
                )
            )
        } else {
            orders.forEach {
                orderRepository.insert(it.apply {
                    paymentId = _currentPaymentId.value
                })
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
        _currentOrder.value = listOf()
        _currentPayment.value = null
        _currentPaymentId.value = ""
    }
}