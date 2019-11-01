package fit.tdc.edu.vn.cafemanagement.data.viewmodel.table_waiter

import fit.tdc.edu.vn.cafemanagement.data.model.table.Table
import fit.tdc.edu.vn.cafemanagement.data.repository.impl.TableRepository
import fit.tdc.edu.vn.cafemanagement.fragment.BaseListViewModel

class TableWaiterListViewModel(
    private val tableRepository: TableRepository
) : BaseListViewModel<Table>() {
    override fun getAllItems() = tableRepository.getAllTables()

    override fun delete(item: Table) = tableRepository.delete(item)
}

