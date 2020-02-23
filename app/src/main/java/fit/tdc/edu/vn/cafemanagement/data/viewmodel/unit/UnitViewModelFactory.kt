package fit.tdc.edu.vn.cafemanagement.data.viewmodel.unit

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.material.MaterialRemoteDataSourceImpl
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.unit.UnitRemoteDataSource
import fit.tdc.edu.vn.cafemanagement.data.repository.unit.UnitRepositoryImpl

class UnitViewModelFactory(
    private val dataSource: UnitRemoteDataSource,
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        if (modelClass.isAssignableFrom(UnitListViewModel::class.java)) {
            return UnitListViewModel(
                unitRepository = UnitRepositoryImpl(
                    // LOOSE
                    unitDataSource = dataSource,
                    materialDataSource = MaterialRemoteDataSourceImpl()
                )
            ) as T
        } else if (modelClass.isAssignableFrom(UnitDetailViewModel::class.java)) {
            return UnitDetailViewModel(
                handle = handle,
                unitRepository = UnitRepositoryImpl(
                    // LOOSE
                    unitDataSource = dataSource,
                    materialDataSource = MaterialRemoteDataSourceImpl()
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
