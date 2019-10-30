package fit.tdc.edu.vn.cafemanagement.data.viewmodel.table_order_waiter

import fit.tdc.edu.vn.cafemanagement.data.model.category.Category
import fit.tdc.edu.vn.cafemanagement.data.repository.CategoryRepositoryAPI
import fit.tdc.edu.vn.cafemanagement.fragment.BaseListViewModel

class TableOrderWaiterListViewModel(
    private val categoryRepository: CategoryRepositoryAPI
) : BaseListViewModel<Category>() {
    override fun getAllItems() = categoryRepository.getAllCategories()

    override fun delete(item: Category) = categoryRepository.delete(item)

}