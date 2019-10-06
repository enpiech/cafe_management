package fit.tdc.edu.vn.cafemanagement.data.data_source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import fit.tdc.edu.vn.cafemanagement.data.Result
import fit.tdc.edu.vn.cafemanagement.data.model.login.LoggedInUser
import fit.tdc.edu.vn.cafemanagement.data.model.user.UserType
import java.io.IOException
import javax.inject.Singleton

/**
 * Class that handles authentication w/ managerLogin credentials and retrieves user information.
 */
@Singleton
class LoginDataSource {
    private val auth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }
    private val firestore: FirebaseFirestore by lazy {
        FirebaseFirestore.getInstance()
    }
    private val _result = MutableLiveData<Result<LoggedInUser>>()
    val result: LiveData<Result<LoggedInUser>> = _result

    init {
        _result.value = null
    }

    fun managerLogin(username: String, password: String, callback: (Result<LoggedInUser>) -> (Unit)) {
        auth.signInWithEmailAndPassword(username, password)
            .addOnSuccessListener {
                val user = LoggedInUser(
                    it.user!!.email as String,
                    it.user!!.email as String,
                    UserType.MANAGER
                )
                _result.value = Result.Success(user)
                callback(Result.Success(user))
            }
            .addOnFailureListener {
                callback(Result.Error(it))
            }
    }

    fun storeManagerLogin(username: String, password: String, callback: (Result<LoggedInUser>) -> (Unit)) {
        firestore.collection("users").document(username)
            .get()
            .addOnSuccessListener { userDocument ->
                if (userDocument.exists()) {
                    if (userDocument["password"] != null) {
                        if (userDocument["password"] == password) {
                            val user = LoggedInUser(
                                userDocument.id,
                                userDocument["name"] as String,
                                UserType.STORE_MANAGER
                            )
                            callback(Result.Success(user))
                            return@addOnSuccessListener
                        }
                    }
                    callback(Result.Error(IOException("Error logging in")))
                }
            }
    }

    fun logout() {
        auth.signOut()
    }
}

