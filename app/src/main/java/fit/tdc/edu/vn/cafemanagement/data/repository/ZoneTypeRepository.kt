package fit.tdc.edu.vn.cafemanagement.data.repository

import fit.tdc.edu.vn.cafemanagement.data.data_source.FireBaseDataSource
import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.ZoneType

class ZoneTypeRepository ( val dataSource: FireBaseDataSource) {

    fun getAllZoneTypes() = dataSource.getZoneTypeList("")

//    fun getUnitById(id: String) : LiveData<ZoneType?> {
//        return dataSource.getZ("", id)
//    }


    fun insert(zoneType: ZoneType) {
        //TODO: get insert function
    }

    fun update(zoneType: ZoneType) {
        //TODO: get update function
    }

    fun delete(zoneType: ZoneType) {
        //TODO: get delete function
    }

    fun deleteAllZoneTypes() {
        //TODO: get delete all function
    }
}