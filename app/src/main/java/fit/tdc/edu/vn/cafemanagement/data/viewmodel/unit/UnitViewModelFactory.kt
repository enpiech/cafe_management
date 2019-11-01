package fit.tdc.edu.vn.cafemanagement.data.viewmodel.unit

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.FireBaseAPI
import fit.tdc.edu.vn.cafemanagement.data.repository.impl.UnitRepository

class UnitViewModelFactory(
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
        if (modelClass.isAssignableFrom(UnitListViewModel::class.java)) {
            return UnitListViewModel(
                unitRepository = UnitRepository()
            ) as T
        } else if (modelClass.isAssignableFrom(UnitDetailViewModel::class.java)) {
            return UnitDetailViewModel(
                handle = handle,
                itemRepository = UnitRepository()
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
