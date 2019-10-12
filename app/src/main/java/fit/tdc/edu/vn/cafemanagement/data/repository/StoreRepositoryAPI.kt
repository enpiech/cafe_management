package fit.tdc.edu.vn.cafemanagement.data.repository

import com.google.firebase.firestore.DocumentReference
import fit.tdc.edu.vn.cafemanagement.data.extension.CollectionLiveData
import fit.tdc.edu.vn.cafemanagement.data.extension.DocumentLiveData
import fit.tdc.edu.vn.cafemanagement.data.extension.TaskLiveData
import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.Store

interface StoreRepositoryAPI {
    fun getStoreList() : CollectionLiveData<Store>

    fun getStore(storeID: String) : DocumentLiveData<Store>

    fun createStore(store: Store) : TaskLiveData<DocumentReference>

    fun updateStore(store: Store) : TaskLiveData<Void>

    fun deleteStore(storeID: String) : TaskLiveData<Void>
}