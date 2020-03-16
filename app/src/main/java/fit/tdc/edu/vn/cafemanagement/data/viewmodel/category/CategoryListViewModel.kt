package fit.tdc.edu.vn.cafemanagement.data.viewmodel.category

import fit.tdc.edu.vn.cafemanagement.data.model.category.Category
import fit.tdc.edu.vn.cafemanagement.data.repository.category.CategoryRepository
import fit.tdc.edu.vn.cafemanagement.fragment.BaseListViewModel

class CategoryListViewModel(
    private val categoryRepository: CategoryRepository
): BaseListViewModel<Category>() {

    override fun getAllItems() = categoryRepository.getAllCategories()

    override fun delete(item: Category) = categoryRepository.delete(item)
}