package fit.tdc.edu.vn.cafemanagement.viewmodel.table_viewmodel

import androidx.lifecycle.*
import fit.tdc.edu.vn.cafemanagement.data.extension.FirestoreResource
import fit.tdc.edu.vn.cafemanagement.data.model.FormState
import fit.tdc.edu.vn.cafemanagement.data.model.table.Table
import fit.tdc.edu.vn.cafemanagement.data.repository.impl.TableRepository

class TableCreateViewModel (
    private val tableRepository: TableRepository
) : ViewModel() {

    private var _viewState = MutableLiveData<FormState.Type?>(null)
    val viewState: LiveData<FormState.Type?> = _viewState

//    private var _formState = MutableLiveData<TableViewFormState>()
//    val formState: LiveData<TableViewFormState> = _formState

    private var _currentTableId = MutableLiveData<String>()
    val currentTable: LiveData<FirestoreResource<Table>> = _currentTableId.switchMap {
        tableRepository.getTable(it)
    }


    fun setViewType(type: FormState.Type) {
        _viewState.value = type
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

//    fun dataChange(table: Table?) {
//        when {
//            !isNameValid(table?.name) -> _formState.value = TableViewFormState(nameError = R.string.invalid_table_name)
//            else -> _formState.value = TableViewFormState().also { it.isDataValid = true }
//        }
//    }
}

