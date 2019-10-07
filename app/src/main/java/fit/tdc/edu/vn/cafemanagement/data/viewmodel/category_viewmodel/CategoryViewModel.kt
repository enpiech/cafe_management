package fit.tdc.edu.vn.cafemanagement.data.viewmodel.category_viewmodel

import fit.tdc.edu.vn.cafemanagement.data.extension.CollectionLiveData
import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.Category
import fit.tdc.edu.vn.cafemanagement.data.repository.CategoryRepositoryAPI

class CategoryViewModel (private val categoryRepository: CategoryRepositoryAPI) {

    private var allCategories: CollectionLiveData<Category> = categoryRepository.getAllCategory()

    fun getCategory(id: String) = categoryRepository.getCategory(id)

    fun insert(category: Category) {
        categoryRepository.insert(category)
    }

    fun update(category: Category) {
        categoryRepository.update(category)
    }

    fun delete(category: Category) {
        categoryRepository.delete(category)
    }

    fun deleteAllCategories() {
        categoryRepository.deleteAllCategories()
    }

    fun getAllCategories(): CollectionLiveData<Category> {
        return allCategories
    }

}