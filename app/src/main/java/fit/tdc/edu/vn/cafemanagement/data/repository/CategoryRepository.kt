package fit.tdc.edu.vn.cafemanagement.data.repository

import fit.tdc.edu.vn.cafemanagement.data.data_source.FireBaseAPI
import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.Category

class CategoryRepository(val dataSource: FireBaseAPI) {

    fun getAllCategory() = dataSource.getCategoryList("EfzspceETNgWk56YDOOt")

    fun getCategory(id: String) = dataSource.getCategory("EfzspceETNgWk56YDOOt", id)

    fun insert(category: Category) {
        dataSource.createCategory("EfzspceETNgWk56YDOOt", category)
    }

    fun update(category: Category) {
        //TODO: get update function
    }

    fun delete(category: Category) {
        dataSource.deleteCategory("EfzspceETNgWk56YDOOt", category.id!!)
    }

    fun deleteAllUnits() {
        //TODO: get delete all function
    }
}