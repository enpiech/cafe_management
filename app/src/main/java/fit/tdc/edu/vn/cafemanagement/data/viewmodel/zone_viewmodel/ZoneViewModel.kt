package fit.tdc.edu.vn.cafemanagement.data.viewmodel.zone_viewmodel

import androidx.lifecycle.LiveData
import fit.tdc.edu.vn.cafemanagement.data.model.Zone
import fit.tdc.edu.vn.cafemanagement.data.repository.ZoneRepository

class ZoneViewModel (private val zoneRepository: ZoneRepository) {

    private var allZones: LiveData<ArrayList<Zone>> = zoneRepository.getAllZones()

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

    fun getAllZones(): LiveData<ArrayList<Zone>> {
        return allZones
    }

}