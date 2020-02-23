package fit.tdc.edu.vn.cafemanagement.data.viewmodel.zone

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.table.TableRemoteDataSourceImpl
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.zone.ZoneRemoteDataSource
import fit.tdc.edu.vn.cafemanagement.data.repository.zone.ZoneRepositoryImpl

class ZoneViewModelFactory(
    private val dataSource: ZoneRemoteDataSource,
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        if (modelClass.isAssignableFrom(ZoneDetailViewModel::class.java)) {
            return ZoneDetailViewModel(
                handle = handle,
                zoneRepository = ZoneRepositoryImpl(
                    // LOOSE
                    zoneDataSource = dataSource,
                    tableDataSource = TableRemoteDataSourceImpl()
                )
            ) as T
        }
        else if (modelClass.isAssignableFrom(ZoneListViewModel::class.java)) {
            return ZoneListViewModel(
                zoneRepository = ZoneRepositoryImpl(
                    // LOOSE
                    zoneDataSource = dataSource,
                    tableDataSource = TableRemoteDataSourceImpl()
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}