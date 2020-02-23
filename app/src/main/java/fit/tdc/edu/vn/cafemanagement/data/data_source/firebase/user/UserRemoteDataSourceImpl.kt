package fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.user

import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import fit.tdc.edu.vn.cafemanagement.data.extension.*
import fit.tdc.edu.vn.cafemanagement.data.model.user.User
import fit.tdc.edu.vn.cafemanagement.util.Constants

class UserRemoteDataSourceImpl : UserRemoteDataSource {
    private val db: FirebaseFirestore by lazy {
        Firebase.firestore
    }

    override fun readUsers(
        documentType: DocumentType
    ): CollectionLiveData<User> =
        db.collection(Constants.USERS_KEY)
            .asLiveData(documentType)

    override fun readUser(
        userId: String,
        documentType: DocumentType
    ): DocumentLiveData<User> =
        db.collection(Constants.USERS_KEY).document(userId)
            .asLiveData()

    override fun createUser(
        user: User
    ): TaskLiveData<DocumentReference> =
        db.collection(Constants.USERS_KEY)
            .add(user)
            .asLiveData()

    override fun updateUser(
        oldUser: User,
        newUser: User
    ): TaskLiveData<Void> {
        var oldStoreRef: DocumentReference? = null
        oldUser.storeId?.let {
            oldStoreRef = db.collection(Constants.STORES_KEY).document(it)
        }
        var newStoreRef: DocumentReference? = null
        newUser.storeId?.let {
            newStoreRef = db.collection(Constants.STORES_KEY).document(it)
        }

        return db.runBatch { batch ->
            if (newUser.role == User.Role.STORE_MANAGER) {
                if (oldUser.storeId.isNullOrBlank() && !newUser.storeId.isNullOrBlank()) {
                    newStoreRef?.let {
                        batch.update(it, Constants.MANAGER_ID_KEY, newUser.id)
                        batch.update(newStoreRef!!, Constants.MANAGER_NAME_KEY, newUser.name)
                    }
                } else if (!oldUser.storeId.isNullOrBlank() && newUser.storeId.isNullOrBlank()) {
                    oldStoreRef?.let {
                        batch.update(it, Constants.MANAGER_ID_KEY, null)
                        batch.update(oldStoreRef!!, Constants.MANAGER_NAME_KEY, null)
                    }
                } else if (!oldUser.storeId.isNullOrBlank() && !newUser.storeId.isNullOrBlank()) {
                    batch.update(oldStoreRef!!, Constants.MANAGER_ID_KEY, null)
                    batch.update(oldStoreRef!!, Constants.MANAGER_NAME_KEY, null)
                    batch.update(newStoreRef!!, Constants.MANAGER_ID_KEY, newUser.id)
                    batch.update(newStoreRef!!, Constants.MANAGER_NAME_KEY, newUser.name)
                }
            }
            val userRef = db.collection(Constants.USERS_KEY).document(oldUser.id)
            batch.set(userRef, newUser)
        }.asLiveData()
    }

    override fun deleteUser(
        user: User
    ): TaskLiveData<Void> {
        return db.runBatch { batch ->
            if (user.role == User.Role.STORE_MANAGER && !user.storeId.isNullOrBlank()) {
                val storeReference = db.collection(Constants.STORES_KEY).document(user.storeId!!)
                batch.update(storeReference, Constants.MANAGER_ID_KEY, null)
                batch.update(storeReference, Constants.MANAGER_NAME_KEY, null)
            }
            batch.delete(db.collection(Constants.USERS_KEY).document(user.id))
        }.asLiveData()
    }
}