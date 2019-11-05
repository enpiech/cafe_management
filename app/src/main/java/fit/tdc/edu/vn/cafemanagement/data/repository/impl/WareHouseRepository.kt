package fit.tdc.edu.vn.cafemanagement.data.repository.impl

import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.FireBaseAPI
import fit.tdc.edu.vn.cafemanagement.data.extension.DocumentType
import fit.tdc.edu.vn.cafemanagement.data.extension.TaskLiveData
import fit.tdc.edu.vn.cafemanagement.data.model.ware_house.WareHouse
import fit.tdc.edu.vn.cafemanagement.data.repository.WareHouseRepositoryAPI

class WareHouseRepository (val dataSource: FireBaseAPI) : WareHouseRepositoryAPI {
    override fun getAllWareHouses()= dataSource.getWareHouseList(DocumentType.ALL)

    override fun getWareHouse(id: String) = dataSource.getWareHouse(id, DocumentType.SINGLE)

    override fun insert(wareHouse: WareHouse) = dataSource.createWareHouse(wareHouse)

    override fun update(oldWareHouse: WareHouse, newWareHouse: WareHouse): TaskLiveData<Void> = dataSource.modifyWareHouse(oldWareHouse, newWareHouse)

    override fun delete(wareHouse: WareHouse) = dataSource.deleteWareHouse(wareHouse)
}