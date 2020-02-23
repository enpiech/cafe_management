package fit.tdc.edu.vn.cafemanagement.data.viewmodel.revenue

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.revenue.RevenueRemoteDataSourceImpl
import fit.tdc.edu.vn.cafemanagement.data.repository.revenue.RevenueRepositoryImpl

class RevenueViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RevenueViewModel::class.java)) {
            return RevenueViewModel(
                revenueRepository = RevenueRepositoryImpl(
                    // LOOSE
                    dataSource = RevenueRemoteDataSourceImpl()
                 )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}