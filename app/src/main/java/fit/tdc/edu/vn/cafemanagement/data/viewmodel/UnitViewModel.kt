package fit.tdc.edu.vn.cafemanagement.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.Unit
import fit.tdc.edu.vn.cafemanagement.data.repository.impl.UnitRepository

class UnitViewModel (application: Application) : AndroidViewModel(application) {
    private var repository = UnitRepository()
    private var allUnits = repository.getAllUnits()

    fun insert(unit: Unit) {
        repository.insert(unit)
    }

    fun update(unit: Unit) {
        repository.update(unit)
    }

    fun delete(unit: Unit) {
        repository.delete(unit)
    }

    fun deleteAllUnits() {
        repository.deleteAllUnits()
    }

    fun getAllUnits() = allUnits

}