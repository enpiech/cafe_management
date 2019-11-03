package fit.tdc.edu.vn.cafemanagement.data.repository

import com.google.firebase.firestore.DocumentReference
import fit.tdc.edu.vn.cafemanagement.data.extension.CollectionLiveData
import fit.tdc.edu.vn.cafemanagement.data.extension.DocumentLiveData
import fit.tdc.edu.vn.cafemanagement.data.extension.TaskLiveData
import fit.tdc.edu.vn.cafemanagement.data.model.user.User

interface UserRepositoryAPI {
    fun getAllUsers(): CollectionLiveData<User>
    fun getUser(id: String): DocumentLiveData<User>
    fun insert(user: User): TaskLiveData<DocumentReference>
    fun update(oldUser: User, newUser: User): TaskLiveData<Void>
    fun delete(user: User): TaskLiveData<Void>
}