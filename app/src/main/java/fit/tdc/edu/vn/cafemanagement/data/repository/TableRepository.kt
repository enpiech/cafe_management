package fit.tdc.edu.vn.cafemanagement.data.repository

import fit.tdc.edu.vn.cafemanagement.data.data_source.FireBaseDataSource
import fit.tdc.edu.vn.cafemanagement.data.extension.DocumentType
import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.Table

class TableRepository(val dataSource: FireBaseDataSource) {

    fun getAllTables() = dataSource.getTableList("EfzspceETNgWk56YDOOt",DocumentType.ALL)

    fun getTable(id: String) = dataSource.getTable("EfzspceETNgWk56YDOOt", id,DocumentType.SINGLE)

    fun insert(table: Table) =
        dataSource.createTable("EfzspceETNgWk56YDOOt", table)

    fun update(table: Table) {
        //TODO: get update function
    }

    fun delete(table: Table) =
        dataSource.deleteTable("EfzspceETNgWk56YDOOt", table.id!!)

    fun deleteAllTables() {
        //TODO: get delete all function
    }
}