package fit.tdc.edu.vn.cafemanagement.data.repository.impl

import fit.tdc.edu.vn.cafemanagement.data.data_source.FireBaseAPI
import fit.tdc.edu.vn.cafemanagement.data.extension.DocumentType
import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.Category
import fit.tdc.edu.vn.cafemanagement.data.repository.CategoryRepsitoryAPI

class CategoryRepository(val dataSource: FireBaseAPI) :
    CategoryRepsitoryAPI {

    override fun getAllCategory() = dataSource.getCategoryList("EfzspceETNgWk56YDOOt", DocumentType.ALL)

    override fun getCategory(id: String) = dataSource.getCategory("EfzspceETNgWk56YDOOt", id,DocumentType.SINGLE)

    override fun insert(category: Category) =
        dataSource.createCategory("EfzspceETNgWk56YDOOt", category)

    override fun update(category: Category) {
        //TODO: get update function
    }

    override fun delete(category: Category) =
        dataSource.deleteCategory("EfzspceETNgWk56YDOOt", category.id!!)

    override fun deleteAllCategories() {
        //TODO: get delete all function
    }
}