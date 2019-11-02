package fit.tdc.edu.vn.cafemanagement.data.viewmodel.material

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.map
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.extension.CombinedLiveData
import fit.tdc.edu.vn.cafemanagement.data.extension.Status
import fit.tdc.edu.vn.cafemanagement.data.model.category.Category
import fit.tdc.edu.vn.cafemanagement.data.model.isNameValid
import fit.tdc.edu.vn.cafemanagement.data.model.material.Material
import fit.tdc.edu.vn.cafemanagement.data.model.material.MaterialViewFormState
import fit.tdc.edu.vn.cafemanagement.data.model.unit.Unit
import fit.tdc.edu.vn.cafemanagement.data.repository.CategoryRepositoryAPI
import fit.tdc.edu.vn.cafemanagement.data.repository.MaterialRepositoryAPI
import fit.tdc.edu.vn.cafemanagement.data.repository.UnitRepositoryAPI
import fit.tdc.edu.vn.cafemanagement.fragment.BaseDetailViewModel

class MaterialDetailViewModel(
    private val handle: SavedStateHandle,
    private val materialRepository: MaterialRepositoryAPI,
    private val unitRepository: UnitRepositoryAPI,
    private val categoryRepository: CategoryRepositoryAPI
): BaseDetailViewModel<Material>() {

    override var saved: Material
        get() = Material(
            name = handle.get("name"),
            price = handle.get("price"),
            unitId = handle.get("unitId"),
            categoryId = handle.get("categoryId")
            // sellable = handle.get("sellable")
        )
        set(value) {
            handle.set("name", value.name)
            handle.set("price", value.price)
            handle.set("unitId", value.unitId)
            handle.set("categoryId", value.categoryId)
            // handle.set("sellable", value.sellable)
        }

    private val _unitReponseList = unitRepository.getAllUnits()
    val unitList: LiveData<List<Unit>> = _unitReponseList.map {
        if (it.status == Status.SUCCESS && it.data != null) {
            it.data
        } else {
            listOf()
        }
    }

    val currentUnit =
        CombinedLiveData<Material, List<Unit>, Unit?>(currentItem, unitList) { unit, unitList ->
            getCurrentUnitOfMaterial(unit, unitList)
        }

    private fun getCurrentUnitOfMaterial(material: Material?, res: List<Unit>?): Unit? {
        if (!res.isNullOrEmpty() && material != null) {
            return res.find { unit -> unit.id == material.unitId }
        }
        return null
    }

    private val _categoryReponseList = categoryRepository.getAllCategories()
    val categoryList: LiveData<List<Category>> = _categoryReponseList.map {
        if (it.status == Status.SUCCESS && it.data != null) {
            it.data
        } else {
            listOf()
        }
    }

    val currentCategory =
        CombinedLiveData<Material, List<Category>, Category?>(currentItem, categoryList) { category, categoryList ->
            getCurrentCategoryOfMaterial(category, categoryList)
        }

    private fun getCurrentCategoryOfMaterial(material: Material?, res: List<Category>?): Category? {
        if (!res.isNullOrEmpty() && material != null) {
            return res.find { category -> category.id == material.categoryId }
        }
        return null
    }

    override fun getItem(id: String) = materialRepository.getMaterial(id)

    override fun insert(item: Material) = materialRepository.insert(item)

    override fun update(item: Material) = materialRepository.update(item)

    override fun validate(item: Material?) {
        when {
            item == null -> {
                _formState.value = MaterialViewFormState(
                    nameError = null
                ).apply {
                    isChanged = false
                    isDataValid = true
                }
            }
            item == currentItem.value -> {
                _formState.value = MaterialViewFormState(
                    nameError = null
                ).apply {
                    isChanged = false
                    isDataValid = false
                }
            }
            !isNameValid(item.name) -> {
                _formState.value = MaterialViewFormState(
                    nameError = R.string.invalid_material_name
                ).apply {
                    isChanged = false
                    isDataValid = false
                }
            }
            isNameValid(item.name) && item != currentItem.value -> {
                _formState.value = MaterialViewFormState(
                    nameError = null
                ).apply {
                    isChanged = true
                    isDataValid = true
                }
            }
        }
    }
}