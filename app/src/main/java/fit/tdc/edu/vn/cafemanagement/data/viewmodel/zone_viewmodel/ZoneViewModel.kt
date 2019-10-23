package fit.tdc.edu.vn.cafemanagement.data.viewmodel.zone_viewmodel

import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.model.isNameValid
import fit.tdc.edu.vn.cafemanagement.data.model.zone.Zone
import fit.tdc.edu.vn.cafemanagement.data.model.zone.ZoneViewFormState
import fit.tdc.edu.vn.cafemanagement.data.repository.ZoneRepositoryAPI
import fit.tdc.edu.vn.cafemanagement.fragment.BaseViewViewModel

class ZoneViewModel(
    private val zoneRepository: ZoneRepositoryAPI
) : BaseViewViewModel<Zone>() {

    override fun getAllItems() = zoneRepository.getAllZones()

    override fun getItem(id: String) = zoneRepository.getZone(id)

    override fun insert(item: Zone) = zoneRepository.insert(item)

    override fun update(item: Zone) = zoneRepository.update(item)

    override fun delete(item: Zone) = zoneRepository.delete(item)

    override fun dataChange(item: Zone) {
        when {
            item == currentItem.value -> {
                _formState.value = ZoneViewFormState(
                    nameError = null
                ).apply {
                    isChanged = false
                    isDataValid = true
                }
            }
            !isNameValid(item.name) -> {
                _formState.value = ZoneViewFormState(
                    nameError = R.string.invalid_zone_name
                ).apply {
                    isChanged = false
                    isDataValid = false
                }
            }
            isNameValid(item.name) && item != currentItem.value -> {
                _formState.value = ZoneViewFormState(
                    nameError = null
                ).apply {
                    isChanged = true
                    isDataValid = true
                }
            }
        }
    }
}