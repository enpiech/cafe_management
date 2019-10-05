package fit.tdc.edu.vn.cafemanagement.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import fit.tdc.edu.vn.cafemanagement.data.data_source.FireBaseDataSource
import fit.tdc.edu.vn.cafemanagement.data.model.Table

class TableRepository(val dataSource: FireBaseDataSource) {

    fun getAllTables() : LiveData<ArrayList<Table>> {
        return dataSource.getTableMap().map { map: HashMap<String, Table> ->
            ArrayList<Table>(map.values)
        }
    }

    fun getTable(id: String) : LiveData<Table?> {
        return dataSource.getTableMap().map { map: HashMap<String, Table> ->
            map[id] = Table.builder().build()
            map[id]
        }
    }

    fun insert(table: Table) {
        //TODO: get insert function
    }

    fun update(table: Table) {
        //TODO: get update function
    }

    fun delete(table: Table) {
        //TODO: get delete function
    }

    fun deleteAllTables() {
        //TODO: get delete all function
    }
}