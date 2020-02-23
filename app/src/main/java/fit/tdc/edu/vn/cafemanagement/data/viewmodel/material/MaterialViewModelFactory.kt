package fit.tdc.edu.vn.cafemanagement.data.viewmodel.material

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.category.CategoryRemoteDatasourceImpl
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.material.MaterialRemoteDataSource
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.unit.UnitRemoteDataSourceImpl
import fit.tdc.edu.vn.cafemanagement.data.repository.category.CategoryRepositoryImpl
import fit.tdc.edu.vn.cafemanagement.data.repository.material.MaterialRepositoryImpl
import fit.tdc.edu.vn.cafemanagement.data.repository.unit.UnitRepositoryImpl
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.dish.DishDetailViewModel
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.dish.DishListViewModel

class MaterialViewModelFactory(
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
                materialRepository = MaterialRepositoryImpl(dataSource),
                unitRepository = UnitRepositoryImpl(
                    // LOOSE
                    unitDataSource = UnitRemoteDataSourceImpl(),
                    materialDataSource = dataSource
                ),
                categoryRepository = CategoryRepositoryImpl(
                    // LOOSE
                    dataSource = CategoryRemoteDatasourceImpl()
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}