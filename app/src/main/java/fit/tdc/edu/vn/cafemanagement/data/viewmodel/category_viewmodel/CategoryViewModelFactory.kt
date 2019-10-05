package fit.tdc.edu.vn.cafemanagement.data.viewmodel.category_viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.firestore.FirebaseFirestore
import fit.tdc.edu.vn.cafemanagement.data.data_source.FireBaseDataSource
import fit.tdc.edu.vn.cafemanagement.data.repository.CategoryRepository
import fit.tdc.edu.vn.cafemanagement.data.repository.UnitRepository
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.unit_viewmodel.UnitViewModel
import fit.tdc.edu.vn.cafemanagement.ui.login.LoginViewModel

class CategoryViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CategoryViewModel::class.java)) {
            return CategoryViewModel(
                categoryRepository = CategoryRepository(
                    dataSource = FireBaseDataSource()
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}