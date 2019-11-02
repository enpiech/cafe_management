package fit.tdc.edu.vn.cafemanagement.data.repository.impl

import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.FireBaseAPI
import fit.tdc.edu.vn.cafemanagement.data.extension.DocumentType
import fit.tdc.edu.vn.cafemanagement.data.model.store.Store
import fit.tdc.edu.vn.cafemanagement.data.repository.StoreRepositoryAPI

class StoreRepository(val dataSource: FireBaseAPI) : StoreRepositoryAPI{
    override fun getStoreList() = dataSource.getStoreList(DocumentType.ALL)

    override fun getStore(storeID: String) = dataSource.getStore(storeID,DocumentType.SINGLE)

    override fun createStore(store: Store) = dataSource.createStore(store)

    override fun updateStore(store: Store) = dataSource.modifyStore(store)

    override fun deleteStore(storeID: String) = dataSource.deleteStore(storeID)
}