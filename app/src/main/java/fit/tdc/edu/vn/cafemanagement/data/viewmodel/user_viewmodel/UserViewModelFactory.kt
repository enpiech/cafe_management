package fit.tdc.edu.vn.cafemanagement.data.viewmodel.user_viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.FireBaseDataSource
import fit.tdc.edu.vn.cafemanagement.data.repository.impl.UserRepository

class UserViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(
                userRepository = UserRepository(
                    dataSource = FireBaseDataSource()
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}
