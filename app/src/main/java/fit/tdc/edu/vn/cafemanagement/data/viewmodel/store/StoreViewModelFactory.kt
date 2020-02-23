package fit.tdc.edu.vn.cafemanagement.data.viewmodel.store

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.store.StoreRemoteDataSource
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.user.UserRemoteDataSourceImpl
import fit.tdc.edu.vn.cafemanagement.data.repository.store.StoreRepositoryImpl
import fit.tdc.edu.vn.cafemanagement.data.repository.user.UserRepositoryImpl

class StoreViewModelFactory(
    private val dataSource: StoreRemoteDataSource,
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
                storeRepository = StoreRepositoryImpl(dataSource),
                userRepository = UserRepositoryImpl(
                    // LOOSE
                    dataSource = UserRemoteDataSourceImpl()
                )
            ) as T
        } else if (modelClass.isAssignableFrom(StoreListViewModel::class.java)) {
            return StoreListViewModel(
                storeRepository = StoreRepositoryImpl(dataSource)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}