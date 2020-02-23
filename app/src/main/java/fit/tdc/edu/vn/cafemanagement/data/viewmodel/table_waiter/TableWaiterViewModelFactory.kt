package fit.tdc.edu.vn.cafemanagement.data.viewmodel.table_waiter

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.table.TableRemoteDataSource
import fit.tdc.edu.vn.cafemanagement.data.repository.table.TableRepositoryImpl

class TableWaiterViewModelFactory(
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
        if (modelClass.isAssignableFrom(TableWaiterDetailViewModel::class.java)) {
            return TableWaiterDetailViewModel(
                handle = handle,
                tableRepository = TableRepositoryImpl(dataSource)
            ) as T
        } else if (modelClass.isAssignableFrom(TableWaiterListViewModel::class.java)) {
            return TableWaiterListViewModel(
                tableRepository = TableRepositoryImpl(dataSource)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}