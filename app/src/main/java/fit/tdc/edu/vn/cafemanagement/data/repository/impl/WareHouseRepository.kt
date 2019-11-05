package fit.tdc.edu.vn.cafemanagement.data.repository.impl

import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.FireBaseAPI
import fit.tdc.edu.vn.cafemanagement.data.extension.DocumentType
import fit.tdc.edu.vn.cafemanagement.data.extension.TaskLiveData
import fit.tdc.edu.vn.cafemanagement.data.model.ware_house.WareHouse
import fit.tdc.edu.vn.cafemanagement.data.repository.WareHouseRepositoryAPI

class WareHouseRepository (val dataSource: FireBaseAPI) : WareHouseRepositoryAPI {
    override fun update(wareHouse: WareHouse) =
        dataSource.modifyWareHouse("EfzspceETNgWk56YDOOt", wareHouse)

    override fun getAllWareHouses()= dataSource.getWareHouseList("EfzspceETNgWk56YDOOt", DocumentType.ALL)

    override fun getWareHouse(id: String) = dataSource.getWareHouse("EfzspceETNgWk56YDOOt",id, DocumentType.SINGLE)

    override fun insert(wareHouse: WareHouse) = dataSource.createWareHouse("EfzspceETNgWk56YDOOt",wareHouse)

    override fun delete(wareHouse: WareHouse) = dataSource.deleteWareHouse("EfzspceETNgWk56YDOOt",wareHouse.id)
}