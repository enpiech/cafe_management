package fit.tdc.edu.vn.cafemanagement.data.repository.impl

import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.FireBaseAPI
import fit.tdc.edu.vn.cafemanagement.data.extension.DocumentType
import fit.tdc.edu.vn.cafemanagement.data.model.category.Category
import fit.tdc.edu.vn.cafemanagement.data.model.user.UserInfor
import fit.tdc.edu.vn.cafemanagement.data.repository.CategoryRepositoryAPI

class CategoryRepository(val dataSource: FireBaseAPI) :
    CategoryRepositoryAPI {

    override fun getAllCategories() = dataSource.getCategoryList(UserInfor.getInstance().storeId!!, DocumentType.ALL)

    override fun getCategory(id: String) = dataSource.getCategory(UserInfor.getInstance().storeId!!, id,DocumentType.SINGLE)

    override fun insert(category: Category) =
        dataSource.createCategory(UserInfor.getInstance().storeId!!, category)

    override fun update(category: Category) =
        dataSource.modifyCategory(UserInfor.getInstance().storeId!!, category)

    override fun delete(category: Category) =
        dataSource.deleteCategory(UserInfor.getInstance().storeId!!, category.id)
}