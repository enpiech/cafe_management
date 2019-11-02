package fit.tdc.edu.vn.cafemanagement.data.viewmodel.payment

import fit.tdc.edu.vn.cafemanagement.data.model.category.Category
import fit.tdc.edu.vn.cafemanagement.data.repository.CategoryRepositoryAPI
import fit.tdc.edu.vn.cafemanagement.fragment.BaseListViewModel

class PaymentListViewModel(
    private val categoryRepository: CategoryRepositoryAPI
) : BaseListViewModel<Category>() {
    override fun getAllItems() = categoryRepository.getAllCategories()

    override fun delete(item: Category) = categoryRepository.delete(item)

}