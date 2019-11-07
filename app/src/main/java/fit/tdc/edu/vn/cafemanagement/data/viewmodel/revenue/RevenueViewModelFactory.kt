package fit.tdc.edu.vn.cafemanagement.data.viewmodel.revenue

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.FireBaseDataSource
import fit.tdc.edu.vn.cafemanagement.data.repository.revenue.RevenueRepository

class RevenueViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RevenueViewModel::class.java)) {
            return RevenueViewModel(
                 revenueRepository = RevenueRepository(
                     dataSource = FireBaseDataSource()
                 )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}