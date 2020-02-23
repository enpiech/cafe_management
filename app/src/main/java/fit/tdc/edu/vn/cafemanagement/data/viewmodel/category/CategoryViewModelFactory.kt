package fit.tdc.edu.vn.cafemanagement.data.viewmodel.category

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.category.CategoryRemoteDataSource
import fit.tdc.edu.vn.cafemanagement.data.repository.category.CategoryRepositoryImpl

class CategoryViewModelFactory (
    private val dataSource: CategoryRemoteDataSource,
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {
    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        if (modelClass.isAssignableFrom(CategoryListViewModel::class.java)) {
            return CategoryListViewModel(
                categoryRepository = CategoryRepositoryImpl(dataSource)
            ) as T
        }
        else if(modelClass.isAssignableFrom(CategoryDetailViewModel::class.java)) {
            return CategoryDetailViewModel(
                handle = handle,
                categoryRepository = CategoryRepositoryImpl(dataSource)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}