package fit.tdc.edu.vn.cafemanagement.data.viewmodel.zone_viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.Table
import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.Zone
import fit.tdc.edu.vn.cafemanagement.data.repository.impl.TableRepository
import fit.tdc.edu.vn.cafemanagement.data.repository.impl.ZoneRepository
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.table_viewmodel.TableViewModel

class ZoneViewModel (private val zoneRepository: ZoneRepository, tableRepository: TableRepository) : ViewModel() {

    private var tableViewModel = TableViewModel(tableRepository)
    private var allZones = zoneRepository.getAllZones()

    val tables = MediatorLiveData<List<Table>>()


    fun insert(zone: Zone) {
        zoneRepository.insert(zone)
    }

    fun update(zone: Zone) {
        zoneRepository.update(zone)
    }

    fun delete(zone: Zone) {
        zoneRepository.delete(zone)
    }

    fun getAllZones() = allZones

    fun getTablesInZone(id: String) = tableViewModel.getAlltables().value.let {

        tables.value = it!!.data

        tables
    }
}