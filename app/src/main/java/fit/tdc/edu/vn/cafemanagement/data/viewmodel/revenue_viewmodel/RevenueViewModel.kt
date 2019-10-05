package fit.tdc.edu.vn.cafemanagement.data.viewmodel.revenue_viewmodel

import androidx.lifecycle.LiveData
import fit.tdc.edu.vn.cafemanagement.data.model.Revenue
import fit.tdc.edu.vn.cafemanagement.data.model.Zone
import fit.tdc.edu.vn.cafemanagement.data.repository.RevenueRepository
import fit.tdc.edu.vn.cafemanagement.data.repository.ZoneRepository

class RevenueViewModel (private val revenueRepository: RevenueRepository) {

    private var allRevenues: LiveData<ArrayList<Revenue>> = revenueRepository.getAllRevenues()

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

    fun getAllRevenues(): LiveData<ArrayList<Revenue>> {
        return allRevenues
    }

}