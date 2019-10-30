package fit.tdc.edu.vn.cafemanagement.data.viewmodel.table_order_waiter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.FireBaseDataSource
import fit.tdc.edu.vn.cafemanagement.data.repository.impl.CategoryRepository

class TableOrderWaiterViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TableOrderWaiterDetailViewModel::class.java)) {
            return TableOrderWaiterDetailViewModel(
                 categoryRepository = CategoryRepository(
                     dataSource = FireBaseDataSource()
                 )
            ) as T
        } else if (modelClass.isAssignableFrom(TableOrderWaiterListViewModel::class.java)) {
            return TableOrderWaiterListViewModel(
                categoryRepository = CategoryRepository(
                    dataSource = FireBaseDataSource()
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}