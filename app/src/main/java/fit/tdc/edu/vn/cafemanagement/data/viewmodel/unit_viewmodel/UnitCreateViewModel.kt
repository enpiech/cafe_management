package fit.tdc.edu.vn.cafemanagement.data.viewmodel.unit_viewmodel

import androidx.lifecycle.*
import com.hadilq.liveevent.LiveEvent
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.extension.Status
import fit.tdc.edu.vn.cafemanagement.data.model.FormState
import fit.tdc.edu.vn.cafemanagement.data.model.isNameValid
import fit.tdc.edu.vn.cafemanagement.data.model.unit.Unit
import fit.tdc.edu.vn.cafemanagement.data.model.unit.UnitViewFormState
import fit.tdc.edu.vn.cafemanagement.data.repository.impl.UnitRepository

class UnitCreateViewModel (
    private val unitRepository: UnitRepository
) : ViewModel() {

    private var _viewType = MutableLiveData<FormState.Type?>(null)
    val viewType: LiveData<FormState.Type?> = _viewType

    private var _formState = MutableLiveData<UnitViewFormState>()
    val formState: LiveData<UnitViewFormState> = _formState

    private var _currentUnitId = LiveEvent<String>()
    val currentUnit = MediatorLiveData<Unit?>()
//            : LiveData<FirestoreResource<Unit>> = _currentUnitId.switchMap {
//        unitRepository.getUnit(it)
//    }

    init {
        with(currentUnit) {
            addSource(
                _currentUnitId.switchMap {unitId ->
                    unitRepository.getUnit(unitId)
                }
            ) { result ->
                if (result.status == Status.SUCCESS) {
                    currentUnit.value = result.data
                }
            }
        }

    }

    fun setViewType(type: FormState.Type) {
        _viewType.value = type
    }

    private var allUnits = unitRepository.getAllUnits()

    fun insert(unit: Unit) = unitRepository.insert(unit)

    fun update(unit: Unit) = unitRepository.update(unit)

    fun delete(unit: Unit) = unitRepository.delete(unit)

    fun getAllUnits() = allUnits

    fun getUnit(unitId: String) {
        _currentUnitId.value = unitId
    }

    fun dataChange(unit: Unit) {
        when {
            unit == currentUnit.value -> {
                _formState.value = UnitViewFormState(
                    nameError = null
                ).apply {
                    isChanged = false
                    isDataValid = true
                }
            }
            !isNameValid(unit.name) -> {
                _formState.value = UnitViewFormState(
                    nameError = R.string.invalid_unit_name
                ).apply {
                    isChanged = false
                    isDataValid = false
                }
            }
            isNameValid(unit.name) && unit != currentUnit.value -> {
                _formState.value = UnitViewFormState(
                    nameError = null
                ).apply {
                    isChanged = true
                    isDataValid = true
                }
            }
        }
    }
}

