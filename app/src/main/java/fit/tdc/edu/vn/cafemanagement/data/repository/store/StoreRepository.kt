package fit.tdc.edu.vn.cafemanagement.data.repository.store

import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.FireBaseAPI
import fit.tdc.edu.vn.cafemanagement.data.extension.DocumentType
import fit.tdc.edu.vn.cafemanagement.data.model.store.Store

class StoreRepository(val dataSource: FireBaseAPI) :
    StoreRepositoryAPI {
    override fun getStoreList() = dataSource.getStoreList(DocumentType.ALL)

    override fun getStore(storeID: String) = dataSource.getStore(storeID,DocumentType.SINGLE)

    override fun createStore(store: Store) = dataSource.createStore(store)

    override fun updateStore(oldStore: Store, newStore: Store) = dataSource.modifyStore(oldStore, newStore)

    override fun deleteStore(storeID: String) = dataSource.deleteStore(storeID)
}