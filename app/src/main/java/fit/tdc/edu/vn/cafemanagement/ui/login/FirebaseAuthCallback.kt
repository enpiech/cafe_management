package fit.tdc.edu.vn.cafemanagement.ui.login

import fit.tdc.edu.vn.cafemanagement.data.Result
import fit.tdc.edu.vn.cafemanagement.data.model.login.LoggedInUser

interface FirebaseAuthCallback {
    fun onSignOut()
    fun onSignIn(result: Result<LoggedInUser>)
}