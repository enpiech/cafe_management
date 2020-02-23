package fit.tdc.edu.vn.cafemanagement.data.viewmodel.table_waiter

import androidx.lifecycle.SavedStateHandle
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.model.table.Table
import fit.tdc.edu.vn.cafemanagement.data.model.table.TableViewFormState
import fit.tdc.edu.vn.cafemanagement.data.repository.table.TableRepository
import fit.tdc.edu.vn.cafemanagement.fragment.BaseDetailViewModel
import fit.tdc.edu.vn.cafemanagement.util.isNameValid

class TableWaiterDetailViewModel(
    private val handle: SavedStateHandle,
    private val tableRepository: TableRepository
) : BaseDetailViewModel<Table>() {
    override var saved: Table
        get() = Table(
            name = handle.get("name"),
            zoneId = handle.get("zoneId"),
            state = handle.get<Table.State>("state") ?: Table.State.FREE
        )
        set(value) {
            handle.set("name", value.name)
            handle.set("zoneId", value.zoneId)
            handle.set("state", value.state)
        }


    override fun getItem(id: String) = tableRepository.getTable(id)

    override fun insert(item: Table) = tableRepository.insert(item)

    override fun update(item: Table) = tableRepository.update(item)

    override fun validate(item: Table?) {
        when {
            item == currentItem.value -> {
                _formState.value = TableViewFormState(
                    nameError = null
                ).apply {
                    isChanged = false
                    isDataValid = true
                }
            }
            !isNameValid(item?.name) -> {
                _formState.value = TableViewFormState(
                    nameError = R.string.invalid_table_name
                ).apply {
                    isChanged = false
                    isDataValid = false
                }
            }
            isNameValid(item?.name) && item != currentItem.value -> {
                _formState.value = TableViewFormState(
                    nameError = null
                ).apply {
                    isChanged = true
                    isDataValid = true
                }
            }
        }
    }
}