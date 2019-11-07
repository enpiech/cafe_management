package fit.tdc.edu.vn.cafemanagement.data.viewmodel.payment

import androidx.lifecycle.*
import com.hadilq.liveevent.LiveEvent
import fit.tdc.edu.vn.cafemanagement.data.extension.FirestoreResource
import fit.tdc.edu.vn.cafemanagement.data.extension.Status
import fit.tdc.edu.vn.cafemanagement.data.extension.TaskStatus
import fit.tdc.edu.vn.cafemanagement.data.extension.observeUntil
import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.Payment
import fit.tdc.edu.vn.cafemanagement.data.model.order.Order
import fit.tdc.edu.vn.cafemanagement.data.repository.OrderRepositoryAPI
import fit.tdc.edu.vn.cafemanagement.data.repository.PaymentRepositoryAPI
import fit.tdc.edu.vn.cafemanagement.data.repository.TableRepositoryAPI

class PaymentListViewModel(
    private val tableRepository: TableRepositoryAPI,
    private val paymentRepository: PaymentRepositoryAPI,
    private val orderRepository: OrderRepositoryAPI
) : ViewModel() {

    fun getCurrentTable(id: String?) {
        if (_currentTableId.value.isNullOrBlank()) {
            _currentTableId.value = id
        }
    }

    private var _currentTableId = LiveEvent<String>()
    val currentTable = _currentTableId.switchMap {
        tableRepository.getTable(it)
    }

    fun getCurrentPayment(id: String?) {
        if (_currentPaymentId.value.isNullOrBlank()) {
            _currentPaymentId.value = id
        }
    }

    private var _currentPaymentId = LiveEvent<String?>()

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