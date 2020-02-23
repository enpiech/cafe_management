package fit.tdc.edu.vn.cafemanagement.data.repository.zone_type

import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.zonetype.ZoneTypeRemoteDataSource
import fit.tdc.edu.vn.cafemanagement.data.extension.DocumentType
import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.ZoneType
import fit.tdc.edu.vn.cafemanagement.data.model.user.UserInfor

class ZoneTypeRepositoryImpl(
    val dataSource: ZoneTypeRemoteDataSource
) : ZoneTypeRepository {

    override fun getAllZoneTypes() =
        dataSource.readZoneTypes(UserInfor.getInstance().storeId!!, DocumentType.ALL)

    override fun getZoneType(id: String) =
        dataSource.readZoneType(UserInfor.getInstance().storeId!!, id, DocumentType.SINGLE)


    override fun insert(zoneType: ZoneType) =
        dataSource.createZoneType(UserInfor.getInstance().storeId!!, zoneType)

    override fun update(zoneType: ZoneType) {
        dataSource.updateZoneType(UserInfor.getInstance().storeId!!, zoneType)
    }

    override fun delete(zoneType: ZoneType) =
        dataSource.deleteZoneType(UserInfor.getInstance().storeId!!, zoneType.id)
}