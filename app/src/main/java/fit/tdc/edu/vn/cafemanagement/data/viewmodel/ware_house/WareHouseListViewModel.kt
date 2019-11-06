package fit.tdc.edu.vn.cafemanagement.data.viewmodel.ware_house

import fit.tdc.edu.vn.cafemanagement.data.extension.CollectionLiveData
import fit.tdc.edu.vn.cafemanagement.data.extension.TaskLiveData
import fit.tdc.edu.vn.cafemanagement.data.model.ware_house.WareHouse
import fit.tdc.edu.vn.cafemanagement.data.repository.WareHouseRepositoryAPI
import fit.tdc.edu.vn.cafemanagement.fragment.BaseListViewModel

class WareHouseListViewModel (
    private val wareHouseRepository: WareHouseRepositoryAPI
) : BaseListViewModel<WareHouse>() {
    override fun getAllItems()= wareHouseRepository.getAllWareHouses()

    override fun delete(item: WareHouse) = wareHouseRepository.delete(item)
}