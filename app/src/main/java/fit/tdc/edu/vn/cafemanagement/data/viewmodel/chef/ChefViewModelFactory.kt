package fit.tdc.edu.vn.cafemanagement.data.viewmodel.chef

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.FireBaseAPI
import fit.tdc.edu.vn.cafemanagement.data.repository.impl.ChefRepository
import fit.tdc.edu.vn.cafemanagement.data.repository.impl.TableRepository
import fit.tdc.edu.vn.cafemanagement.data.repository.impl.ZoneRepository

class ChefViewModelFactory(
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
        if (modelClass.isAssignableFrom(ChefListViewModel::class.java)) {
            return ChefListViewModel(
                chefRepository = ChefRepository(
                    dataSource = dataSource
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}