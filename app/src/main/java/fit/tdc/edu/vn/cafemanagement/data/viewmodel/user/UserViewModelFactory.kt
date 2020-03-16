package fit.tdc.edu.vn.cafemanagement.data.viewmodel.user

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.store.StoreRemoteDataSourceImpl
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.user.UserRemoteDataSource
import fit.tdc.edu.vn.cafemanagement.data.repository.store.StoreRepositoryImpl
import fit.tdc.edu.vn.cafemanagement.data.repository.user.UserRepositoryImpl

class UserViewModelFactory(
    private val dataSource: UserRemoteDataSource,
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        if (modelClass.isAssignableFrom(UserDetailViewModel::class.java)) {
            return UserDetailViewModel(
                handle = handle,
                userRepository = UserRepositoryImpl(dataSource),
                storeRepository = StoreRepositoryImpl(
                    // LOOSE
                    dataSource = StoreRemoteDataSourceImpl()
                )
            ) as T
        } else if (modelClass.isAssignableFrom(UserListViewModel::class.java)) {
            return UserListViewModel(
                userRepository = UserRepositoryImpl(dataSource)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
