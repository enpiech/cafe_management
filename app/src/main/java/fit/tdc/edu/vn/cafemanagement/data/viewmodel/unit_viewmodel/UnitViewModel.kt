package fit.tdc.edu.vn.cafemanagement.data.viewmodel.unit_viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import fit.tdc.edu.vn.cafemanagement.data.model.Unit
import fit.tdc.edu.vn.cafemanagement.data.repository.UnitRepository

class UnitViewModel (private val unitRepository: UnitRepository) {

    private var allUnits: LiveData<ArrayList<Unit>> = unitRepository.getAllUnits()

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

    fun getAllUnits(): LiveData<ArrayList<Unit>> {
        return allUnits
    }

}