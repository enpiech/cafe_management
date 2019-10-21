package fit.tdc.edu.vn.cafemanagement.data.viewmodel.zone_viewmodel

import androidx.lifecycle.*
import com.hadilq.liveevent.LiveEvent
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.extension.Status
import fit.tdc.edu.vn.cafemanagement.data.model.FormState
import fit.tdc.edu.vn.cafemanagement.data.model.isNameValid
import fit.tdc.edu.vn.cafemanagement.data.model.zone.Zone
import fit.tdc.edu.vn.cafemanagement.data.model.zone.ZoneViewFormState
import fit.tdc.edu.vn.cafemanagement.data.repository.impl.TableRepository
import fit.tdc.edu.vn.cafemanagement.data.repository.impl.ZoneRepository

class ZoneViewModel(
    private val zoneRepository: ZoneRepository,
    private val tableRepository: TableRepository
) : ViewModel() {

    private var _viewType = MutableLiveData<FormState.Type?>(null)
    val viewType: LiveData<FormState.Type?> = _viewType

    private var _formState = MutableLiveData<ZoneViewFormState>(null)
    val formState: LiveData<ZoneViewFormState> = _formState

    private var _currentZoneId = LiveEvent<String>()
    val currentZone = MediatorLiveData<Zone?>()

    init {
        with(currentZone) {
            addSource(
                _currentZoneId.switchMap {zoneId ->
                    zoneRepository.getZone(zoneId)
                }
            ) { result ->
                if (result.status == Status.SUCCESS) {
                    currentZone.value = result.data
                }
            }
        }

    }

    fun setViewType(type: FormState.Type) {
        _viewType.value = type
    }

    private var allZones = zoneRepository.getAllZones()

    fun insert(zone: Zone) = zoneRepository.insert(zone)

    fun update(zone: Zone) = zoneRepository.update(zone)

    fun delete(zone: Zone) = zoneRepository.delete(zone)

    fun getAllZones() = allZones

    fun getZone(zoneId: String) {
        _currentZoneId.value = zoneId
    }

    fun dataChange(zone: Zone) {
        when {
            zone == currentZone.value -> {
                _formState.value = ZoneViewFormState(
                    nameError = null
                ).apply {
                    isChanged = false
                    isDataValid = true
                }
            }
            !isNameValid(zone.name) -> {
                _formState.value = ZoneViewFormState(
                    nameError = R.string.invalid_zone_name
                ).apply {
                    isChanged = false
                    isDataValid = false
                }
            }
            isNameValid(zone.name) && zone != currentZone.value -> {
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