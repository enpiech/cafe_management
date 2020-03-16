package fit.tdc.edu.vn.cafemanagement.data.repository.ware_house

import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.warehouse.WareHouseRemoteDataSource
import fit.tdc.edu.vn.cafemanagement.data.extension.DocumentType
import fit.tdc.edu.vn.cafemanagement.data.model.user.UserInfor
import fit.tdc.edu.vn.cafemanagement.data.model.ware_house.WareHouse

class WareHouseRepositoryImpl(
    val dataSource: WareHouseRemoteDataSource
) : WareHouseRepository {
    override fun update(wareHouse: WareHouse) =
        dataSource.updateWareHouse(UserInfor.getInstance().storeId!!, wareHouse)

    override fun getAllWareHouses() =
        dataSource.readWareHouses(UserInfor.getInstance().storeId!!, DocumentType.ALL)

    override fun getWareHouse(id: String) =
        dataSource.readWareHouse(UserInfor.getInstance().storeId!!, id, DocumentType.SINGLE)

    override fun insert(wareHouse: WareHouse) =
        dataSource.createWareHouse(UserInfor.getInstance().storeId!!, wareHouse)

    override fun delete(wareHouse: WareHouse) =
        dataSource.deleteWareHouse(UserInfor.getInstance().storeId!!, wareHouse.id)
}