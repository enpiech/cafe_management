package fit.tdc.edu.vn.cafemanagement.data.repository

import fit.tdc.edu.vn.cafemanagement.data.data_source.FireBaseDataSource
import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.Zone

class ZoneRepository ( val dataSource: FireBaseDataSource) {

    fun getAllZones() = dataSource.getZoneList("EfzspceETNgWk56YDOOt")

    fun getUnitById(id: String) = dataSource.getZone("EfzspceETNgWk56YDOOt", id)


    fun insert(zone: Zone) {
        dataSource.createZone("EfzspceETNgWk56YDOOt", zone)
    }

    fun update(zone: Zone) {
        //TODO: get update function
    }

    fun delete(zone: Zone) {
        dataSource.deleteZone("EfzspceETNgWk56YDOOt", zone.id!!)
    }

    fun deleteAllUnits() {
        //TODO: get delete all function
    }
}