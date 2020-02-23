package fit.tdc.edu.vn.cafemanagement.data.viewmodel.dish

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.category.CategoryRemoteDatasourceImpl
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.material.MaterialRemoteDataSource
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.material.MaterialRemoteDataSourceImpl
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.unit.UnitRemoteDataSourceImpl
import fit.tdc.edu.vn.cafemanagement.data.repository.category.CategoryRepositoryImpl
import fit.tdc.edu.vn.cafemanagement.data.repository.material.MaterialRepositoryImpl
import fit.tdc.edu.vn.cafemanagement.data.repository.unit.UnitRepositoryImpl

class DishViewModelFactory(
    private val dataSource: MaterialRemoteDataSource,
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        if (modelClass.isAssignableFrom(DishListViewModel::class.java)) {
            return DishListViewModel(
                materialRepository = MaterialRepositoryImpl(dataSource)
            ) as T
        } else if (modelClass.isAssignableFrom(DishDetailViewModel::class.java)) {
            return DishDetailViewModel(
                handle = handle,
                materialRepository = MaterialRepositoryImpl(
                    dataSource
                ),
                // LOOSE
                unitRepository = UnitRepositoryImpl(
                    unitDataSource = UnitRemoteDataSourceImpl(),
                    materialDataSource = MaterialRemoteDataSourceImpl()
                ),
                // LOOSE
                categoryRepository = CategoryRepositoryImpl(
                    dataSource = CategoryRemoteDatasourceImpl()
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}