package fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.order

import com.google.firebase.firestore.DocumentReference
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.FirebaseRemoteDataSource
import fit.tdc.edu.vn.cafemanagement.data.extension.DocumentLiveData
import fit.tdc.edu.vn.cafemanagement.data.extension.DocumentType
import fit.tdc.edu.vn.cafemanagement.data.extension.QueryLiveData
import fit.tdc.edu.vn.cafemanagement.data.extension.TaskLiveData
import fit.tdc.edu.vn.cafemanagement.data.model.order.Order

interface OrderRemoteDataSource : FirebaseRemoteDataSource {
    fun readChefOrders(
        storeId: String,
        documentType: DocumentType
    ): QueryLiveData<Order>

    fun readWaiterOrders(
        storeId: String,
        paymentId: String,
        documentType: DocumentType
    ): QueryLiveData<Order>

    fun readOrder(
        storeId: String,
        orderId: String,
        documentType: DocumentType
    ): DocumentLiveData<Order>

    fun createOrder(
        storeId: String,
        order: Order
    ): TaskLiveData<DocumentReference>

    fun completeOrder(
        storeId: String,
        orderId: String
    ): TaskLiveData<Void>
}