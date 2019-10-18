package fit.tdc.edu.vn.cafemanagement.data.viewmodel.table_viewmodel

import androidx.lifecycle.ViewModel
import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.Table
import fit.tdc.edu.vn.cafemanagement.data.repository.TableRepositoryAPI

class TableViewModel (
    private val tableRepository: TableRepositoryAPI): ViewModel() {

    fun insert(table: Table) {
        tableRepository.insert(table)
    }

    fun update(table: Table) {
        tableRepository.update(table)
    }

    fun delete(table: Table) {
        tableRepository.delete(table)
    }

    fun getAlltables() = tableRepository.getAllTables()

}