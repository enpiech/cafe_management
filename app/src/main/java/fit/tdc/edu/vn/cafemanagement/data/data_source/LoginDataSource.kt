package fit.tdc.edu.vn.cafemanagement.data.data_source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ServerValue
import com.google.firebase.firestore.FirebaseFirestore
import fit.tdc.edu.vn.cafemanagement.data.extension.FirestoreResource
import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.User
import fit.tdc.edu.vn.cafemanagement.data.model.user.UserType
import java.lang.Exception
import javax.inject.Singleton

/**
 * Class that handles authentication w/ managerLogin credentials and retrieves loggedInUser information.
 */
@Singleton
class LoginDataSource {
    private val auth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }
    private val firestore: FirebaseFirestore by lazy {
        FirebaseFirestore.getInstance()
    }
    private val _result = MutableLiveData<FirestoreResource<User>>()
    val result: LiveData<FirestoreResource<User>> = _result

    init {
        _result.value = null
    }

    fun managerLogin(username: String, password: String) {
        auth.signInWithEmailAndPassword(username, password)
            .addOnSuccessListener {
                if (it.user == null) {
                    _result.value = FirestoreResource.error(Exception("User it not exist"))
                    return@addOnSuccessListener
                }
                val firebaseUser = it.user!!
                val user = User(
                    role = UserType.MANAGER,
                    username = firebaseUser.email,
                    lastLogin = Timestamp.now()
                ).apply {
                    id = firebaseUser.uid
                }

                _result.value = FirestoreResource.success(user)
            }
            .addOnFailureListener {
                _result.value = FirestoreResource.error(it)
            }
    }

    fun storeManagerLogin(
        username: String,
        password: String,
        userType: UserType
    ) {
        firestore.collection("users").document(username)
            .get()
            .addOnSuccessListener { userDocument ->
                if (userDocument.exists()) {
                    if (userDocument.getString("password").equals(password)) {
                        val user = userDocument.toObject(User::class.java)?.apply {
                            id = userDocument.id
                        }
                        _result.value = FirestoreResource.success(user)
                        return@addOnSuccessListener
                    } else {
                        _result.value = FirestoreResource.error(Exception("Username and Password is not match"))
                    }
                } else {
                    _result.value = FirestoreResource.error(Exception("User is not exist"))
                }
            }
            .addOnFailureListener {
                _result.value = FirestoreResource.error(it)
            }
    }

    fun logout() {
        auth.signOut()
        _result.value = null
    }
}

