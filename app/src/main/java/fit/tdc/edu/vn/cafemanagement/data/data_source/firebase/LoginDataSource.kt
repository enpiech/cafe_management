package fit.tdc.edu.vn.cafemanagement.data.data_source.firebase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import fit.tdc.edu.vn.cafemanagement.data.extension.FirestoreResource
import fit.tdc.edu.vn.cafemanagement.data.model.user.User
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
    private val _result = MutableLiveData<FirestoreResource<User>>(null)
    val result: LiveData<FirestoreResource<User>> = _result

    fun managerLogin(username: String, password: String) {
        auth.signInWithEmailAndPassword(username, password)
            .addOnSuccessListener {
                if (it.user == null) {
                    _result.value = FirestoreResource.error(Exception("User it not exist"))
                    return@addOnSuccessListener
                }
                val firebaseUser = it.user!!
                val user = User(
                    role = User.Role.MANAGER,
                    username = firebaseUser.email
                ).apply {
                    id = firebaseUser.uid
                }
                _result.value = FirestoreResource.success(user)
            }
            .addOnFailureListener {
                _result.value = FirestoreResource.error(it)
            }
    }

    fun employeeLogin(
        username: String,
        password: String,
        userType: User.Role
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

