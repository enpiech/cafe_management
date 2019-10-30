package fit.tdc.edu.vn.cafemanagement.data.viewmodel.store

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.FireBaseDataSource
import fit.tdc.edu.vn.cafemanagement.data.repository.impl.StoreRepository
import fit.tdc.edu.vn.cafemanagement.data.repository.impl.UserRepository

class StoreViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StoreDetailViewModel::class.java)) {
            return StoreDetailViewModel(
                storeRepository = StoreRepository(
                    dataSource = FireBaseDataSource()
                ),
                userRepository = UserRepository(
                    dataSource = FireBaseDataSource()
                )
            ) as T
        } else if (modelClass.isAssignableFrom(StoreListViewModel::class.java)) {
            return StoreListViewModel(
                storeRepository = StoreRepository(
                    dataSource = FireBaseDataSource()
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}