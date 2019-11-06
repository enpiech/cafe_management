package fit.tdc.edu.vn.cafemanagement.data.repository.impl

import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.FireBaseAPI
import fit.tdc.edu.vn.cafemanagement.data.extension.DocumentLiveData
import fit.tdc.edu.vn.cafemanagement.data.extension.DocumentType
import fit.tdc.edu.vn.cafemanagement.data.extension.QueryLiveData
import fit.tdc.edu.vn.cafemanagement.data.model.order.Order
import fit.tdc.edu.vn.cafemanagement.data.repository.OrderRepositoryAPI

class OrderRepository(val dataSource: FireBaseAPI) : OrderRepositoryAPI {
    override fun getAllChefOrders() = dataSource.getChefOrders("EfzspceETNgWk56YDOOt", DocumentType.ALL)

    override fun getOrder(orderId: String): DocumentLiveData<Order> {
        return dataSource.getOrder(
            storeId = "EfzspceETNgWk56YDOOt",
            orderId = orderId,
            documentType = DocumentType.ALL
        )
    }

    override fun getWaiterOrders(paymentId: String): QueryLiveData<Order> {
        return dataSource.getWaiterOrders(
            storeId = "EfzspceETNgWk56YDOOt",
            paymentId = paymentId,
            documentType = DocumentType.ALL
        )
    }

    override fun insert(order: Order) =
        dataSource.createOrder("EfzspceETNgWk56YDOOt", order)


    override fun complete(order: Order) =
        dataSource.completeOrder("EfzspceETNgWk56YDOOt", order.id)
}