package fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.store

import com.google.firebase.firestore.DocumentReference
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.FirebaseRemoteDataSource
import fit.tdc.edu.vn.cafemanagement.data.extension.CollectionLiveData
import fit.tdc.edu.vn.cafemanagement.data.extension.DocumentLiveData
import fit.tdc.edu.vn.cafemanagement.data.extension.DocumentType
import fit.tdc.edu.vn.cafemanagement.data.extension.TaskLiveData
import fit.tdc.edu.vn.cafemanagement.data.model.store.Store

interface StoreRemoteDataSource : FirebaseRemoteDataSource {
    fun readStores(
        documentType: DocumentType
    ): CollectionLiveData<Store>

    fun readStore(
        storeId: String,
        documentType: DocumentType
    ): DocumentLiveData<Store>

    fun createStore(
        store: Store
    ): TaskLiveData<DocumentReference>

    fun updateStore(
        oldStore: Store, newStore: Store
    ): TaskLiveData<Void>

    fun deleteStore(
        storeId: String
    ): TaskLiveData<Void>
}