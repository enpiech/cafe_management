package fit.tdc.edu.vn.cafemanagement.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import fit.tdc.edu.vn.cafemanagement.data.repository.UnitRepository

class UnitViewModel (application: Application) : AndroidViewModel(application) {
    private var repository: UnitRepository =
        UnitRepository(application)
    private var allUnits: LiveData<List<fit.tdc.edu.vn.cafemanagement.data.model.Unit.Unit>> = repository.getAllUnits()

    fun insert(unit: fit.tdc.edu.vn.cafemanagement.data.model.Unit.Unit) {
        repository.insert(unit)
    }

    fun update(unit: fit.tdc.edu.vn.cafemanagement.data.model.Unit.Unit) {
        repository.update(unit)
    }

    fun delete(unit: fit.tdc.edu.vn.cafemanagement.data.model.Unit.Unit) {
        repository.delete(unit)
    }

    fun deleteAllUnits() {
        repository.deleteAllUnits()
    }

    fun getAllUnits(): LiveData<List<fit.tdc.edu.vn.cafemanagement.data.model.Unit.Unit>> {
        return allUnits
    }

}