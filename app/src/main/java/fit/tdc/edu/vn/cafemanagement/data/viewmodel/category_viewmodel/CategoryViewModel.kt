package fit.tdc.edu.vn.cafemanagement.data.viewmodel.category_viewmodel

import androidx.lifecycle.LiveData
import fit.tdc.edu.vn.cafemanagement.data.model.Category
import fit.tdc.edu.vn.cafemanagement.data.repository.CategoryRepository

class CategoryViewModel (private val categoryRepository:CategoryRepository) {

    private var allCategories: LiveData<ArrayList<Category>> = categoryRepository.getAllCategory()

    fun getCategory(id: String) : LiveData<Category?> {
        return categoryRepository.getCategory(id)
    }

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
        categoryRepository.deleteAllUnits()
    }

    fun getAllCategories(): LiveData<ArrayList<Category>> {
        return allCategories
    }

}