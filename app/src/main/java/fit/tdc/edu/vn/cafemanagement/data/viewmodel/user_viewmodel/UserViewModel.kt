package fit.tdc.edu.vn.cafemanagement.data.viewmodel.user_viewmodel

import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.model.isNameValid
import fit.tdc.edu.vn.cafemanagement.data.model.user.User
import fit.tdc.edu.vn.cafemanagement.data.model.user.UserViewFormState
import fit.tdc.edu.vn.cafemanagement.data.repository.UserRepositoryAPI
import fit.tdc.edu.vn.cafemanagement.fragment.BaseViewViewModel

class UserViewModel (
    private val userRepository: UserRepositoryAPI
): BaseViewViewModel<User>() {
    override fun getAllItems() = userRepository.getAllUsers()

    override fun getItem(id: String) = userRepository.getUser(id)

    override fun insert(item: User) = userRepository.insert(item)

    override fun update(item: User) = userRepository.update(item)

    override fun delete(item: User) = userRepository.delete(item)

    override fun dataChange(item: User) {
        when {
            item == currentItem.value -> {
                _formState.value = UserViewFormState(
                    nameError = null
                ).apply {
                    isChanged = false
                    isDataValid = true
                }
            }
            !isNameValid(item.name) -> {
                _formState.value = UserViewFormState(
                    nameError = R.string.invalid_user_name
                ).apply {
                    isChanged = false
                    isDataValid = false
                }
            }
            isNameValid(item.name) && item != currentItem.value -> {
                _formState.value = UserViewFormState(
                    nameError = null
                ).apply {
                    isChanged = true
                    isDataValid = true
                }
            }
        }
    }
}