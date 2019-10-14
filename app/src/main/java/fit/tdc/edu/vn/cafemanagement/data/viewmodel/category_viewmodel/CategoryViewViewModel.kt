package fit.tdc.edu.vn.cafemanagement.data.viewmodel.category_viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.Category
import fit.tdc.edu.vn.cafemanagement.data.repository.CategoryRepositoryAPI

class CategoryViewViewModel(private val categoryRepository: CategoryRepositoryAPI) : ViewModel() {
    private val _category = MutableLiveData<Category>()

    var category: LiveData<Category?> = _category

    fun insert(category: Category) {
        categoryRepository.insert(category)
    }

    fun update(category: Category) {
        categoryRepository.update(category)
    }

    fun getCategory(id: String) {
        category = categoryRepository.getCategory(id).map {
            it.data
        }
    }
}