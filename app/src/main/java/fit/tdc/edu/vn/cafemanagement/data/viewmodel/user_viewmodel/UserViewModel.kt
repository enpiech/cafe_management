package fit.tdc.edu.vn.cafemanagement.data.viewmodel.user_viewmodel

import fit.tdc.edu.vn.cafemanagement.data.model.user.User
import fit.tdc.edu.vn.cafemanagement.data.repository.UserRepsitoryAPI

class UserViewModel (private val userRepository: UserRepsitoryAPI) {

    private var allUsers = userRepository.getAllUsers()

    fun insert(user: User) {
        userRepository.insert(user)
    }

    fun update(user: User) {
        userRepository.update(user)
    }

    fun delete(user: User) {
        userRepository.delete(user)
    }

    fun getAllUsers() = allUsers
}