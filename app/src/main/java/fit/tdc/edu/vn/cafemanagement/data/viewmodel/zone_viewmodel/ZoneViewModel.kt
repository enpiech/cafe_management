package fit.tdc.edu.vn.cafemanagement.data.viewmodel.zone_viewmodel

import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.Zone
import fit.tdc.edu.vn.cafemanagement.data.repository.ZoneRepository

class ZoneViewModel (private val zoneRepository: ZoneRepository) {

    private var allZones = zoneRepository.getAllZones()

    fun insert(zone: Zone) {
        zoneRepository.insert(zone)
    }

    fun update(zone: Zone) {
        zoneRepository.update(zone)
    }

    fun delete(zone: Zone) {
        zoneRepository.delete(zone)
    }

    fun deleteAllZones() {
        zoneRepository.deleteAllUnits()
    }

    fun getAllZones() = allZones

}