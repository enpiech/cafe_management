package fit.tdc.edu.vn.cafemanagement.data.viewmodel.table_viewmodel

import androidx.lifecycle.*
import com.hadilq.liveevent.LiveEvent
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.extension.CollectionLiveData
import fit.tdc.edu.vn.cafemanagement.data.extension.DocumentLiveData
import fit.tdc.edu.vn.cafemanagement.data.extension.FirestoreResource
import fit.tdc.edu.vn.cafemanagement.data.extension.Status
import fit.tdc.edu.vn.cafemanagement.data.model.FormState
import fit.tdc.edu.vn.cafemanagement.data.model.isNameValid
import fit.tdc.edu.vn.cafemanagement.data.model.table.Table
import fit.tdc.edu.vn.cafemanagement.data.model.table.TableViewFormState
import fit.tdc.edu.vn.cafemanagement.data.repository.impl.TableRepository
import fit.tdc.edu.vn.cafemanagement.fragment.BaseViewViewModel

class TableViewModel (
    private val tableRepository: TableRepository
) : BaseViewViewModel<Table>() {
    override fun getAllItems() = tableRepository.getAllTables()

    override fun getItem(id: String) = tableRepository.getTable(id)


    private var allTables = tableRepository.getAllTables()

    private val tables = MediatorLiveData<List<Table>>()

    override fun insert(table: Table) = tableRepository.insert(table)

    override fun update(table: Table) = tableRepository.update(table)

    override fun delete(table: Table) = tableRepository.delete(table)

    fun getAllTables() = allTables


    override fun dataChange(table: Table) {
        when {
            table == currentItem.value -> {
                _formState.value = TableViewFormState(
                    nameError = null
                ).apply {
                    isChanged = false
                    isDataValid = true
                }
            }
            !isNameValid(table.name) -> {
                _formState.value = TableViewFormState(
                    nameError = R.string.invalid_table_name
                ).apply {
                    isChanged = false
                    isDataValid = false
                }
            }
            isNameValid(table.name) && table != currentItem.value -> {
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