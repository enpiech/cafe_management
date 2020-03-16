package fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.order

import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import fit.tdc.edu.vn.cafemanagement.data.extension.*
import fit.tdc.edu.vn.cafemanagement.data.model.order.Order
import fit.tdc.edu.vn.cafemanagement.util.Constants

class OrderRemoteDataSourceImpl : OrderRemoteDataSource {
    private val db: FirebaseFirestore by lazy {
        Firebase.firestore
    }

    override fun readChefOrders(storeId: String, documentType: DocumentType): QueryLiveData<Order> =
        db.collection(Constants.STORES_KEY).document(storeId)
            .collection(Constants.ORDERS_KEY).whereEqualTo(
                Constants.ORDER_STATE_KEY,
                Order.State.DOING
            )
            .asLiveData()

    override fun readWaiterOrders(
        storeId: String,
        paymentId: String,
        documentType: DocumentType
    ): QueryLiveData<Order> {
        return db.collection(Constants.STORES_KEY).document(storeId)
            .collection(Constants.ORDERS_KEY).whereEqualTo(Constants.PAYMENT_ID_KEY, paymentId)
            .asLiveData()
    }

    override fun readOrder(
        storeId: String,
        orderId: String,
        documentType: DocumentType
    ): DocumentLiveData<Order> {
        return db.collection(Constants.STORES_KEY).document(storeId)
            .collection(Constants.ORDERS_KEY).document(orderId)
            .asLiveData()
    }

    override fun createOrder(
        storeId: String,
        order: Order
    ): TaskLiveData<DocumentReference> {
        val orderCollectionReference =
            db.collection(Constants.STORES_KEY).document(storeId).collection(Constants.ORDERS_KEY)
        return orderCollectionReference.add(order).asLiveData()
    }

    override fun completeOrder(storeId: String, orderId: String): TaskLiveData<Void> =
        db.collection(Constants.STORES_KEY).document(storeId)
            .collection(Constants.ORDERS_KEY).document(orderId)
            .update(Constants.ORDER_STATE_KEY, Order.State.DONE)
            .asLiveData()
}