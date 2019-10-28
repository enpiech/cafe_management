package fit.tdc.edu.vn.cafemanagement.data.viewmodel.table_viewmodel

import androidx.lifecycle.MediatorLiveData
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.model.isNameValid
import fit.tdc.edu.vn.cafemanagement.data.model.table.Table
import fit.tdc.edu.vn.cafemanagement.data.model.table.TableViewFormState
import fit.tdc.edu.vn.cafemanagement.data.repository.impl.TableRepository
import fit.tdc.edu.vn.cafemanagement.fragment.BaseDetailViewModel

class TableDetailViewModel(
    private val tableRepository: TableRepository
) : BaseDetailViewModel<Table>() {
    fun getAllItems() = tableRepository.getAllTables()

    override fun getItem(id: String) = tableRepository.getTable(id)


    private var allTables = tableRepository.getAllTables()

    private val tables = MediatorLiveData<List<Table>>()

    override fun insert(table: Table) = tableRepository.insert(table)

    override fun update(table: Table) = tableRepository.update(table)

    fun getAllTables() = allTables


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