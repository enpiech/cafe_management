package fit.tdc.edu.vn.cafemanagement.data.repository.payment

import com.google.firebase.firestore.DocumentReference
import fit.tdc.edu.vn.cafemanagement.data.extension.CollectionLiveData
import fit.tdc.edu.vn.cafemanagement.data.extension.DocumentLiveData
import fit.tdc.edu.vn.cafemanagement.data.extension.QueryLiveData
import fit.tdc.edu.vn.cafemanagement.data.extension.TaskLiveData
import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.Payment
import fit.tdc.edu.vn.cafemanagement.data.model.order.Order

interface PaymentRepository {
    fun getListPaymentByState(state: Payment.State): QueryLiveData<Payment>
    fun getList(): CollectionLiveData<Payment>
    fun get(id: String): DocumentLiveData<Payment>
    fun insert(payment: Payment): TaskLiveData<DocumentReference>
    fun addOrder(orders: List<Order>)
    fun update(oldPayment: Payment, newPayment: Payment): TaskLiveData<Void>
    fun complete(payment: Payment): TaskLiveData<Void>
}