package fit.tdc.edu.vn.cafemanagement.data.viewmodel.table

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.table.TableRemoteDataSource
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.table.TableRemoteDataSourceImpl
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.zone.ZoneRemoteDataSourceImpl
import fit.tdc.edu.vn.cafemanagement.data.repository.table.TableRepositoryImpl
import fit.tdc.edu.vn.cafemanagement.data.repository.zone.ZoneRepositoryImpl

class TableViewModelFactory(
    private val dataSource: TableRemoteDataSource,
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        if (modelClass.isAssignableFrom(TableDetailViewModel::class.java)) {
            return TableDetailViewModel(
                handle = handle,
                tableRepository = TableRepositoryImpl(dataSource),
                zoneRepository = ZoneRepositoryImpl(
                    // LOOSE
                    zoneDataSource = ZoneRemoteDataSourceImpl(),
                    tableDataSource = TableRemoteDataSourceImpl()
                )
            ) as T
        } else if (modelClass.isAssignableFrom(TableListViewModel::class.java)) {
            return TableListViewModel(
                tableRepository = TableRepositoryImpl(dataSource)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}