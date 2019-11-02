package fit.tdc.edu.vn.cafemanagement.data.viewmodel.material

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.savedstate.SavedStateRegistryOwner
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.FireBaseAPI
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.FireBaseDataSource
import fit.tdc.edu.vn.cafemanagement.data.repository.impl.CategoryRepository
import fit.tdc.edu.vn.cafemanagement.data.repository.impl.MaterialRepository
import fit.tdc.edu.vn.cafemanagement.data.repository.impl.UnitRepository

class MaterialViewModelFactory(
    private val dataSource: FireBaseAPI,
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        if (modelClass.isAssignableFrom(MaterialListViewModel::class.java)) {
            return MaterialListViewModel(
                materialRepository = MaterialRepository(
                    dataSource = FireBaseDataSource()
                )
            ) as T
        } else if (modelClass.isAssignableFrom(MaterialDetailViewModel::class.java)) {
            return MaterialDetailViewModel(
                handle = handle,
                materialRepository = MaterialRepository(
                    dataSource = FireBaseDataSource()
                ),
                unitRepository = UnitRepository(
                    dataSource
                ),
                categoryRepository = CategoryRepository(
                    dataSource
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}