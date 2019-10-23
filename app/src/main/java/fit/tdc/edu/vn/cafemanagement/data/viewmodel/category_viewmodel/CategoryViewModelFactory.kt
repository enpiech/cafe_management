package fit.tdc.edu.vn.cafemanagement.data.viewmodel.category_viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.FireBaseDataSource
import fit.tdc.edu.vn.cafemanagement.data.repository.impl.CategoryRepository

class CategoryViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CategoryListViewModel::class.java)) {
            return CategoryListViewModel(
                categoryRepository = CategoryRepository(
                    dataSource = FireBaseDataSource()
                )
            ) as T
        }
        else if(modelClass.isAssignableFrom(CategoryDetailViewModel::class.java)) {
            return CategoryDetailViewModel(
                categoryRepository = CategoryRepository(
                    dataSource = FireBaseDataSource()
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}