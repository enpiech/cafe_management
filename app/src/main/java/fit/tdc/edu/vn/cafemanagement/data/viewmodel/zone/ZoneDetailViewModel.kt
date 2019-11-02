package fit.tdc.edu.vn.cafemanagement.data.viewmodel.zone

import androidx.lifecycle.SavedStateHandle
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.model.isValidZoneName
import fit.tdc.edu.vn.cafemanagement.data.model.zone.Zone
import fit.tdc.edu.vn.cafemanagement.data.model.zone.ZoneViewFormState
import fit.tdc.edu.vn.cafemanagement.data.repository.ZoneRepositoryAPI
import fit.tdc.edu.vn.cafemanagement.fragment.BaseDetailViewModel

class ZoneDetailViewModel(
    private val handle: SavedStateHandle,
    private val zoneRepository: ZoneRepositoryAPI
) : BaseDetailViewModel<Zone>() {
    override var saved: Zone
        get() = Zone(
            name = handle.get("name")
        )
        set(value) {
            handle.set("name", value.name)
        }

    override fun getItem(id: String) = zoneRepository.getZone(id)

    override fun insert(item: Zone) = zoneRepository.insert(item)

    override fun update(item: Zone) = zoneRepository.update(item)

    override fun validate(item: Zone?) {
        when (item) {
            null -> {
                _formState.value = ZoneViewFormState().apply {
                    isChanged = false
                    isDataValid = true
                }
                return
            }
            currentItem.value -> {
                _formState.value = ZoneViewFormState()
            }
        }

        // SavedStateHandle
        saved = item!!

        if (_formState.value == null) {
            _formState.value = ZoneViewFormState()
        }
        val formState = _formState.value as ZoneViewFormState
        var noError = true

        if (item.name != null && !item.name.isValidZoneName()) {
            formState.nameError = R.string.invalid_user_name
            noError = false
        } else {
            formState.nameError = null
        }

        if (currentItem.value != null) {
            when {
                item.name != currentItem.value!!.name -> formState.isChanged = true
                else -> formState.isChanged = false
            }
        }

        formState.isDataValid = noError
        _formState.value = formState
    }
}