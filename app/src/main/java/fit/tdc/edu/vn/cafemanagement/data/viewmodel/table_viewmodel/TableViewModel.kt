package fit.tdc.edu.vn.cafemanagement.data.viewmodel.table_viewmodel

import androidx.lifecycle.LiveData
import fit.tdc.edu.vn.cafemanagement.data.model.Table
import fit.tdc.edu.vn.cafemanagement.data.model.Zone
import fit.tdc.edu.vn.cafemanagement.data.repository.TableRepository

class TableViewModel (private val tableRepository: TableRepository) {

    private var allTables: LiveData<ArrayList<Table>> = tableRepository.getAllTables()

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

    fun getAlltables(): LiveData<ArrayList<Table>> {
        return allTables
    }

}