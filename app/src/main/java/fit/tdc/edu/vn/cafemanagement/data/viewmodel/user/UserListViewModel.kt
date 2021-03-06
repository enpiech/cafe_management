package fit.tdc.edu.vn.cafemanagement.data.viewmodel.user

import fit.tdc.edu.vn.cafemanagement.data.model.user.User
import fit.tdc.edu.vn.cafemanagement.data.repository.user.UserRepository
import fit.tdc.edu.vn.cafemanagement.fragment.BaseListViewModel

class UserListViewModel(
    private val userRepository: UserRepository
) : BaseListViewModel<User>() {

    override fun getAllItems() = userRepository.getAllUsers()

    override fun delete(item: User) = userRepository.delete(item)
}