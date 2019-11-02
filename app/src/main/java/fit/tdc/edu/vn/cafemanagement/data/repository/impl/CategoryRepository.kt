package fit.tdc.edu.vn.cafemanagement.data.repository.impl

import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.FireBaseAPI
import fit.tdc.edu.vn.cafemanagement.data.extension.DocumentType
import fit.tdc.edu.vn.cafemanagement.data.model.category.Category
import fit.tdc.edu.vn.cafemanagement.data.repository.CategoryRepositoryAPI

class CategoryRepository(val dataSource: FireBaseAPI) :
    CategoryRepositoryAPI {

    override fun getAllCategories() = dataSource.getCategoryList("EfzspceETNgWk56YDOOt", DocumentType.ALL)

    override fun getCategory(id: String) = dataSource.getCategory("EfzspceETNgWk56YDOOt", id,DocumentType.SINGLE)

    override fun insert(category: Category) =
        dataSource.createCategory("EfzspceETNgWk56YDOOt", category)

    override fun update(category: Category) =
        dataSource.modifyCategory("EfzspceETNgWk56YDOOt", category)

    override fun delete(category: Category) =
        dataSource.deleteCategory("EfzspceETNgWk56YDOOt", category.id)
}