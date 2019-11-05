package fit.tdc.edu.vn.cafemanagement.data.repository

import com.google.firebase.firestore.DocumentReference
import fit.tdc.edu.vn.cafemanagement.data.extension.CollectionLiveData
import fit.tdc.edu.vn.cafemanagement.data.extension.DocumentLiveData
import fit.tdc.edu.vn.cafemanagement.data.extension.TaskLiveData
import fit.tdc.edu.vn.cafemanagement.data.model.chef.Chef

interface ChefRepositoryAPI {
    fun getAllChefs(): CollectionLiveData<Chef>
    fun getChef(id: String): DocumentLiveData<Chef>
    fun insert(chef: Chef): TaskLiveData<DocumentReference>
    fun update(chef: Chef): TaskLiveData<Void>
    fun delete(chef: Chef): TaskLiveData<Void>
}