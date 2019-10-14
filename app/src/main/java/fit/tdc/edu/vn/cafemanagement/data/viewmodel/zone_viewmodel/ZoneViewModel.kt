package fit.tdc.edu.vn.cafemanagement.data.viewmodel.zone_viewmodel

import androidx.lifecycle.*
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.extension.FirestoreResource
import fit.tdc.edu.vn.cafemanagement.data.model.FormState
import fit.tdc.edu.vn.cafemanagement.data.model.isNameValid
import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.Table
import fit.tdc.edu.vn.cafemanagement.data.model.zone.Zone
import fit.tdc.edu.vn.cafemanagement.data.model.zone.ZoneViewFormState
import fit.tdc.edu.vn.cafemanagement.data.repository.impl.TableRepository
import fit.tdc.edu.vn.cafemanagement.data.repository.impl.ZoneRepository

class ZoneViewModel (
    private val zoneRepository: ZoneRepository,
    private val tableRepository: TableRepository
) : ViewModel() {

    private var _viewState = MutableLiveData<FormState.Type?>(null)
    val viewState: LiveData<FormState.Type?> = _viewState

    private var _formState = MutableLiveData<ZoneViewFormState>()
    val formState: LiveData<ZoneViewFormState> = _formState

    private var _currentZoneId = MutableLiveData<String>()
    val currentZone: LiveData<FirestoreResource<Zone>> = _currentZoneId.switchMap {
        zoneRepository.getZone(it)
    }


    fun setViewType(type: FormState.Type) {
        _viewState.value = type
    }

    private var allZones = zoneRepository.getAllZones()

    private val tables = MediatorLiveData<List<Table>>()

    fun insert(zone: Zone) = zoneRepository.insert(zone)

    fun update(zone: Zone) = zoneRepository.update(zone)

    fun delete(zone: Zone) = zoneRepository.delete(zone)

    fun getAllZones() = allZones

    fun getZone(zoneId: String) {
        _currentZoneId.value = zoneId
    }

    fun getTablesInZone(id: String) = tableRepository.getAllTables().value.let {

        tables.value = it!!.data

        tables
    }


    fun dataChange(zone: Zone?) {
        when {
            !isNameValid(zone?.name) -> _formState.value = ZoneViewFormState(nameError = R.string.invalid_zone_name)
            else -> _formState.value = ZoneViewFormState().also { it.isDataValid = true }
        }
    }
}