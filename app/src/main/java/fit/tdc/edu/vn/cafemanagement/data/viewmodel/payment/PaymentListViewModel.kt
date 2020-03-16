package fit.tdc.edu.vn.cafemanagement.data.viewmodel.payment

import androidx.lifecycle.*
import fit.tdc.edu.vn.cafemanagement.data.extension.FirestoreResource
import fit.tdc.edu.vn.cafemanagement.data.extension.Status
import fit.tdc.edu.vn.cafemanagement.data.extension.TaskStatus
import fit.tdc.edu.vn.cafemanagement.data.extension.observeUntil
import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.Payment
import fit.tdc.edu.vn.cafemanagement.data.model.order.Order
import fit.tdc.edu.vn.cafemanagement.data.repository.order.OrderRepository
import fit.tdc.edu.vn.cafemanagement.data.repository.payment.PaymentRepository
import fit.tdc.edu.vn.cafemanagement.data.repository.table.TableRepository

class PaymentListViewModel(
    private val tableRepository: TableRepository,
    private val paymentRepository: PaymentRepository,
    private val orderRepository: OrderRepository
) : ViewModel() {

    fun getCurrentTable(id: String?) {
        if (_currentTableId.value.isNullOrBlank()) {
            _currentTableId.value = id
        }
    }

    private var _currentTableId = MediatorLiveData<String>()
    val currentTable = _currentTableId.switchMap {
        tableRepository.getTable(it)
    }

    fun getCurrentPayment(id: String?) {
        if (_currentPaymentId.value.isNullOrBlank()) {
            _currentPaymentId.value = id
        }
    }

    private var _currentPaymentId = MediatorLiveData<String?>()

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

    fun delete(item: Payment) = paymentRepository.complete(item)

    val completeCheckout = MutableLiveData<TaskStatus>()

    fun checkout(payment: Payment) {
        paymentRepository.complete(payment).observeUntil(Observer {
            completeCheckout.value = it.status
        }) {
            it?.status == TaskStatus.SUCCESS
        }
    }
}