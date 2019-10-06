package fit.tdc.edu.vn.cafemanagement.data.data_source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import fit.tdc.edu.vn.cafemanagement.data.Result
import fit.tdc.edu.vn.cafemanagement.data.model.login.LoggedInUser
import fit.tdc.edu.vn.cafemanagement.data.model.user.UserType
import java.io.IOException
import java.lang.Exception
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

    fun managerLogin(username: String, password: String) {
        auth.signInWithEmailAndPassword(username, password)
            .addOnSuccessListener {
                if (it.user == null) {
                    _result.value = Result.Error(Exception("User it not exist"))
                    return@addOnSuccessListener
                }
                val user = LoggedInUser(
                    it.user!!.email as String,
                    it.user!!.email as String,
                    UserType.MANAGER
                )
                _result.value = Result.Success(user)
            }
            .addOnFailureListener {
                _result.value = Result.Error(it)
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
                        val user = LoggedInUser(
                            userDocument.id,
                            userDocument["name"] as String,
                            userType
                        )
                        _result.value = Result.Success(user)
                        return@addOnSuccessListener
                    } else {
                        _result.value = Result.Error(Exception("Username and Password is not match"))
                    }
                } else {
                    _result.value = Result.Error(Exception("User is not exist"))
                }
            }
            .addOnFailureListener {
                _result.value = Result.Error(it)
            }
    }

    fun logout() {
        auth.signOut()
        _result.value = null
    }
}

