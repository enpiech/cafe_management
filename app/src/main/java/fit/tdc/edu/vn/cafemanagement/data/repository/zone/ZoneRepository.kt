package fit.tdc.edu.vn.cafemanagement.data.repository.zone

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.FireBaseAPI
import fit.tdc.edu.vn.cafemanagement.data.extension.DocumentType
import fit.tdc.edu.vn.cafemanagement.data.extension.TaskLiveData
import fit.tdc.edu.vn.cafemanagement.data.model.table.Table
import fit.tdc.edu.vn.cafemanagement.data.model.user.UserInfor
import fit.tdc.edu.vn.cafemanagement.data.model.zone.Zone

class ZoneRepository(
    val dataSource: FireBaseAPI
) : ZoneRepositoryAPI {

    private val filteredTableList = MediatorLiveData<List<Table>?>()

    private var listTable = dataSource.getTableList(UserInfor.getInstance().storeId!!, DocumentType.ALL)

    private var currentZoneId: String = ""

    init {
        filteredTableList.addSource(listTable) {
            filterTable(currentZoneId)
        }
    }

    override fun tablesInZone(id: String): LiveData<List<Table>?> {
        this.currentZoneId = id
        listTable = dataSource.getTableList(UserInfor.getInstance().storeId!!, DocumentType.ALL)
        return filteredTableList
    }

    private fun filterTable(zoneId: String) = listTable.value?.let {
        filteredTableList.value = listTableInZone(it.data, currentZoneId)
    }.also { currentZoneId = zoneId }

    private fun listTableInZone(list: List<Table>?, zoneId: String) = list?.filter { table ->
        if (zoneId.isEmpty()) {
            true
        } else {
            table.zoneId.equals(zoneId)
        }
    }

    override fun getAllZones() = dataSource.getZoneList(UserInfor.getInstance().storeId!!, DocumentType.ALL)

    override fun getZone(id: String) =
        dataSource.getZone(UserInfor.getInstance().storeId!!, id, DocumentType.SINGLE)

    override fun insert(zone: Zone) =
        dataSource.createZone(UserInfor.getInstance().storeId!!, zone)

    override fun update(zone: Zone) =
        dataSource.modifyZone(UserInfor.getInstance().storeId!!, zone)

    override fun delete(zone: Zone): TaskLiveData<Void> {
        return dataSource.deleteZone(UserInfor.getInstance().storeId!!, zone.id)
    }

}