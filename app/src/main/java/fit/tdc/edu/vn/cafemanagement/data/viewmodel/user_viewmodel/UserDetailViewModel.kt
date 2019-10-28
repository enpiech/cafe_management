package fit.tdc.edu.vn.cafemanagement.data.viewmodel.user_viewmodel

import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.model.isNameValid
import fit.tdc.edu.vn.cafemanagement.data.model.user.User
import fit.tdc.edu.vn.cafemanagement.data.model.user.UserViewFormState
import fit.tdc.edu.vn.cafemanagement.data.repository.UserRepositoryAPI
import fit.tdc.edu.vn.cafemanagement.fragment.BaseDetailViewModel

class UserDetailViewModel (
    private val userRepository: UserRepositoryAPI
): BaseDetailViewModel<User>() {

    override fun getItem(id: String) = userRepository.getUser(id)

    override fun insert(item: User) = userRepository.insert(item)

    override fun update(item: User) = userRepository.update(item)

    override fun validate(item: User?) {
        when {
            item == null -> {
                _formState.value = UserViewFormState(
                    nameError = null
                ).apply {
                    isChanged = false
                    isDataValid = true
                }
            }
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