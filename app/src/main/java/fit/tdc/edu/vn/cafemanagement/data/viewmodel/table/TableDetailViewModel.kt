package fit.tdc.edu.vn.cafemanagement.data.viewmodel.table

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.map
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.extension.CombinedLiveData
import fit.tdc.edu.vn.cafemanagement.data.extension.Status
import fit.tdc.edu.vn.cafemanagement.data.model.isNameValid
import fit.tdc.edu.vn.cafemanagement.data.model.table.Table
import fit.tdc.edu.vn.cafemanagement.data.model.table.TableViewFormState
import fit.tdc.edu.vn.cafemanagement.data.model.zone.Zone
import fit.tdc.edu.vn.cafemanagement.data.repository.TableRepositoryAPI
import fit.tdc.edu.vn.cafemanagement.data.repository.ZoneRepositoryAPI
import fit.tdc.edu.vn.cafemanagement.fragment.BaseDetailViewModel

class TableDetailViewModel(
    private val handle: SavedStateHandle,
    private val tableRepository: TableRepositoryAPI,
    zoneRepository: ZoneRepositoryAPI
) : BaseDetailViewModel<Table>() {
    override var saved: Table
        get() = Table(
            name = handle.get("name"),
            zoneId = handle.get("zoneId"),
            state = handle.get<Table.State>("state") ?: Table.State.FREE
        )
        set(value) {
            handle.set("name", value.name)
            handle.set("zoneId", value.zoneId)
            handle.set("state", value.state)
        }

    private val _zoneResponseList = zoneRepository.getAllZones()
    val zoneList: LiveData<List<Zone>> = _zoneResponseList.map {
        if (it.status == Status.SUCCESS && it.data != null) {
            it.data
        } else {
            listOf()
        }
    }
    val currentZone =
        CombinedLiveData<Table, List<Zone>, Zone?>(currentItem, zoneList) { zone, zoneList ->
            getCurrentStoreOfUser(zone, zoneList)
        }

    private fun getCurrentStoreOfUser(table: Table?, res: List<Zone>?): Zone? {
        if (!res.isNullOrEmpty() && table != null) {
            return res.find { zone -> zone.id == table.zoneId }
        }
        return null
    }

    override fun getItem(id: String) = tableRepository.getTable(id)

    override fun insert(item: Table) = tableRepository.insert(item)

    override fun update(item: Table) = tableRepository.update(item)

    override fun validate(item: Table?) {
        item?.let {
            saved = it
        }
        when {
            item == currentItem.value -> {
                _formState.value = TableViewFormState(
                    nameError = null
                ).apply {
                    isChanged = false
                    isDataValid = true
                }
            }
            !isNameValid(item?.name) -> {
                _formState.value = TableViewFormState(
                    nameError = R.string.invalid_table_name
                ).apply {
                    isChanged = false
                    isDataValid = false
                }
            }
            isNameValid(item?.name) && item != currentItem.value -> {
                _formState.value = TableViewFormState(
                    nameError = null
                ).apply {
                    isChanged = true
                    isDataValid = true
                }
            }
        }
    }
}