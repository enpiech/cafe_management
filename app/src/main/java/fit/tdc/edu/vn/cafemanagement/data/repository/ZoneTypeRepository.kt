package fit.tdc.edu.vn.cafemanagement.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import fit.tdc.edu.vn.cafemanagement.data.data_source.FireBaseDataSource
import fit.tdc.edu.vn.cafemanagement.data.model.ZoneType

class ZoneTypeRepository ( val dataSource: FireBaseDataSource) {

    fun getAllZoneTypes() : LiveData<ArrayList<ZoneType>> {
        return dataSource.getZoneTypeMap().map { map: HashMap<String, ZoneType> ->
            ArrayList<ZoneType>(map.values)
        }
    }

    fun getUnitById(id: String) : LiveData<ZoneType?> {
        return dataSource.getZoneTypeMap().map { map: HashMap<String, ZoneType> ->
            map[id] = ZoneType.builder().build()
            map[id]
        }
    }


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