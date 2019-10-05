package fit.tdc.edu.vn.cafemanagement.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import fit.tdc.edu.vn.cafemanagement.data.data_source.FireBaseDataSource
import fit.tdc.edu.vn.cafemanagement.data.model.Zone

class ZoneRepository ( val dataSource: FireBaseDataSource) {

    fun getAllZones() : LiveData<ArrayList<Zone>> {
        return dataSource.getZoneList("EfzspceETNgWk56YDOOt")
    }

    fun getUnitById(id: String) : LiveData<Zone?> {
        return dataSource.getZone("EfzspceETNgWk56YDOOt", id)
    }


    fun insert(zone: Zone) {
        dataSource.createZone("EfzspceETNgWk56YDOOt", zone)
    }

    fun update(zone: Zone) {
        //TODO: get update function
    }

    fun delete(zone: Zone) {
        dataSource.deleteZone("EfzspceETNgWk56YDOOt", zone.id)
    }

    fun deleteAllUnits() {
        //TODO: get delete all function
    }
}