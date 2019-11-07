package fit.tdc.edu.vn.cafemanagement.data.viewmodel.chef

import fit.tdc.edu.vn.cafemanagement.data.model.order.Order
import fit.tdc.edu.vn.cafemanagement.data.repository.order.OrderRepository
import fit.tdc.edu.vn.cafemanagement.fragment.BaseListViewModel

class ChefListViewModel(
    private val orderRepository: OrderRepository
) : BaseListViewModel<Order>() {
    override fun getAllItems() = orderRepository.getAllChefOrders()

    override fun delete(item: Order) = orderRepository.complete(item)
}

