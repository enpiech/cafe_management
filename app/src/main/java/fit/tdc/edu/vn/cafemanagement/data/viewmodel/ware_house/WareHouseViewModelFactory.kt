package fit.tdc.edu.vn.cafemanagement.data.viewmodel.ware_house

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.FireBaseAPI
import fit.tdc.edu.vn.cafemanagement.data.repository.impl.*

class WareHouseViewModelFactory(
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
        if (modelClass.isAssignableFrom(WareHouseDetailViewModel::class.java)) {
            return WareHouseDetailViewModel(
                handle = handle,
                wareHouseRepository = WareHouseRepository(
                    dataSource = dataSource
                ),
                unitRepository = UnitRepository(
                    dataSource = dataSource
                ),
                materialRepository = MaterialRepository(
                    dataSource = dataSource
                )
            ) as T
        } else if (modelClass.isAssignableFrom(WareHouseListViewModel::class.java)) {
            return WareHouseListViewModel(
                wareHouseRepository = WareHouseRepository(
                    dataSource = dataSource
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
