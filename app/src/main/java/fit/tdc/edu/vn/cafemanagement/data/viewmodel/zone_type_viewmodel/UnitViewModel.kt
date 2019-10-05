package fit.tdc.edu.vn.cafemanagement.data.viewmodel.zone_type_viewmodel

import androidx.lifecycle.LiveData
import fit.tdc.edu.vn.cafemanagement.data.model.Unit
import fit.tdc.edu.vn.cafemanagement.data.model.ZoneType
import fit.tdc.edu.vn.cafemanagement.data.repository.ZoneTypeRepository

class UnitViewModel (private val zoneTypeRepository: ZoneTypeRepository) {

    private var allZoneTypes: LiveData<ArrayList<ZoneType>> = zoneTypeRepository.getAllZoneTypes()

    fun insert(zoneType: ZoneType) {
        zoneTypeRepository.insert(zoneType)
    }

    fun update(zoneType: ZoneType) {
        zoneTypeRepository.update(zoneType)
    }

    fun delete(zoneType: ZoneType) {
        zoneTypeRepository.delete(zoneType)
    }

    fun deleteAllUnits() {
        zoneTypeRepository.deleteAllZoneTypes()
    }

    fun getAllZoneTypes(): LiveData<ArrayList<ZoneType>> {
        return allZoneTypes
    }

}