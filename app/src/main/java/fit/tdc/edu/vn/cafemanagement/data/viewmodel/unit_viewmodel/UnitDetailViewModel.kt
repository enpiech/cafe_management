package fit.tdc.edu.vn.cafemanagement.data.viewmodel.unit_viewmodel

import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.model.isNameValid
import fit.tdc.edu.vn.cafemanagement.data.model.unit.Unit
import fit.tdc.edu.vn.cafemanagement.data.model.unit.UnitViewFormState
import fit.tdc.edu.vn.cafemanagement.data.model.zone.ZoneViewFormState
import fit.tdc.edu.vn.cafemanagement.data.repository.impl.UnitRepository
import fit.tdc.edu.vn.cafemanagement.fragment.BaseDetailViewModel

class UnitDetailViewModel (
    private val itemRepository: UnitRepository
) : BaseDetailViewModel<Unit>() {
    override fun getItem(id: String) = itemRepository.getUnit(id)

    override fun insert(item: Unit) = itemRepository.insert(item)

    override fun update(item: Unit) = itemRepository.update(item)

    override fun dataChange(item: Unit?) {
        when {
            item == null -> {
                _formState.value = ZoneViewFormState(
                    nameError = null
                ).apply {
                    isChanged = false
                    isDataValid = true
                }
            }
            item == currentItem.value -> {
                _formState.value = UnitViewFormState(
                    nameError = null
                ).apply {
                    isChanged = false
                    isDataValid = true
                }
            }
            !isNameValid(item.name) -> {
                _formState.value = UnitViewFormState(
                    nameError = R.string.invalid_unit_name
                ).apply {
                    isChanged = false
                    isDataValid = false
                }
            }
            isNameValid(item.name) && item != currentItem.value -> {
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

