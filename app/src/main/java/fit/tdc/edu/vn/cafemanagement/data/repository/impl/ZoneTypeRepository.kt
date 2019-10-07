package fit.tdc.edu.vn.cafemanagement.data.repository.impl

import fit.tdc.edu.vn.cafemanagement.data.data_source.FireBaseDataSource
import fit.tdc.edu.vn.cafemanagement.data.extension.DocumentType
import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.ZoneType
import fit.tdc.edu.vn.cafemanagement.data.repository.ZoneTypeRepositoryAPI

class ZoneTypeRepository ( val dataSource: FireBaseDataSource):
    ZoneTypeRepositoryAPI {

    override fun getAllZoneTypes() = dataSource.getZoneTypeList("",DocumentType.ALL)

    override fun getZoneType(id: String) = dataSource.getZoneType("",id, DocumentType.SINGLE)


    override fun insert(zoneType: ZoneType) = dataSource.createZoneType("",zoneType)

    override fun update(zoneType: ZoneType) {
        //TODO: get update function
    }

    override fun delete(zoneType: ZoneType) = dataSource.deleteZoneType("", zoneType.id!!)

    override fun deleteAllZoneTypes() {
        //TODO: get delete all function
    }
}