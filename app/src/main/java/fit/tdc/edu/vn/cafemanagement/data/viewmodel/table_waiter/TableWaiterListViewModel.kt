package fit.tdc.edu.vn.cafemanagement.data.viewmodel.table_waiter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import fit.tdc.edu.vn.cafemanagement.data.extension.CombinedLiveData
import fit.tdc.edu.vn.cafemanagement.data.model.table.Table
import fit.tdc.edu.vn.cafemanagement.data.repository.impl.TableRepository
import fit.tdc.edu.vn.cafemanagement.fragment.BaseListViewModel

class TableWaiterListViewModel(
    private val tableRepository: TableRepository
) : BaseListViewModel<Table>() {
    override fun getAllItems() = tableRepository.getAllTables()

    override fun delete(item: Table) = tableRepository.delete(item)

    private val _currentListFilter = MutableLiveData<Table.State?>(null)
    fun filterList(state: Table.State?) {
        _currentListFilter.value = state
    }

    private val _items = CombinedLiveData(
        source1 = _currentListFilter,
        source2 = getAllItems(),
        combine = { filter, items ->
            items?.data?.filter {table ->
                if (filter != null) {
                    table.state == filter
                } else {
                    true
                }
            } ?: listOf()
        }
    )
    val items: LiveData<List<Table>> = _items
}

