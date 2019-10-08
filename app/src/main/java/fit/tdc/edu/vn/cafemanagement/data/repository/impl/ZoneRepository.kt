package fit.tdc.edu.vn.cafemanagement.data.repository.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.FireBaseDataSource
import fit.tdc.edu.vn.cafemanagement.data.extension.DocumentType
import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.Table
import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.Zone
import fit.tdc.edu.vn.cafemanagement.data.repository.ZoneRepositoryAPI

class ZoneRepository ( val dataSource: FireBaseDataSource):
    ZoneRepositoryAPI {

    val filteredTableList = MediatorLiveData<List<Table>?>()
    private var listTable = dataSource.getTableList("EfzspceETNgWk56YDOOt",DocumentType.ALL)
    private var currentZoneId:String = ""
    init {
        filteredTableList.addSource(listTable) { filteredList ->
            filteredList?.let {
                filteredTableList.value = listTableInZone(filteredList.data, currentZoneId)
            }
        }
    }

    fun tablesInZone(id: String) : LiveData<List<Table>?> {
        this.currentZoneId = id
        listTable = dataSource.getTableList("EfzspceETNgWk56YDOOt",DocumentType.ALL)
        return filteredTableList
    }

    fun filterTable(zoneId: String) = listTable.value?.let {
        filteredTableList.value = listTableInZone(it.data, currentZoneId)
    }.also { currentZoneId = zoneId }

    private fun listTableInZone(list: List<Table>?, zoneId: String) = list?.filter { table ->
        if (zoneId.isEmpty()) {
            true
        } else {
            table.zoneId.equals(zoneId)
        }
    }

    override fun getAllZones() = dataSource.getZoneList("EfzspceETNgWk56YDOOt",DocumentType.ALL)

    override fun getZone(id: String) = dataSource.getZone("EfzspceETNgWk56YDOOt", id,DocumentType.SINGLE)


    override fun insert(zone: Zone) =
        dataSource.createZone("EfzspceETNgWk56YDOOt", zone)

    override fun update(zone: Zone) {
        //TODO: get update function
    }

    override fun delete(zone: Zone) =
        dataSource.deleteZone("EfzspceETNgWk56YDOOt", zone.id)

    override fun deleteAllZones() {
        //TODO: get delete all function
    }
}