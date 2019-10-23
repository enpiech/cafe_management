package fit.tdc.edu.vn.cafemanagement.data.viewmodel.user_viewmodel

import fit.tdc.edu.vn.cafemanagement.data.model.user.User
import fit.tdc.edu.vn.cafemanagement.data.repository.UserRepositoryAPI
import fit.tdc.edu.vn.cafemanagement.fragment.BaseListViewModel

class UserListViewModel(
    private val userRepository: UserRepositoryAPI
): BaseListViewModel<User>() {

    override fun getAllItems() = userRepository.getAllUsers()

    override fun delete(item: User) = userRepository.delete(item)
}