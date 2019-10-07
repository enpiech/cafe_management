package fit.tdc.edu.vn.cafemanagement.data.repository.impl

import fit.tdc.edu.vn.cafemanagement.data.data_source.FireBaseDataSource
import fit.tdc.edu.vn.cafemanagement.data.extension.DocumentType
import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.Zone
import fit.tdc.edu.vn.cafemanagement.data.repository.ZoneRepositoryAPI

class ZoneRepository ( val dataSource: FireBaseDataSource):
    ZoneRepositoryAPI {

    override fun getAllZones() = dataSource.getZoneList("EfzspceETNgWk56YDOOt",DocumentType.ALL)

    override fun getZone(id: String) = dataSource.getZone("EfzspceETNgWk56YDOOt", id,DocumentType.SINGLE)


    override fun insert(zone: Zone) =
        dataSource.createZone("EfzspceETNgWk56YDOOt", zone)

    override fun update(zone: Zone) {
        //TODO: get update function
    }

    override fun delete(zone: Zone) =
        dataSource.deleteZone("EfzspceETNgWk56YDOOt", zone.id!!)

    override fun deleteAllZones() {
        //TODO: get delete all function
    }
}