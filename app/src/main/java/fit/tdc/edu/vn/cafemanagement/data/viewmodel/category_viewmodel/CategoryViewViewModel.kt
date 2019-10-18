package fit.tdc.edu.vn.cafemanagement.data.viewmodel.category_viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.extension.FirestoreResource
import fit.tdc.edu.vn.cafemanagement.data.model.FormState
import fit.tdc.edu.vn.cafemanagement.data.model.category.Category
import fit.tdc.edu.vn.cafemanagement.data.model.category.CategoryViewFormState
import fit.tdc.edu.vn.cafemanagement.data.model.isNameValid
import fit.tdc.edu.vn.cafemanagement.data.repository.CategoryRepositoryAPI

class CategoryViewViewModel(
    private val categoryRepository: CategoryRepositoryAPI
) : ViewModel() {

    private var _viewState = MutableLiveData<FormState.Type?>(null)
    val viewState: LiveData<FormState.Type?> = _viewState

    private var _formState = MutableLiveData<CategoryViewFormState>()
    val formState: LiveData<CategoryViewFormState> = _formState

    private var _currentCategoryId = MutableLiveData<String>()

    val currentCategory: LiveData<FirestoreResource<Category>> = _currentCategoryId.switchMap {
        categoryRepository.getCategory(it)
    }

    fun setViewType(type: FormState.Type) {
        _viewState.value = type
    }


    private var allCategorys = categoryRepository.getAllCategory()

    fun insert(category: Category) = categoryRepository.insert(category)

    fun update(category: Category) = categoryRepository.update(category)

    fun delete(category: Category) = categoryRepository.delete(category)

    fun getAllUnits() = allCategorys

    fun getCategory(categoryId: String) {
        _currentCategoryId.value = categoryId
    }

    fun dataChange(category: Category?) {
        when {
            !isNameValid(category?.name) -> _formState.value =
                CategoryViewFormState(nameError = R.string.invalid_category_name)
            else -> _formState.value = CategoryViewFormState().also { it.isDataValid = true }
        }
    }
}