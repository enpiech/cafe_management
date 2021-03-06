package fit.tdc.edu.vn.cafemanagement.data.viewmodel.revenue

import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.Revenue
import fit.tdc.edu.vn.cafemanagement.data.repository.revenue.RevenueRepository

class RevenueViewModel(
    private val revenueRepository: RevenueRepository
) {
    private var allRevenues = revenueRepository.getAllRevenues()

    fun getRevenue(id: String) = revenueRepository.getRevenue(id)

    fun insert(revenue: Revenue) {
        revenueRepository.insert(revenue)
    }

    fun update(revenue: Revenue) {
        revenueRepository.update(revenue)
    }

    fun delete(revenue: Revenue) {
        revenueRepository.delete(revenue)
    }

    fun deleteAllRevenues() {
        revenueRepository.deleteAllRevenues()
    }

    fun getAllRevenues() = allRevenues
}