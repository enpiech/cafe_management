package fit.tdc.edu.vn.cafemanagement.data.data_source

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.auth.User
import fit.tdc.edu.vn.cafemanagement.data.Result
import fit.tdc.edu.vn.cafemanagement.data.model.login.LoggedInUser
import java.io.IOException
import javax.inject.Singleton

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
@Singleton
class LoginDataSource {
    private val auth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    fun login(username: String, password: String, callback: (Result<LoggedInUser>) -> (Unit)) {
        auth.signInWithEmailAndPassword(username.plus("@ccf.fit.tdc"), password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    val user = LoggedInUser(
                        it.result!!.user!!.email!!,
                        it.result!!.user!!.email!!
                    )
                    callback(Result.Success(user))
                } else {
                    callback(Result.Error(IOException("Error logging in")))
                }
            }
    }

    fun logout() {
        auth.signOut()
    }
}

