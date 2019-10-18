package fit.tdc.edu.vn.cafemanagement.data.viewmodel.unit_viewmodel

import androidx.lifecycle.*
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.extension.FirestoreResource
import fit.tdc.edu.vn.cafemanagement.data.model.FormState
import fit.tdc.edu.vn.cafemanagement.data.model.isNameValid
import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.Table
import fit.tdc.edu.vn.cafemanagement.data.model.unit.Unit
import fit.tdc.edu.vn.cafemanagement.data.model.unit.UnitViewFormState
import fit.tdc.edu.vn.cafemanagement.data.repository.impl.UnitRepository

class UnitCreateViewModel (
    private val unitRepository: UnitRepository
) : ViewModel() {

    private var _viewState = MutableLiveData<FormState.Type?>(null)
    val viewState: LiveData<FormState.Type?> = _viewState

    private var _formState = MutableLiveData<UnitViewFormState>()
    val formState: LiveData<UnitViewFormState> = _formState

    private var _currentUnitId = MutableLiveData<String>()
    val currentUnit: LiveData<FirestoreResource<Unit>> = _currentUnitId.switchMap {
        unitRepository.getUnit(it)
    }


    fun setViewType(type: FormState.Type) {
        _viewState.value = type
    }

    private var allUnits = unitRepository.getAllUnits()

    private val tables = MediatorLiveData<List<Table>>()

    fun insert(unit: Unit) = unitRepository.insert(unit)

    fun update(unit: Unit) = unitRepository.update(unit)

    fun delete(unit: Unit) = unitRepository.delete(unit)

    fun getAllUnits() = allUnits

    fun getUnit(unitId: String) {
        _currentUnitId.value = unitId
    }

    fun dataChange(unit: Unit?) {
        when {
            !isNameValid(unit?.name) -> _formState.value = UnitViewFormState(nameError = R.string.invalid_unit_name)
            else -> _formState.value = UnitViewFormState().also { it.isDataValid = true }
        }
    }
}

