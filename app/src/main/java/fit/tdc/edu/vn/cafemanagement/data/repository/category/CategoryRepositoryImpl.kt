package fit.tdc.edu.vn.cafemanagement.data.repository.category

import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.category.CategoryRemoteDataSource
import fit.tdc.edu.vn.cafemanagement.data.extension.DocumentType
import fit.tdc.edu.vn.cafemanagement.data.model.category.Category
import fit.tdc.edu.vn.cafemanagement.data.model.user.UserInfor

class CategoryRepositoryImpl(
    private val dataSource: CategoryRemoteDataSource
) : CategoryRepository {

    override fun getAllCategories() =
        dataSource.readCategories(UserInfor.getInstance().storeId!!, DocumentType.ALL)

    override fun getCategory(id: String) =
        dataSource.readCategory(UserInfor.getInstance().storeId!!, id, DocumentType.SINGLE)

    override fun insert(category: Category) =
        dataSource.createCategory(UserInfor.getInstance().storeId!!, category)

    override fun update(category: Category) =
        dataSource.updateCategory(UserInfor.getInstance().storeId!!, category)

    override fun delete(category: Category) =
        dataSource.deleteCategory(UserInfor.getInstance().storeId!!, category.id)
}