package fit.tdc.edu.vn.cafemanagement.data.repository

import com.google.firebase.firestore.DocumentReference
import fit.tdc.edu.vn.cafemanagement.data.extension.CollectionLiveData
import fit.tdc.edu.vn.cafemanagement.data.extension.DocumentLiveData
import fit.tdc.edu.vn.cafemanagement.data.extension.TaskLiveData
import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.Category

interface CategoryRepositoryAPI {
    fun getAllCategory(): CollectionLiveData<Category>
    fun getCategory(id: String): DocumentLiveData<Category>
    fun insert(category: Category): TaskLiveData<DocumentReference>
    fun update(category: Category)
    fun delete(category: Category): TaskLiveData<Void>
    fun deleteAllCategories()
}