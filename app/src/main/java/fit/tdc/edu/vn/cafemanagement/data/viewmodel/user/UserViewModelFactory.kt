package fit.tdc.edu.vn.cafemanagement.data.viewmodel.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.FireBaseDataSource
import fit.tdc.edu.vn.cafemanagement.data.repository.impl.StoreRepository
import fit.tdc.edu.vn.cafemanagement.data.repository.impl.UserRepository

class UserViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserDetailViewModel::class.java)) {
            return UserDetailViewModel(
                userRepository = UserRepository(
                    dataSource = FireBaseDataSource()
                ),
                storeRepository = StoreRepository(
                    dataSource = FireBaseDataSource()
                )
            ) as T
        } else if (modelClass.isAssignableFrom(UserListViewModel::class.java)) {
            return UserListViewModel(
                userRepository = UserRepository(
                    dataSource = FireBaseDataSource()
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}
