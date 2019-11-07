package fit.tdc.edu.vn.cafemanagement.data.repository.order

import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.FireBaseAPI
import fit.tdc.edu.vn.cafemanagement.data.extension.DocumentLiveData
import fit.tdc.edu.vn.cafemanagement.data.extension.DocumentType
import fit.tdc.edu.vn.cafemanagement.data.extension.QueryLiveData
import fit.tdc.edu.vn.cafemanagement.data.model.order.Order
import fit.tdc.edu.vn.cafemanagement.data.model.user.UserInfor

class OrderRepository(val dataSource: FireBaseAPI) :
    OrderRepositoryAPI {
    override fun getAllChefOrders() = dataSource.getChefOrders(UserInfor.getInstance().storeId!!, DocumentType.ALL)

    override fun getOrder(orderId: String): DocumentLiveData<Order> {
        return dataSource.getOrder(
            storeId = UserInfor.getInstance().storeId!!,
            orderId = orderId,
            documentType = DocumentType.ALL
        )
    }

    override fun getWaiterOrders(paymentId: String): QueryLiveData<Order> {
        return dataSource.getWaiterOrders(
            storeId = UserInfor.getInstance().storeId!!,
            paymentId = paymentId,
            documentType = DocumentType.ALL
        )
    }

    override fun insert(order: Order) =
        dataSource.createOrder(UserInfor.getInstance().storeId!!, order)


    override fun complete(order: Order) =
        dataSource.completeOrder(UserInfor.getInstance().storeId!!, order.id)
}