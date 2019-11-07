package fit.tdc.edu.vn.cafemanagement.data.viewmodel.store

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.FireBaseAPI
import fit.tdc.edu.vn.cafemanagement.data.repository.store.StoreRepository
import fit.tdc.edu.vn.cafemanagement.data.repository.user.UserRepository

//class StoreViewModelFactory : ViewModelProvider.Factory {
//
//    @Suppress("UNCHECKED_CAST")
//    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(StoreDetailViewModel::class.java)) {
//            return StoreDetailViewModel(
//                storeRepository = StoreRepository(
//                    dataSource = FireBaseDataSource()
//                ),
//                userRepository = UserRepository(
//                    dataSource = FireBaseDataSource()
//                )
//            ) as T
//        } else if (modelClass.isAssignableFrom(StoreListViewModel::class.java)) {
//            return StoreListViewModel(
//                storeRepository = StoreRepository(
//                    dataSource = FireBaseDataSource()
//                )
//            ) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}

class StoreViewModelFactory(
    private val dataSource: FireBaseAPI,
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        if (modelClass.isAssignableFrom(StoreDetailViewModel::class.java)) {
            return StoreDetailViewModel(
                handle = handle,
                storeRepository = StoreRepository(
                    dataSource = dataSource
                ),
                userRepository = UserRepository(
                    dataSource = dataSource
                )
            ) as T
        } else if (modelClass.isAssignableFrom(StoreListViewModel::class.java)) {
            return StoreListViewModel(
                storeRepository = StoreRepository(
                    dataSource = dataSource
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}