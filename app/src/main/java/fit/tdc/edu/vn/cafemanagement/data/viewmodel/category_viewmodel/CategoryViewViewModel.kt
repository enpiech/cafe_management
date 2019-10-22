package fit.tdc.edu.vn.cafemanagement.data.viewmodel.category_viewmodel

import androidx.lifecycle.*
import com.hadilq.liveevent.LiveEvent
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.extension.Status
import fit.tdc.edu.vn.cafemanagement.data.model.FormState
import fit.tdc.edu.vn.cafemanagement.data.model.category.Category
import fit.tdc.edu.vn.cafemanagement.data.model.category.CategoryViewFormState
import fit.tdc.edu.vn.cafemanagement.data.model.isNameValid
import fit.tdc.edu.vn.cafemanagement.data.repository.CategoryRepositoryAPI

class CategoryViewViewModel(
    private val categoryRepository: CategoryRepositoryAPI
) : ViewModel() {

    private var _viewType = MutableLiveData<FormState.Type?>(null)
    val viewType: LiveData<FormState.Type?> = _viewType

    private var _formState = MutableLiveData<CategoryViewFormState>(null)
    val formState: LiveData<CategoryViewFormState> = _formState

    private var _currentCategoryId = LiveEvent<String>()
    val currentCategory = MediatorLiveData<Category?>()

    init {
        with(currentCategory) {
            addSource(
                _currentCategoryId.switchMap {categoryId ->
                    categoryRepository.getCategory(categoryId)
                }
            ) { result ->
                if (result.status == Status.SUCCESS) {
                    currentCategory.value = result.data
                }
            }
        }

    }

    fun setViewType(type: FormState.Type) {
        _viewType.value = type
    }

    private var allCategories = categoryRepository.getAllCategories()

    fun insert(category: Category) = categoryRepository.insert(category)

    fun update(category: Category) = categoryRepository.update(category)

    fun delete(category: Category) = categoryRepository.delete(category)

    fun getAllCategories() = allCategories

    fun getCategory(categoryId: String) {
        _currentCategoryId.value = categoryId
    }

    fun dataChange(category: Category) {
        when {
            category == currentCategory.value -> {
                _formState.value = CategoryViewFormState(
                    nameError = null
                ).apply {
                    isChanged = false
                    isDataValid = true
                }
            }
            !isNameValid(category.name) -> {
                _formState.value = CategoryViewFormState(
                    nameError = R.string.invalid_category_name
                ).apply {
                    isChanged = false
                    isDataValid = false
                }
            }
            isNameValid(category.name) && category != currentCategory.value -> {
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