package fit.tdc.edu.vn.cafemanagement.data.viewmodel.zone

import android.util.Log
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.model.isNameValid
import fit.tdc.edu.vn.cafemanagement.data.model.zone.Zone
import fit.tdc.edu.vn.cafemanagement.data.model.zone.ZoneViewFormState
import fit.tdc.edu.vn.cafemanagement.data.repository.ZoneRepositoryAPI
import fit.tdc.edu.vn.cafemanagement.fragment.BaseDetailViewModel

class ZoneDetailViewModel(
    private val zoneRepository: ZoneRepositoryAPI
) : BaseDetailViewModel<Zone>() {

    override fun getItem(id: String) = zoneRepository.getZone(id)

    override fun insert(item: Zone) = zoneRepository.insert(item)

    override fun update(item: Zone) = zoneRepository.update(item)

    override fun validate(item: Zone?) {
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
                _formState.value = ZoneViewFormState(
                    nameError = null
                ).apply {
                    isChanged = false
                    isDataValid = false
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
                Log.d("test", item.toString() + ":" + currentItem.value.toString())
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