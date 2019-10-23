package fit.tdc.edu.vn.cafemanagement.data.viewmodel.category_viewmodel

import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.model.category.Category
import fit.tdc.edu.vn.cafemanagement.data.model.category.CategoryViewFormState
import fit.tdc.edu.vn.cafemanagement.data.model.isNameValid
import fit.tdc.edu.vn.cafemanagement.data.repository.CategoryRepositoryAPI
import fit.tdc.edu.vn.cafemanagement.fragment.BaseViewViewModel

class CategoryViewModel(
    private val categoryRepository: CategoryRepositoryAPI
): BaseViewViewModel<Category>() {

    override fun getAllItems() = categoryRepository.getAllCategories()

    override fun getItem(id: String) = categoryRepository.getCategory(id)

    override fun insert(item: Category) = categoryRepository.insert(item)

    override fun update(item: Category) = categoryRepository.update(item)

    override fun delete(item: Category) = categoryRepository.delete(item)

    override fun dataChange(item: Category) {
        when {
            item == currentItem.value -> {
                _formState.value = CategoryViewFormState(
                    nameError = null
                ).apply {
                    isChanged = false
                    isDataValid = true
                }
            }
            !isNameValid(item.name) -> {
                _formState.value = CategoryViewFormState(
                    nameError = R.string.invalid_category_name
                ).apply {
                    isChanged = false
                    isDataValid = false
                }
            }
            isNameValid(item.name) && item != currentItem.value -> {
                _formState.value = CategoryViewFormState(
                    nameError = null
                ).apply {
                    isChanged = true
                    isDataValid = true
                }
            }
        }
    }
}