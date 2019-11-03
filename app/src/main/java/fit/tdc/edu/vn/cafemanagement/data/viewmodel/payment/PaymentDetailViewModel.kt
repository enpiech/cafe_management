package fit.tdc.edu.vn.cafemanagement.data.viewmodel.payment

import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.model.category.Category
import fit.tdc.edu.vn.cafemanagement.data.model.category.CategoryViewFormState
import fit.tdc.edu.vn.cafemanagement.data.model.isNameValid
import fit.tdc.edu.vn.cafemanagement.data.repository.CategoryRepositoryAPI
import fit.tdc.edu.vn.cafemanagement.fragment.BaseDetailViewModel

class PaymentDetailViewModel(
    private val categoryRepository: CategoryRepositoryAPI
) : BaseDetailViewModel<Category>() {
    override var saved: Category
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
        set(value) {}

    override fun getItem(id: String) = categoryRepository.getCategory(id)

    override fun insert(item: Category) = categoryRepository.insert(item)

    override fun update(item: Category) = categoryRepository.update(item)

    override fun validate(item: Category?) {
        when {
            item == currentItem.value -> {
                _formState.value = CategoryViewFormState(
                    nameError = null
                ).apply {
                    isChanged = false
                    isDataValid = true
                }
            }
            !isNameValid(item?.name) -> {
                _formState.value = CategoryViewFormState(
                    nameError = R.string.invalid_table_name
                ).apply {
                    isChanged = false
                    isDataValid = false
                }
            }
            isNameValid(item?.name) && item != currentItem.value -> {
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