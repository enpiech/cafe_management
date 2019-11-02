package fit.tdc.edu.vn.cafemanagement.data.viewmodel.user

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.FireBaseAPI
import fit.tdc.edu.vn.cafemanagement.data.repository.impl.StoreRepository
import fit.tdc.edu.vn.cafemanagement.data.repository.impl.UserRepository

class UserViewModelFactory(
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
        if (modelClass.isAssignableFrom(UserDetailViewModel::class.java)) {
            return UserDetailViewModel(
                handle = handle,
                userRepository = UserRepository(
                    dataSource = dataSource
                ),
                storeRepository = StoreRepository(
                    dataSource = dataSource
                )
            ) as T
        } else if (modelClass.isAssignableFrom(UserListViewModel::class.java)) {
            return UserListViewModel(
                userRepository = UserRepository(
                    dataSource = dataSource
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
