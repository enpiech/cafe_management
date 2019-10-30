package fit.tdc.edu.vn.cafemanagement.data.viewmodel.category

import fit.tdc.edu.vn.cafemanagement.data.model.category.Category
import fit.tdc.edu.vn.cafemanagement.data.repository.CategoryRepositoryAPI
import fit.tdc.edu.vn.cafemanagement.fragment.BaseListViewModel

class CategoryListViewModel(
    private val categoryRepository: CategoryRepositoryAPI
): BaseListViewModel<Category>() {

    override fun getAllItems() = categoryRepository.getAllCategories()

    override fun delete(item: Category) = categoryRepository.delete(item)
}