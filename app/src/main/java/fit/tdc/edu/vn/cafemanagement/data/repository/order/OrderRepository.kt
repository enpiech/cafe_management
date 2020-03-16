package fit.tdc.edu.vn.cafemanagement.data.repository.order

import com.google.firebase.firestore.DocumentReference
import fit.tdc.edu.vn.cafemanagement.data.extension.DocumentLiveData
import fit.tdc.edu.vn.cafemanagement.data.extension.QueryLiveData
import fit.tdc.edu.vn.cafemanagement.data.extension.TaskLiveData
import fit.tdc.edu.vn.cafemanagement.data.model.order.Order

interface OrderRepository {
    fun getAllChefOrders(): QueryLiveData<Order>
    fun getWaiterOrders(paymentId: String): QueryLiveData<Order>
    fun getOrder(orderId: String): DocumentLiveData<Order>
    fun insert(order: Order): TaskLiveData<DocumentReference>
    fun complete(order: Order): TaskLiveData<Void>
}