package fit.tdc.edu.vn.cafemanagement.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import fit.tdc.edu.vn.cafemanagement.data.data_source.FireBaseDataSource
import fit.tdc.edu.vn.cafemanagement.data.model.Table

class TableRepository(val dataSource: FireBaseDataSource) {

    fun getAllTables() : LiveData<ArrayList<Table>> {
        return dataSource.getTableList("EfzspceETNgWk56YDOOt")
    }

    fun getTable(id: String) : LiveData<Table?> {
        return dataSource.getTable("EfzspceETNgWk56YDOOt", id)
    }

    fun insert(table: Table) {
        dataSource.createTable("EfzspceETNgWk56YDOOt", table)
    }

    fun update(table: Table) {
        //TODO: get update function
    }

    fun delete(table: Table) {
        dataSource.deleteTable("EfzspceETNgWk56YDOOt", table.id)
    }

    fun deleteAllTables() {
        //TODO: get delete all function
    }
}