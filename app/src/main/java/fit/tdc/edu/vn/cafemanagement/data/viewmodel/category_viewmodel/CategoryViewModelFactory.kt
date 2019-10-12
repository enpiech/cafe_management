package fit.tdc.edu.vn.cafemanagement.data.viewmodel.category_viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.FireBaseDataSource
import fit.tdc.edu.vn.cafemanagement.data.repository.impl.CategoryRepository
import fit.tdc.edu.vn.cafemanagement.ui.category_list.CategoryViewModel
import fit.tdc.edu.vn.cafemanagement.ui.category_view.CategoryViewViewModel

class CategoryViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CategoryViewModel::class.java)) {
            return CategoryViewModel(
                categoryRepository = CategoryRepository(
                    dataSource = FireBaseDataSource()
                )
            ) as T
        }else if(modelClass.isAssignableFrom(CategoryViewViewModel::class.java)) {
            return CategoryViewViewModel(
                categoryRepository = CategoryRepository(
                    dataSource = FireBaseDataSource()
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}