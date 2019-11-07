package fit.tdc.edu.vn.cafemanagement.data.viewmodel.table

import fit.tdc.edu.vn.cafemanagement.data.model.table.Table
import fit.tdc.edu.vn.cafemanagement.data.repository.table.TableRepository
import fit.tdc.edu.vn.cafemanagement.fragment.BaseListViewModel

class TableListViewModel(
    private val tableRepository: TableRepository
) : BaseListViewModel<Table>() {
    override fun getAllItems() = tableRepository.getAllTables()

    override fun delete(item: Table) = tableRepository.delete(item)
}

