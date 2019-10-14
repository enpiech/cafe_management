package fit.tdc.edu.vn.cafemanagement.data.viewmodel.unit_viewmodel

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.Unit
import fit.tdc.edu.vn.cafemanagement.data.repository.UnitRepositoryAPI
import fit.tdc.edu.vn.cafemanagement.data.repository.impl.UnitRepository

class UnitViewModel (
    private val unitRepository: UnitRepositoryAPI): ViewModel() {

    fun insert(unit: Unit) {
        unitRepository.insert(unit)
    }

    fun update(unit: Unit) {
        unitRepository.update(unit)
    }

    fun delete(unit: Unit) {
        unitRepository.delete(unit)
    }

    fun getAllUnits() = unitRepository.getAllUnits()

}