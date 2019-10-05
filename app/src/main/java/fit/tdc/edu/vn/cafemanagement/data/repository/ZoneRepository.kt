package fit.tdc.edu.vn.cafemanagement.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import fit.tdc.edu.vn.cafemanagement.data.data_source.FireBaseDataSource
import fit.tdc.edu.vn.cafemanagement.data.model.Zone

class ZoneRepository ( val dataSource: FireBaseDataSource) {

    fun getAllZones() : LiveData<ArrayList<Zone>> {
        return dataSource.getZoneMap().map { map: HashMap<String, Zone> ->
            ArrayList<Zone>(map.values)
        }
    }

    fun getUnitById(id: String) : LiveData<Zone?> {
        return dataSource.getZoneMap().map { map: HashMap<String, Zone> ->
            map[id] = Zone.builder().build()
            map[id]
        }
    }


    fun insert(zone: Zone) {
        //TODO: get insert function
    }

    fun update(zone: Zone) {
        //TODO: get update function
    }

    fun delete(zone: Zone) {
        //TODO: get delete function
    }

    fun deleteAllUnits() {
        //TODO: get delete all function
    }
}