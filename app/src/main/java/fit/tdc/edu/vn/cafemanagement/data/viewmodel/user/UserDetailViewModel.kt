package fit.tdc.edu.vn.cafemanagement.data.viewmodel.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.extension.CombinedLiveData
import fit.tdc.edu.vn.cafemanagement.data.extension.Status
import fit.tdc.edu.vn.cafemanagement.data.model.*
import fit.tdc.edu.vn.cafemanagement.data.model.store.Store
import fit.tdc.edu.vn.cafemanagement.data.model.user.User
import fit.tdc.edu.vn.cafemanagement.data.model.user.UserViewFormState
import fit.tdc.edu.vn.cafemanagement.data.repository.StoreRepositoryAPI
import fit.tdc.edu.vn.cafemanagement.data.repository.UserRepositoryAPI
import fit.tdc.edu.vn.cafemanagement.fragment.BaseDetailViewModel

class UserDetailViewModel(
    private val userRepository: UserRepositoryAPI,
    private val storeRepository: StoreRepositoryAPI
) : BaseDetailViewModel<User>() {

    private val _storeResponseList = storeRepository.getStoreList()
    val storeList: LiveData<List<Store>> = _storeResponseList.map {
        if (it.status == Status.SUCCESS && !it.data.isNullOrEmpty()) {
            it.data
        } else {
            listOf()
        }
    }

    val currentStore =
        CombinedLiveData<User, List<Store>, Store?>(currentItem, storeList) { user, storeList ->
            getCurrentStoreOfUser(user, storeList)
        }

    private fun getCurrentStoreOfUser(user: User?, res: List<Store>?): Store? {
        return if (!res.isNullOrEmpty() && user != null) {
            res.find { store -> store.id == user.storeId }
        } else
            null
    }

    override fun getItem(id: String) = userRepository.getUser(id)

    override fun insert(item: User) = userRepository.insert(item)

    override fun update(item: User) = userRepository.update(item)

    override fun validate(item: User?) {
        when (item) {
            null -> {
                _formState.value = UserViewFormState(
                    nameError = null
                ).apply {
                    isChanged = false
                    isDataValid = true
                }
                return
            }
            currentItem.value -> {
                _formState.value = UserViewFormState(
                    nameError = null
                ).apply {
                    isChanged = false
                    isDataValid = false
                }

            }
        }
        if (_formState.value == null) {
            _formState.value = UserViewFormState()
        }
        val formState = _formState.value as UserViewFormState
        var noError = true
        if (item?.username != null && !item.username.isValidUserName()) {
            formState.usernameError = R.string.invalid_user_username
            noError = false
        } else {
            formState.usernameError = null
        }

        if (item?.password != null && !item.password.isValidPassword()) {
            formState.passwordError = R.string.invalid_user_password
            noError = false
        } else {
            formState.passwordError = null
        }

        if (item?.name != null && !item.name.isValidName()) {
            formState.nameError = R.string.invalid_user_name
            noError = false
        } else {
            formState.nameError = null
        }

        if (item?.birth != null && !item.birth.isValidBirth()) {
            formState.birthError = R.string.invalid_user_birth
            noError = false
        } else {
            formState.birthError = null
        }

        if (!item?.identityId.isNullOrBlank() && !item?.identityId.isValidIdentityId()) {
            formState.identityIdError = R.string.invalid_user_identityId
            noError = false
        } else {
            formState.identityIdError = null
        }

        if (!item?.phone.isNullOrBlank() && !item?.phone.isValidPhoneNumber()) {
            formState.phoneError = R.string.invalid_user_phone
            noError = false
        } else {
            formState.phoneError = null
        }

        if (!item?.address.isNullOrBlank() && !item?.address.isValidAddress()) {
            formState.addressError = R.string.invalid_user_address
            noError = false
        } else {
            formState.addressError = null
        }

        if (currentItem.value != null) {
            when {
                item?.username != currentItem.value!!.username -> formState.isChanged = true
                item?.password != currentItem.value!!.password -> formState.isChanged = true
                item?.name != currentItem.value!!.name -> formState.isChanged = true
                item?.birth != currentItem.value!!.birth -> formState.isChanged = true
                item?.identityId != currentItem.value!!.identityId -> formState.isChanged = true
                item?.phone != currentItem.value!!.phone -> formState.isChanged = true
                item?.address != currentItem.value!!.address -> formState.isChanged = true
                item?.gender != currentItem.value!!.gender -> formState.isChanged = true
                item.role != currentItem.value!!.role -> formState.isChanged = true
                item.storeId != currentItem.value!!.storeId -> formState.isChanged = true
            }
        } else {
            formState.isChanged = true
        }

        formState.isDataValid = noError && formState.isChanged
        _formState.value = formState
    }
}