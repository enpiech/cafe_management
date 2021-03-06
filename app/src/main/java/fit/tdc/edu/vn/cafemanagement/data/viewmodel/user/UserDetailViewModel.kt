package fit.tdc.edu.vn.cafemanagement.data.viewmodel.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.google.firebase.Timestamp
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.extension.CombinedLiveData
import fit.tdc.edu.vn.cafemanagement.data.extension.Status
import fit.tdc.edu.vn.cafemanagement.data.model.store.Store
import fit.tdc.edu.vn.cafemanagement.data.model.user.User
import fit.tdc.edu.vn.cafemanagement.data.model.user.UserViewFormState
import fit.tdc.edu.vn.cafemanagement.data.repository.store.StoreRepository
import fit.tdc.edu.vn.cafemanagement.data.repository.user.UserRepository
import fit.tdc.edu.vn.cafemanagement.fragment.BaseDetailViewModel
import fit.tdc.edu.vn.cafemanagement.util.*

class UserDetailViewModel(
    private val handle: SavedStateHandle,
    private val userRepository: UserRepository,
    storeRepository: StoreRepository
) : BaseDetailViewModel<User>() {
    override var saved: User
        get() = User(
            name = handle.get<String>("name"),
            birth = handle.get<Timestamp>("birth"),
            gender = handle.get<User.Gender>("gender") ?: User.Gender.UNKNOWN,
            identityId = handle.get<String>("identityId"),
            phone = handle.get<String>("phone"),
            address = handle.get<String>("address"),
            role = handle.get<User.Role>("role"),
            storeId = handle.get<String>("storeId"),
            storeName = handle.get<String>("storeName"),
            username = handle.get<String>("username"),
            password = handle.get<String>("password")
        )
        set(value) {
            handle.set("name", value.name)
            handle.set("birth", value.birth)
            handle.set("gender", value.gender)
            handle.set("identityId", value.identityId)
            handle.set("phone", value.phone)
            handle.set("address", value.address)
            handle.set("role", value.role)
            handle.set("storeId", value.storeId)
            handle.set("storeName", value.storeName)
            handle.set("username", value.username)
            handle.set("password", value.password)

            _currentRole.value = value.role
        }

    private val _currentRole = MutableLiveData<User.Role?>(null)

    private val _storeResponseList =
        CombinedLiveData(
            source1 = _currentRole,
            source2 = storeRepository.getStoreList(),
            combine = { role, listResponse ->
                if (listResponse?.status == Status.SUCCESS && !listResponse.data.isNullOrEmpty()) {
                    if (role == User.Role.STORE_MANAGER) {
                        listResponse.data.filter { store -> store.managerId.isNullOrBlank() }
                    } else {
                        listResponse.data
                    }
                } else {
                    listOf()
                }
            }
        )
//    private val _storeResponseList = storeRepository.getStoreList().map {
//        if (it.status == Status.SUCCESS && !it.data.isNullOrEmpty()) {
//            if (saved.role == User.Role.STORE_MANAGER) {
//                it.data.filter { store -> store.managerId.isNullOrBlank() }
//            } else {
//                it.data
//            }
//        } else {
//            listOf()
//        }
//    }

    val storeList: LiveData<List<Store>> = _storeResponseList

    override fun getItem(id: String) = userRepository.getUser(id)

    override fun insert(item: User) = userRepository.insert(item)

    override fun update(item: User) = userRepository.update(
        oldUser = currentItem.value!!,
        newUser = item
    )

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
        // SavedStateHandle
        saved = item!!

        if (_formState.value == null) {
            _formState.value = UserViewFormState()
        }
        val formState = _formState.value as UserViewFormState
        var noError = true
        if (item.username != null && !item.username.isValidUserName()) {
            formState.usernameError = R.string.invalid_user_username
            noError = false
        } else {
            formState.usernameError = null
        }

        if (item.password != null && !item.password.isValidPassword()) {
            formState.passwordError = R.string.invalid_user_password
            noError = false
        } else {
            formState.passwordError = null
        }

        if (item.name != null && !item.name.isValidPersonalName()) {
            formState.nameError = R.string.invalid_user_name
            noError = false
        } else {
            formState.nameError = null
        }

        if (item.birth != null && !item.birth.isValidBirth()) {
            formState.birthError = R.string.invalid_user_birth
            noError = false
        } else {
            formState.birthError = null
        }

        if (!item.identityId.isNullOrBlank() && !item.identityId.isValidIdentityId()) {
            formState.identityIdError = R.string.invalid_user_identityId
            noError = false
        } else {
            formState.identityIdError = null
        }

        if (!item.phone.isNullOrBlank() && !item.phone.isValidPhoneNumber()) {
            formState.phoneError = R.string.invalid_user_phone
            noError = false
        } else {
            formState.phoneError = null
        }

        if (!item.address.isNullOrBlank() && !item.address.isValidAddress()) {
            formState.addressError = R.string.invalid_user_address
            noError = false
        } else {
            formState.addressError = null
        }

        if (currentItem.value != null) {
            when {
                item.username != currentItem.value!!.username -> formState.isChanged = true
                item.password != currentItem.value!!.password -> formState.isChanged = true
                item.name != currentItem.value!!.name -> formState.isChanged = true
                item.birth != currentItem.value!!.birth -> formState.isChanged = true
                item.identityId != currentItem.value!!.identityId -> formState.isChanged = true
                item.phone != currentItem.value!!.phone -> formState.isChanged = true
                item.address != currentItem.value!!.address -> formState.isChanged = true
                item.gender != currentItem.value!!.gender -> formState.isChanged = true
                item.role != currentItem.value!!.role -> formState.isChanged = true
                item.storeId != currentItem.value!!.storeId && item.storeName != currentItem.value!!.storeName -> formState.isChanged =
                    true
            }
        } else {
            formState.isChanged = true
        }

        formState.isDataValid = noError
        _formState.value = formState
    }
}