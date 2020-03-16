package fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.category

import com.google.firebase.firestore.DocumentReference
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.FirebaseRemoteDataSource
import fit.tdc.edu.vn.cafemanagement.data.extension.CollectionLiveData
import fit.tdc.edu.vn.cafemanagement.data.extension.DocumentLiveData
import fit.tdc.edu.vn.cafemanagement.data.extension.DocumentType
import fit.tdc.edu.vn.cafemanagement.data.extension.TaskLiveData
import fit.tdc.edu.vn.cafemanagement.data.model.category.Category

interface CategoryRemoteDataSource : FirebaseRemoteDataSource {
    fun readCategories(
        storeId: String,
        documentType: DocumentType
    ): CollectionLiveData<Category>

    fun readCategory(
        storeId: String,
        categoryId: String,
        documentType: DocumentType
    ): DocumentLiveData<Category>

    fun createCategory(
        storeId: String,
        category: Category
    ): TaskLiveData<DocumentReference>

    fun updateCategory(
        storeId: String,
        category: Category
    ): TaskLiveData<Void>

    fun deleteCategory(
        storeId: String,
        categoryId: String
    ): TaskLiveData<Void>
}