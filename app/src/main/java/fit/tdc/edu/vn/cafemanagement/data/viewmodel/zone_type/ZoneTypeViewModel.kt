package fit.tdc.edu.vn.cafemanagement.data.viewmodel.zone_type

import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.ZoneType
import fit.tdc.edu.vn.cafemanagement.data.repository.zone_type.ZoneTypeRepository

class ZoneTypeViewModel(private val zoneTypeRepository: ZoneTypeRepository) {

    private var allZoneTypes = zoneTypeRepository.getAllZoneTypes()

    fun insert(zoneType: ZoneType) {
        zoneTypeRepository.insert(zoneType)
    }

    fun update(zoneType: ZoneType) {
        zoneTypeRepository.update(zoneType)
    }

    fun delete(zoneType: ZoneType) {
        zoneTypeRepository.delete(zoneType)
    }

    fun deleteAllZoneTypes() {
        zoneTypeRepository.getAllZoneTypes()
    }

    fun getAllZoneTypes() = allZoneTypes
}