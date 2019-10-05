package fit.tdc.edu.vn.cafemanagement.data.data_source

import com.google.firebase.auth.FirebaseAuth
import fit.tdc.edu.vn.cafemanagement.data.Result
import fit.tdc.edu.vn.cafemanagement.data.model.login.LoggedInUser
import java.io.IOException

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {
    fun login(username: String, password: String, callback: (Result<LoggedInUser>) -> (Unit)) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(username.plus("@ccf.fit.tdc"), password)
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
        // TODO: revoke authentication
    }
}

