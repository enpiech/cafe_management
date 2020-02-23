package fit.tdc.edu.vn.cafemanagement.data.repository.zone

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.table.TableRemoteDataSource
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.zone.ZoneRemoteDataSource
import fit.tdc.edu.vn.cafemanagement.data.extension.DocumentType
import fit.tdc.edu.vn.cafemanagement.data.extension.TaskLiveData
import fit.tdc.edu.vn.cafemanagement.data.model.table.Table
import fit.tdc.edu.vn.cafemanagement.data.model.user.UserInfor
import fit.tdc.edu.vn.cafemanagement.data.model.zone.Zone

class ZoneRepositoryImpl(
    private val zoneDataSource: ZoneRemoteDataSource,
    private val tableDataSource: TableRemoteDataSource
) : ZoneRepository {

    private val filteredTableList = MediatorLiveData<List<Table>?>()

    private var listTable =
        tableDataSource.readTables(UserInfor.getInstance().storeId!!, DocumentType.ALL)

    private var currentZoneId: String = ""

    init {
        filteredTableList.addSource(listTable) {
            filterTable(currentZoneId)
        }
    }

    override fun tablesInZone(id: String): LiveData<List<Table>?> {
        this.currentZoneId = id
        listTable = tableDataSource.readTables(UserInfor.getInstance().storeId!!, DocumentType.ALL)
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

    override fun getAllZones() =
        zoneDataSource.readZones(UserInfor.getInstance().storeId!!, DocumentType.ALL)

    override fun getZone(id: String) =
        zoneDataSource.readZone(UserInfor.getInstance().storeId!!, id, DocumentType.SINGLE)

    override fun insert(zone: Zone) =
        zoneDataSource.createZone(UserInfor.getInstance().storeId!!, zone)

    override fun update(zone: Zone) =
        zoneDataSource.updateZone(UserInfor.getInstance().storeId!!, zone)

    override fun delete(zone: Zone): TaskLiveData<Void> {
        return zoneDataSource.deleteZone(UserInfor.getInstance().storeId!!, zone.id)
    }

}