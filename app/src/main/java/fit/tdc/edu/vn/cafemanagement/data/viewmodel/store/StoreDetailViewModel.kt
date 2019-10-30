package fit.tdc.edu.vn.cafemanagement.data.viewmodel.store

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.extension.CombinedLiveData
import fit.tdc.edu.vn.cafemanagement.data.extension.Status
import fit.tdc.edu.vn.cafemanagement.data.model.isValidAddress
import fit.tdc.edu.vn.cafemanagement.data.model.isValidName
import fit.tdc.edu.vn.cafemanagement.data.model.store.Store
import fit.tdc.edu.vn.cafemanagement.data.model.store.StoreViewFormState
import fit.tdc.edu.vn.cafemanagement.data.model.user.User
import fit.tdc.edu.vn.cafemanagement.data.repository.StoreRepositoryAPI
import fit.tdc.edu.vn.cafemanagement.data.repository.UserRepositoryAPI
import fit.tdc.edu.vn.cafemanagement.fragment.BaseDetailViewModel

class StoreDetailViewModel(
    private val storeRepository: StoreRepositoryAPI,
    private val userRepository: UserRepositoryAPI
) : BaseDetailViewModel<Store>() {
    private val _userResponseList = userRepository.getAllUsers().map {
        if (it.status == Status.SUCCESS && !it.data.isNullOrEmpty()) {
            it.data.filter { user ->
                user.role == User.Role.STORE_MANAGER
            }
        } else {
            listOf()
        }
    }
    private val _userList = CombinedLiveData<Store, List<User>, List<User>>(
        currentItem,
        _userResponseList
    ) { store, userList ->
        if (!userList.isNullOrEmpty() && store != null) {
            userList.filter { user -> user.storeId.isNullOrBlank() }
        } else {
            listOf()
        }
    }
    val userList: LiveData<List<User>> = _userList

    val currentStoreManager =
        CombinedLiveData<Store, List<User>, User?>(
            currentItem,
            _userResponseList
        ) { store, userList ->
            getCurrentStoreManager(store, userList)
        }

    private fun getCurrentStoreManager(store: Store?, userList: List<User>?): User? {
        return if (!userList.isNullOrEmpty() && store != null) {
            userList.find { it.storeId == store.id }
        } else {
            null
        }
    }

    override fun getItem(id: String) = storeRepository.getStore(id)

    override fun insert(item: Store) = storeRepository.createStore(item)

    override fun update(item: Store) = storeRepository.updateStore(item)

    override fun validate(item: Store?) {
        when (item) {
            null -> {
                _formState.value = StoreViewFormState(
                    nameError = null
                ).apply {
                    isChanged = false
                    isDataValid = true
                }
                return
            }
            currentItem.value -> {
                _formState.value = StoreViewFormState(
                    nameError = null
                ).apply {
                    isChanged = false
                    isDataValid = false
                }

            }
        }
        if (_formState.value == null) {
            _formState.value = StoreViewFormState()
        }
        val formState = _formState.value as StoreViewFormState
        var noError = true

        if (item?.name != null && !item.name.isValidName()) {
            formState.nameError = R.string.invalid_user_name
            noError = false
        } else {
            formState.nameError = null
        }

        if (!item?.address.isNullOrBlank() && !item?.address.isValidAddress()) {
            formState.addressError = R.string.invalid_user_address
            noError = false
        } else {
            formState.addressError = null
        }

        if (currentItem.value != null) {
            when {
                item?.name != currentItem.value!!.name -> formState.isChanged = true
                item?.address != currentItem.value!!.address -> formState.isChanged = true
            }
        }

        formState.isDataValid = noError && formState.isChanged
        _formState.value = formState
    }

}