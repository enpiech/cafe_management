package fit.tdc.edu.vn.cafemanagement.data.viewmodel.category_viewmodel

import androidx.lifecycle.ViewModel
import fit.tdc.edu.vn.cafemanagement.data.extension.CollectionLiveData
import fit.tdc.edu.vn.cafemanagement.data.model.category.Category
import fit.tdc.edu.vn.cafemanagement.data.repository.CategoryRepositoryAPI

class CategoryViewModel(private val categoryRepository: CategoryRepositoryAPI): ViewModel() {

    private var allCategories: CollectionLiveData<Category> = categoryRepository.getAllCategory()

    fun insert(category: Category) {
        categoryRepository.insert(category)
    }

    fun update(category: Category) {
        categoryRepository.update(category)
    }

    fun delete(category: Category) {
        categoryRepository.delete(category)
    }

    fun getAllCategories(): CollectionLiveData<Category> {
        return allCategories
    }
}