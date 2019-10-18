package fit.tdc.edu.vn.cafemanagement.data.viewmodel.table_viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.FireBaseDataSource
import fit.tdc.edu.vn.cafemanagement.data.repository.impl.TableRepository

class TableViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TableViewModel::class.java)) {
            return TableViewModel(
                 tableRepository = TableRepository(
                     dataSource = FireBaseDataSource()
                 )
            ) as T
        }else if (modelClass.isAssignableFrom(TableCreateViewModel::class.java)) {
            return TableCreateViewModel(
                tableRepository = TableRepository(
                    dataSource = FireBaseDataSource()
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}