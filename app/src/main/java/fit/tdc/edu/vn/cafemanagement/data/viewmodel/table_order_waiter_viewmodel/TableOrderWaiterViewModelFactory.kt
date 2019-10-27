package fit.tdc.edu.vn.cafemanagement.data.viewmodel.table_order_waiter_viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.FireBaseDataSource
import fit.tdc.edu.vn.cafemanagement.data.repository.impl.CategoryRepository
import fit.tdc.edu.vn.cafemanagement.data.repository.impl.TableRepository

class TableOrderWaiterViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TableOrderWaiterViewModel::class.java)) {
            return TableOrderWaiterViewModel(
                 categoryRepository = CategoryRepository(
                     dataSource = FireBaseDataSource()
                 )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}