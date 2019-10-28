package fit.tdc.edu.vn.cafemanagement.ui.viewmodel.table_viewmodel

import androidx.lifecycle.*
import com.hadilq.liveevent.LiveEvent
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.extension.Status
import fit.tdc.edu.vn.cafemanagement.data.model.FormState
import fit.tdc.edu.vn.cafemanagement.data.model.isNameValid
import fit.tdc.edu.vn.cafemanagement.data.model.table.Table
import fit.tdc.edu.vn.cafemanagement.data.model.table.TableViewFormState
import fit.tdc.edu.vn.cafemanagement.data.repository.impl.TableRepository

class TableViewModel(
    private val tableRepository: TableRepository
) : ViewModel() {

    private var _viewType = MutableLiveData<FormState.Type?>(null)
    val viewType: LiveData<FormState.Type?> = _viewType

    private var _formState = MutableLiveData<TableViewFormState>(null)
    val formState: LiveData<TableViewFormState> = _formState

    private var _currentTableId = LiveEvent<String>()
    val currentTable = MediatorLiveData<Table?>()

    init {
        with(currentTable) {
            addSource(
                _currentTableId.switchMap { tableId ->
                    tableRepository.getTable(tableId)
                }
            ) { result ->
                if (result.status == Status.SUCCESS) {
                    currentTable.value = result.data
                }
            }
        }

    }

    fun setViewType(type: FormState.Type) {
        _viewType.value = type
    }

    private var allTables = tableRepository.getAllTables()

    private val tables = MediatorLiveData<List<Table>>()

    fun insert(table: Table) = tableRepository.insert(table)

    fun update(table: Table) = tableRepository.update(table)

    fun delete(table: Table) = tableRepository.delete(table)

    fun getAllTables() = allTables

    fun getTable(tableId: String) {
        _currentTableId.value = tableId
    }

    fun getCurrentTable(tableId: String) {
        _currentTableId.value = tableId
    }

    fun dataChange(table: Table) {
        when {
            table == currentTable.value -> {
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
            isNameValid(table.name) && table != currentTable.value -> {
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