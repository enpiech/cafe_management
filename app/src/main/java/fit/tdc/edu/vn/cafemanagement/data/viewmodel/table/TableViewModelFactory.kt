package fit.tdc.edu.vn.cafemanagement.data.viewmodel.table

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.FireBaseDataSource
import fit.tdc.edu.vn.cafemanagement.data.repository.impl.TableRepository
import fit.tdc.edu.vn.cafemanagement.data.repository.impl.ZoneRepository

class TableViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TableDetailViewModel::class.java)) {
            return TableDetailViewModel(
                tableRepository = TableRepository(
                    dataSource = FireBaseDataSource()
                ),
                zoneRepository = ZoneRepository(
                    dataSource = FireBaseDataSource()
                )
            ) as T
        } else if (modelClass.isAssignableFrom(TableListViewModel::class.java)) {
            return TableListViewModel(
                tableRepository = TableRepository(
                    dataSource = FireBaseDataSource()
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}