package fit.tdc.edu.vn.cafemanagement.data.repository.impl

import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.FireBaseAPI
import fit.tdc.edu.vn.cafemanagement.data.extension.DocumentType
import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.ZoneType
import fit.tdc.edu.vn.cafemanagement.data.repository.ZoneTypeRepositoryAPI

class ZoneTypeRepository ( val dataSource: FireBaseAPI):
    ZoneTypeRepositoryAPI {

    override fun getAllZoneTypes() = dataSource.getZoneTypeList("",DocumentType.ALL)

    override fun getZoneType(id: String) = dataSource.getZoneType("",id, DocumentType.SINGLE)


    override fun insert(zoneType: ZoneType) = dataSource.createZoneType("",zoneType)

    override fun update(zoneType: ZoneType) {
        dataSource.modifyZoneType("EfzspceETNgWk56YDOOt", zoneType)
    }

    override fun delete(zoneType: ZoneType) = dataSource.deleteZoneType("", zoneType.id)
}