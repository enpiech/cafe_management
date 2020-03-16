package fit.tdc.edu.vn.cafemanagement.data.viewmodel.dish

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.map
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.extension.Status
import fit.tdc.edu.vn.cafemanagement.data.model.category.Category
import fit.tdc.edu.vn.cafemanagement.data.model.material.Material
import fit.tdc.edu.vn.cafemanagement.data.model.material.MaterialViewFormState
import fit.tdc.edu.vn.cafemanagement.data.model.unit.Unit
import fit.tdc.edu.vn.cafemanagement.data.repository.category.CategoryRepository
import fit.tdc.edu.vn.cafemanagement.data.repository.material.MaterialRepository
import fit.tdc.edu.vn.cafemanagement.data.repository.unit.UnitRepository
import fit.tdc.edu.vn.cafemanagement.fragment.BaseDetailViewModel
import fit.tdc.edu.vn.cafemanagement.util.isValidMaterialName

class DishDetailViewModel(
    private val handle: SavedStateHandle,
    private val materialRepository: MaterialRepository,
    unitRepository: UnitRepository,
    categoryRepository: CategoryRepository
) : BaseDetailViewModel<Material>() {

    override var saved: Material
        get() = Material(
            name = handle.get<String>("name"),
            price = handle.get<Long>("price") ?: 0,
            unitId = handle.get<String>("unitId"),
            unitName = handle.get<String>("unitName"),
            categoryId = handle.get<String>("categoryId"),
            categoryName = handle.get<String>("categoryName"),
            type = handle.get<Material.Type>("type") ?: Material.Type.INGREDIENT
        )
        set(value) {
            handle.set("name", value.name)
            handle.set("price", value.price)
            handle.set("unitId", value.unitId)
            handle.set("unitName", value.unitName)
            handle.set("categoryId", value.categoryId)
            handle.set("categoryName", value.categoryName)
            handle.set("type", value.type)
        }

    private val _unitResponseList = unitRepository.getAllUnits()
    val unitList: LiveData<List<Unit>> = _unitResponseList.map {
        if (it.status == Status.SUCCESS && it.data != null) {
            it.data
        } else {
            listOf()
        }
    }

    private val _categoryResponseList = categoryRepository.getAllCategories()
    val categoryList: LiveData<List<Category>> = _categoryResponseList.map {
        if (it.status == Status.SUCCESS && it.data != null) {
            it.data
        } else {
            listOf()
        }
    }

    override fun getItem(id: String) = materialRepository.getMaterial(id)

    override fun insert(item: Material) = materialRepository.insert(item)

    override fun update(item: Material) = materialRepository.update(item)

    override fun validate(item: Material?) {
        when (item) {
            null -> {
                _formState.value = MaterialViewFormState(
                    nameError = null
                ).apply {
                    isChanged = false
                    isDataValid = true
                }
                return
            }
            currentItem.value -> {
                _formState.value = MaterialViewFormState(
                    nameError = null
                ).apply {
                    isChanged = false
                    isDataValid = false
                }
            }
        }
        // SavedStateHandle
        saved = item!!

        if (_formState.value == null) {
            _formState.value = MaterialViewFormState()
        }
        val formState = _formState.value as MaterialViewFormState
        var noError = true
        if (item.name != null && !item.name.isValidMaterialName()) {
            formState.nameError = R.string.invalid_material_name
            noError = false
        } else {
            formState.nameError = null
        }

        //TODO(Validate price)
//        if (item.price != null /* && !item.price.isValidMaterialPrice()*/) {
//            formState.nameError = R.string.invalid_user_name
//            noError = false
//        } else {
//            formState.nameError = null
//        }

        if (currentItem.value != null) {
            when {
                item.name != currentItem.value!!.name -> formState.isChanged = true
                item.price != currentItem.value!!.price -> formState.isChanged = true
                item.unitId != currentItem.value!!.unitId && item.unitName != currentItem.value!!.unitName -> formState.isChanged =
                    true
                item.type != currentItem.value!!.type -> formState.isChanged = true
                item.categoryId != currentItem.value!!.categoryId && item.categoryName != currentItem.value!!.categoryName -> formState.isChanged =
                    true
            }
        } else {
            formState.isChanged = true
        }

        formState.isDataValid = noError
        _formState.value = formState
    }
}