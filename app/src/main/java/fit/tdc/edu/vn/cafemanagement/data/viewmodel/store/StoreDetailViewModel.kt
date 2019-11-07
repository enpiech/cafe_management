package fit.tdc.edu.vn.cafemanagement.data.viewmodel.store

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.map
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.extension.CombinedLiveData
import fit.tdc.edu.vn.cafemanagement.data.extension.Status
import fit.tdc.edu.vn.cafemanagement.data.extension.TaskLiveData
import fit.tdc.edu.vn.cafemanagement.util.isValidAddress
import fit.tdc.edu.vn.cafemanagement.util.isValidPersonalName
import fit.tdc.edu.vn.cafemanagement.data.model.store.Store
import fit.tdc.edu.vn.cafemanagement.data.model.store.StoreViewFormState
import fit.tdc.edu.vn.cafemanagement.data.model.user.User
import fit.tdc.edu.vn.cafemanagement.data.repository.store.StoreRepositoryAPI
import fit.tdc.edu.vn.cafemanagement.data.repository.user.UserRepositoryAPI
import fit.tdc.edu.vn.cafemanagement.fragment.BaseDetailViewModel

class StoreDetailViewModel(
    private val handle: SavedStateHandle,
    private val storeRepository: StoreRepositoryAPI,
    userRepository: UserRepositoryAPI
) : BaseDetailViewModel<Store>() {

    override var saved: Store
        get() = Store(
            name = handle.get("name"),
            address = handle.get("address"),
            managerId = handle.get("managerId"),
            managerName = handle.get("managerName")
        )
        set(value) {
            handle.set("name", value.name)
            handle.set("address", value.address)
            handle.set("managerId", value.managerId)
            handle.set("managerName", value.managerName)
        }

    private val _userResponseList = userRepository.getAllUsers().map {
        if (it.status == Status.SUCCESS && !it.data.isNullOrEmpty()) {
            it.data
        } else {
            listOf()
        }
    }
    private val _userList = CombinedLiveData<Store, List<User>, List<User>>(
        currentItem,
        _userResponseList
    ) { store, userList ->
        if (!userList.isNullOrEmpty()) {
            var list = userList.filter { user -> user.role == User.Role.STORE_MANAGER }
            list = if (store != null) {
                list.filter { user -> user.storeId.isNullOrBlank() || user.storeId == store.id }
            } else {
                list.filter { user -> user.storeId.isNullOrBlank() }
            }
            list
        } else {
            listOf()
        }
    }
    val userList: LiveData<List<User>> = _userList

    val currentStoreManager =
        CombinedLiveData(
            currentItem.map { it.managerId },
            _userResponseList
        ) { store, userList ->
            getCurrentStoreManager(store, userList)
        }

    private fun getCurrentStoreManager(managerId: String?, userList: List<User>?): User? {
        return if (!userList.isNullOrEmpty() && !managerId.isNullOrBlank()) {
            userList.find { it.id == managerId }
        } else {
            null
        }
    }

    override fun getItem(id: String) = storeRepository.getStore(id)

    override fun insert(item: Store) = storeRepository.createStore(item)

    override fun update(item: Store): TaskLiveData<Void> {
        return storeRepository.updateStore(
            oldStore = currentItem.value!!,
            newStore = item
        )
    }

    override fun validate(item: Store?) {
        when (item) {
            null -> {
                _formState.value = StoreViewFormState().apply {
                    isChanged = false
                    isDataValid = true
                }
                return
            }
            currentItem.value -> {
                _formState.value = StoreViewFormState().apply {
                    isChanged = false
                    isDataValid = false
                }

            }
        }
        // SavedStateHandle
        saved = item!!

        if (_formState.value == null) {
            _formState.value = StoreViewFormState()
        }
        val formState = _formState.value as StoreViewFormState
        var noError = true

        if (item.name != null && !item.name.isValidPersonalName()) {
            formState.nameError = R.string.invalid_user_name
            noError = false
        } else {
            formState.nameError = null
        }

        if (!item.address.isNullOrBlank() && !item.address.isValidAddress()) {
            formState.addressError = R.string.invalid_user_address
            noError = false
        } else {
            formState.addressError = null
        }

        if (currentItem.value != null) {
            when {
                item.name != currentItem.value!!.name -> formState.isChanged = true
                item.address != currentItem.value!!.address -> formState.isChanged = true
                item.managerId != currentItem.value!!.managerId -> formState.isChanged = true
                item.managerName != currentItem.value!!.managerName -> formState.isChanged = true
                else -> formState.isChanged = false
            }
        }

        formState.isDataValid = noError
        _formState.value = formState
    }

}