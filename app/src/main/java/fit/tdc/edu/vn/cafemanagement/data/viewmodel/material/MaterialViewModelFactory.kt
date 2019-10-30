package fit.tdc.edu.vn.cafemanagement.data.viewmodel.material

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.FireBaseDataSource
import fit.tdc.edu.vn.cafemanagement.data.repository.impl.MaterialRepository

class MaterialViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MaterialViewModel::class.java)) {
            return MaterialViewModel(
                 materialRepository = MaterialRepository(
                     dataSource = FireBaseDataSource()
                 )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}