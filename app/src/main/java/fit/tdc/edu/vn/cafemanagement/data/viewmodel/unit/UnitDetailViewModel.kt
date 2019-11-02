package fit.tdc.edu.vn.cafemanagement.data.viewmodel.unit

import androidx.lifecycle.SavedStateHandle
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.model.FormState
import fit.tdc.edu.vn.cafemanagement.data.model.isValidUnitName
import fit.tdc.edu.vn.cafemanagement.data.model.unit.Unit
import fit.tdc.edu.vn.cafemanagement.data.model.unit.UnitViewFormState
import fit.tdc.edu.vn.cafemanagement.data.repository.UnitRepositoryAPI
import fit.tdc.edu.vn.cafemanagement.data.repository.impl.UnitRepository
import fit.tdc.edu.vn.cafemanagement.fragment.BaseDetailViewModel

class UnitDetailViewModel (
    private val handle: SavedStateHandle,
    private val unitRepository: UnitRepositoryAPI
) : BaseDetailViewModel<Unit>() {
    override var saved: Unit
        get() = Unit(
            name = handle.get("name")
        )
        set(value) {
            handle.set("name", value.name)
        }

    override fun getItem(id: String) = unitRepository.getUnit(id)

    override fun insert(unit: Unit) = unitRepository.insert(unit)

    override fun update(unit: Unit) = unitRepository.update(unit)

    override fun validate(item: Unit?) {
        when (item) {
            null -> {
                _formState.value = UnitViewFormState().apply {
                    isChanged = false
                    isDataValid = true
                }
                return
            }
            currentItem.value -> {
                _formState.value = UnitViewFormState()
            }
        }

        // SavedStateHandle
        saved = item!!

        if (_formState.value == null) {
            _formState.value = UnitViewFormState()
        }
        val formState = _formState.value as UnitViewFormState
        var noError = true

        if (item.name != null && !item.name.isValidUnitName()) {
            formState.nameError = R.string.invalid_user_name
            noError = false
        } else {
            formState.nameError = null
        }

        if (currentItem.value != null) {
            when {
                item.name != currentItem.value!!.name -> formState.isChanged = true
                viewType.value == FormState.Type.ADD -> formState.isChanged = true
                else -> formState.isChanged = false
            }
        }

        formState.isDataValid = noError
        _formState.value = formState
    }
}

