package fit.tdc.edu.vn.cafemanagement.data.data_source.firebase

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import fit.tdc.edu.vn.cafemanagement.data.extension.FirestoreResource
import fit.tdc.edu.vn.cafemanagement.data.model.user.User
import fit.tdc.edu.vn.cafemanagement.data.model.user.UserOnFireBase
import fit.tdc.edu.vn.cafemanagement.util.Constants
import fit.tdc.edu.vn.cafemanagement.util.asUser
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

interface AuthDataSource {
    suspend fun managerLogin(username: String, password: String): User
    suspend fun employeeLogin(username: String, password: String): User
    suspend fun logout()
}

/**
 * Class that handles authentication w/ managerLogin credentials and retrieves loggedInUser information.
 */
@Singleton
class AuthDataSourceImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
    private val sharedPreferences: SharedPreferences
): AuthDataSource {
    private val _result = MutableLiveData<FirestoreResource<User>?>(null)
    val result: MutableLiveData<FirestoreResource<User>?> = _result

    override suspend fun logout() {
        auth.signOut()
    }

    override suspend fun managerLogin(
        username: String,
        password: String
    ): User = suspendCoroutine { cont ->
        auth.signInWithEmailAndPassword(username, password)
            .addOnSuccessListener {
                it.user?.let { firebaseUser ->
                    cont.resume(firebaseUser.asUser())
                } ?: cont.resumeWithException(IllegalStateException("User it not exist"))
            }
            .addOnFailureListener { cont.resumeWithException(it) }
    }

    override suspend fun employeeLogin(
        username: String,
        password: String
    ): User = suspendCoroutine { cont ->
        firestore
            .collection(UserOnFireBase.COLLECTION_KEY)
            .whereEqualTo(UserOnFireBase.USER_NAME_KEY, username)
            .get()
            .addOnSuccessListener { snapshot ->
                if (snapshot.isEmpty) {
                    cont.resumeWithException(IllegalStateException("User is not exist"))
                }
                val userDocument = snapshot.documents.first()
                if (!userDocument.exists()) {
                    cont.resumeWithException(IllegalStateException("User is not exist"))
                }

                if (!userDocument.getString(Constants.USER_PASSWORD_KEY).equals(password)) {
                    cont.resumeWithException(IllegalStateException("Username and Password is not match"))
                }

                val user = userDocument.toObject(User::class.java)?.apply {
                    id = userDocument.id
                }

                user?.let {
                    cont.resume(user)
                } ?: cont.resumeWithException(ClassCastException("Cannot cast user from document"))
            }
            .addOnFailureListener { cont.resumeWithException(it) }
    }
}

