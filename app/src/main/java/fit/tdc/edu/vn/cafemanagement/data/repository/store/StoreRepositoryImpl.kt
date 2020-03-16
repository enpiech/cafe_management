package fit.tdc.edu.vn.cafemanagement.data.repository.store

import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.store.StoreRemoteDataSource
import fit.tdc.edu.vn.cafemanagement.data.extension.DocumentType
import fit.tdc.edu.vn.cafemanagement.data.model.store.Store

class StoreRepositoryImpl(
    val dataSource: StoreRemoteDataSource
) :
    StoreRepository {
    override fun getStoreList() = dataSource.readStores(DocumentType.ALL)

    override fun getStore(storeID: String) = dataSource.readStore(storeID, DocumentType.SINGLE)

    override fun createStore(store: Store) = dataSource.createStore(store)

    override fun updateStore(oldStore: Store, newStore: Store) =
        dataSource.updateStore(oldStore, newStore)

    override fun deleteStore(storeID: String) = dataSource.deleteStore(storeID)
}