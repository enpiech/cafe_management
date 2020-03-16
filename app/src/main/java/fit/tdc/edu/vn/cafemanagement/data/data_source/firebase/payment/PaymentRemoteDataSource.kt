package fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.payment

import com.google.firebase.firestore.DocumentReference
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.FirebaseRemoteDataSource
import fit.tdc.edu.vn.cafemanagement.data.extension.*
import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.Payment
import fit.tdc.edu.vn.cafemanagement.data.model.order.Order

interface PaymentRemoteDataSource : FirebaseRemoteDataSource {
    fun readPaymentsWithState(
        storeId: String,
        state: Payment.State,
        documentType: DocumentType
    ): QueryLiveData<Payment>

    fun readPayments(
        storeId: String,
        documentType: DocumentType
    ): CollectionLiveData<Payment>

    fun readPayment(
        storeId: String,
        paymentId: String,
        documentType: DocumentType
    ): DocumentLiveData<Payment>

    fun createPayment(
        storeId: String,
        payment: Payment
    ): TaskLiveData<DocumentReference>

    fun checkoutPayment(
        storeId: String,
        payment: Payment
    ): TaskLiveData<Void>

    fun updatePayment(
        storeId: String,
        orders: List<Order>
    )
}