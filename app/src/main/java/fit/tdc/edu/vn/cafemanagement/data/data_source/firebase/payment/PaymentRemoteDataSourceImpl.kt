package fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.payment

import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import fit.tdc.edu.vn.cafemanagement.data.extension.*
import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.Payment
import fit.tdc.edu.vn.cafemanagement.data.model.order.Order
import fit.tdc.edu.vn.cafemanagement.data.model.table.Table
import fit.tdc.edu.vn.cafemanagement.util.Constants

class PaymentRemoteDataSourceImpl : PaymentRemoteDataSource {
    private val db: FirebaseFirestore by lazy {
        Firebase.firestore
    }

    override fun readPaymentsWithState(
        storeId: String,
        state: Payment.State,
        documentType: DocumentType
    ): QueryLiveData<Payment> =
        db.collection(Constants.STORES_KEY).document(storeId)
            .collection(Constants.PAYMENTS_KEY)
            .whereEqualTo("state", state)
            .asLiveData()

    override fun readPayments(
        storeId: String,
        documentType: DocumentType
    ): CollectionLiveData<Payment> =
        db.collection(Constants.STORES_KEY).document(storeId)
            .collection(Constants.PAYMENTS_KEY)
            .asLiveData()

    override fun readPayment(
        storeId: String,
        paymentId: String,
        documentType: DocumentType
    ): DocumentLiveData<Payment> =
        db.collection(Constants.STORES_KEY).document(storeId)
            .collection(Constants.PAYMENTS_KEY).document(paymentId)
            .asLiveData()


    override fun updatePayment(
        storeId: String,
        orders: List<Order>
    ) {
        val orderCollectionReference =
            db.collection(Constants.STORES_KEY).document(storeId).collection(Constants.ORDERS_KEY)
        orders.forEachIndexed { _, order ->
            orderCollectionReference.add(order)
        }
    }


    override fun checkoutPayment(storeId: String, payment: Payment): TaskLiveData<Void> {
        val paymentRef =
            db.collection(Constants.STORES_KEY).document(storeId).collection(Constants.PAYMENTS_KEY)
                .document(payment.id)
        val tableRef =
            db.collection(Constants.STORES_KEY).document(storeId).collection(Constants.TABLES_KEY)
                .document(payment.tableId!!)
        db.collection(Constants.STORES_KEY).document(storeId).collection(Constants.ORDERS_KEY)
            .whereEqualTo(Constants.PAYMENT_ID_KEY, payment.id)
            .whereGreaterThan(Constants.ORDER_STATE_KEY, Order.State.CLOSE)
            .get()
            .addOnSuccessListener { query ->
                db.runTransaction {
                    query.documents.forEach { doc ->
                        it.update(doc.reference, Constants.ORDER_STATE_KEY, Order.State.CLOSE)
                    }
                }
            }
        return db.runBatch {
            it.update(paymentRef, Constants.PAYMENT_STATE_KEY, Payment.State.PAID)
            it.update(tableRef, Constants.TABLE_STATE_KEY, Table.State.FREE)
            it.update(tableRef, Constants.PAYMENT_ID_KEY, null)
        }.asLiveData()
    }


    override fun createPayment(storeId: String, payment: Payment): TaskLiveData<DocumentReference> {
        return db.collection(Constants.STORES_KEY).document(storeId)
            .collection(Constants.PAYMENTS_KEY)
            .add(payment)
            .addOnSuccessListener { docRef ->
                payment.orderList.forEach { order ->
                    val orderRef = db.collection(Constants.STORES_KEY).document(storeId).collection(
                        Constants.ORDERS_KEY
                    )
                    orderRef.add(order.apply {
                        paymentId = docRef.id
                    })
                }
                val tableRef = db.collection(Constants.STORES_KEY).document(storeId)
                    .collection(Constants.TABLES_KEY).document(payment.tableId!!)
                db.runBatch {
                    it.update(tableRef, Constants.PAYMENT_ID_KEY, docRef.id)
                    it.update(tableRef, Constants.TABLE_STATE_KEY, Table.State.BOOKED)
                }
            }.asLiveData()
    }
}