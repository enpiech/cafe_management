package fit.tdc.edu.vn.cafemanagement.data.viewmodel.category_viewmodel

import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.model.category.Category
import fit.tdc.edu.vn.cafemanagement.data.model.category.CategoryViewFormState
import fit.tdc.edu.vn.cafemanagement.data.model.isNameValid
import fit.tdc.edu.vn.cafemanagement.data.repository.CategoryRepositoryAPI
import fit.tdc.edu.vn.cafemanagement.fragment.BaseDetailViewModel

class CategoryDetailViewModel(
    private val categoryRepository: CategoryRepositoryAPI
) : BaseDetailViewModel<Category>() {
    override fun getItem(id: String) = categoryRepository.getCategory(id)

    override fun insert(item: Category) = categoryRepository.insert(item)

    override fun update(item: Category) = categoryRepository.update(item)

    override fun dataChange(item: Category?) {
        when {
            item == null -> {
                _formState.value = CategoryViewFormState(
                    nameError = null
                ).apply {
                    isChanged = false
                    isDataValid = true
                }
            }
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