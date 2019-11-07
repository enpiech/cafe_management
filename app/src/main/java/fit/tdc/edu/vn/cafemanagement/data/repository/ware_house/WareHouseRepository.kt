package fit.tdc.edu.vn.cafemanagement.data.repository.ware_house

import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.FireBaseAPI
import fit.tdc.edu.vn.cafemanagement.data.extension.DocumentType
import fit.tdc.edu.vn.cafemanagement.data.model.user.UserInfor
import fit.tdc.edu.vn.cafemanagement.data.model.ware_house.WareHouse

class WareHouseRepository (val dataSource: FireBaseAPI) :
    WareHouseRepositoryAPI {
    override fun update(wareHouse: WareHouse) =
        dataSource.modifyWareHouse(UserInfor.getInstance().storeId!!, wareHouse)

    override fun getAllWareHouses()= dataSource.getWareHouseList(UserInfor.getInstance().storeId!!, DocumentType.ALL)

    override fun getWareHouse(id: String) = dataSource.getWareHouse(UserInfor.getInstance().storeId!!,id, DocumentType.SINGLE)

    override fun insert(wareHouse: WareHouse) = dataSource.createWareHouse(UserInfor.getInstance().storeId!!,wareHouse)

    override fun delete(wareHouse: WareHouse) = dataSource.deleteWareHouse(UserInfor.getInstance().storeId!!,wareHouse.id)
}