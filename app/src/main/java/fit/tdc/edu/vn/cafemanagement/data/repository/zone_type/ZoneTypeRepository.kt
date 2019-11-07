package fit.tdc.edu.vn.cafemanagement.data.repository.zone_type

import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.FireBaseAPI
import fit.tdc.edu.vn.cafemanagement.data.extension.DocumentType
import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.ZoneType
import fit.tdc.edu.vn.cafemanagement.data.model.user.UserInfor

class ZoneTypeRepository ( val dataSource: FireBaseAPI):
    ZoneTypeRepositoryAPI {

    override fun getAllZoneTypes() = dataSource.getZoneTypeList(UserInfor.getInstance().storeId!!,DocumentType.ALL)

    override fun getZoneType(id: String) = dataSource.getZoneType(UserInfor.getInstance().storeId!!,id, DocumentType.SINGLE)


    override fun insert(zoneType: ZoneType) = dataSource.createZoneType(UserInfor.getInstance().storeId!!,zoneType)

    override fun update(zoneType: ZoneType) {
        dataSource.modifyZoneType(UserInfor.getInstance().storeId!!, zoneType)
    }

    override fun delete(zoneType: ZoneType) = dataSource.deleteZoneType(UserInfor.getInstance().storeId!!, zoneType.id)
}