package fit.tdc.edu.vn.cafemanagement.data.viewmodel.store

import fit.tdc.edu.vn.cafemanagement.data.model.store.Store
import fit.tdc.edu.vn.cafemanagement.data.repository.store.StoreRepositoryAPI
import fit.tdc.edu.vn.cafemanagement.fragment.BaseListViewModel

class StoreListViewModel(
    private val storeRepository: StoreRepositoryAPI
) : BaseListViewModel<Store>() {

    override fun getAllItems() = storeRepository.getStoreList()

    override fun delete(item: Store) = storeRepository.deleteStore(item.id)
}