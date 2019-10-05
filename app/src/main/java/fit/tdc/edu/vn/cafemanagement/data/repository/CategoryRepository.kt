package fit.tdc.edu.vn.cafemanagement.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import fit.tdc.edu.vn.cafemanagement.data.data_source.FireBaseAPI
import fit.tdc.edu.vn.cafemanagement.data.data_source.FireBaseDataSource
import fit.tdc.edu.vn.cafemanagement.data.extension.CollectionLiveData
import fit.tdc.edu.vn.cafemanagement.data.model.Category
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class CategoryRepository(val dataSource: FireBaseAPI) {

    fun getAllCategory() = dataSource.getCategoryList("EfzspceETNgWk56YDOOt")

    fun getCategory(id: String) : LiveData<Category?> {
        return dataSource.getCategory("EfzspceETNgWk56YDOOt", id)
    }

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