package fit.tdc.edu.vn.cafemanagement.data.viewmodel.table_viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
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
import fit.tdc.edu.vn.cafemanagement.data.repository.impl.TableRepository
import fit.tdc.edu.vn.cafemanagement.fragment.BaseDetailViewModel

class TableDetailViewModel(
    private val tableRepository: TableRepositoryAPI,
    private val zoneRepository: ZoneRepositoryAPI
) : BaseDetailViewModel<Table>() {
    //val pos: LiveData<Int> = CombinedLiveData<Table, List<Zone>, Int>(currentItem, )

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

    override fun insert(table: Table) = tableRepository.insert(table)

    override fun update(table: Table) = tableRepository.update(table)

    override fun validate(item: Table?) {
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