package fit.tdc.edu.vn.cafemanagement.ui.unit_create

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.Unit
import fit.tdc.edu.vn.cafemanagement.data.repository.UnitRepositoryAPI

class UnitCreateViewModel (
    private val unitRepository: UnitRepositoryAPI): ViewModel() {
    private val _unit = MutableLiveData<Unit>()
    var unit: LiveData<Unit?> = _unit

    fun insert(unit: Unit) {
        unitRepository.insert(unit)
    }

    fun update(unit: Unit) {
        unitRepository.update(unit)
    }

    fun getUnit(id: String) {
        unit = unitRepository.getUnit(id).map {
            it.data
        }
    }
}

