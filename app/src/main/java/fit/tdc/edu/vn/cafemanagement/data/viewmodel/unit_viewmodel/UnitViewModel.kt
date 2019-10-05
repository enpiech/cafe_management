package fit.tdc.edu.vn.cafemanagement.data.viewmodel.unit_viewmodel

import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.Unit
import fit.tdc.edu.vn.cafemanagement.data.repository.UnitRepository

class UnitViewModel (private val unitRepository: UnitRepository) {

    private var allUnits = unitRepository.getAllUnits()

    fun insert(unit: Unit) {
        unitRepository.insert(unit)
    }

    fun update(unit: Unit) {
        unitRepository.update(unit)
    }

    fun delete(unit: Unit) {
        unitRepository.delete(unit)
    }

    fun deleteAllUnits() {
        unitRepository.deleteAllUnits()
    }

    fun getAllUnits() = allUnits

}