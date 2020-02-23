package fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.user

import com.google.firebase.firestore.DocumentReference
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.FirebaseRemoteDataSource
import fit.tdc.edu.vn.cafemanagement.data.extension.CollectionLiveData
import fit.tdc.edu.vn.cafemanagement.data.extension.DocumentLiveData
import fit.tdc.edu.vn.cafemanagement.data.extension.DocumentType
import fit.tdc.edu.vn.cafemanagement.data.extension.TaskLiveData
import fit.tdc.edu.vn.cafemanagement.data.model.user.User

interface UserRemoteDataSource : FirebaseRemoteDataSource {
    fun readUsers(
        documentType: DocumentType
    ): CollectionLiveData<User>

    fun readUser(
        userId: String,
        documentType: DocumentType
    ): DocumentLiveData<User>

    fun createUser(
        user: User
    ): TaskLiveData<DocumentReference>

    fun updateUser(
        oldUser: User,
        newUser: User
    ): TaskLiveData<Void>

    fun deleteUser(
        user: User
    ): TaskLiveData<Void>
}