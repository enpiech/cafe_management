package fit.tdc.edu.vn.cafemanagement.data.viewmodel.table_order_waiter

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.FireBaseAPI
import fit.tdc.edu.vn.cafemanagement.data.repository.impl.CategoryRepository

class TableOrderWaiterViewModelFactory(
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
        if(modelClass.isAssignableFrom(TableOrderWaiterListViewModel::class.java)) {
            return TableOrderWaiterListViewModel(
                categoryRepository = CategoryRepository(
                    dataSource = dataSource
                )
            ) as T
        }else if (modelClass.isAssignableFrom(TableOrderWaiterDetailViewModel::class.java)) {
            return TableOrderWaiterDetailViewModel(
                handle = handle,
                categoryRepository = CategoryRepository(
                    dataSource = dataSource
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}