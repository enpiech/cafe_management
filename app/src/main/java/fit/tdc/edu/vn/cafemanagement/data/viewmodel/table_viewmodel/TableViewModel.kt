package fit.tdc.edu.vn.cafemanagement.data.viewmodel.table_viewmodel

import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.Table
import fit.tdc.edu.vn.cafemanagement.data.repository.TableRepositoryAPI
import fit.tdc.edu.vn.cafemanagement.data.repository.impl.TableRepository

class TableViewModel (private val tableRepository: TableRepositoryAPI) {

    private var allTables = tableRepository.getAllTables()

    fun insert(table: Table) {
        tableRepository.insert(table)
    }

    fun update(table: Table) {
        tableRepository.update(table)
    }

    fun delete(table: Table) {
        tableRepository.delete(table)
    }

    fun deleteAllTables() {
        tableRepository.deleteAllTables()
    }

    fun getAlltables() = allTables

}