package fit.tdc.edu.vn.cafemanagement.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import fit.tdc.edu.vn.cafemanagement.data.Result
import fit.tdc.edu.vn.cafemanagement.data.data_source.LoginDataSource
import fit.tdc.edu.vn.cafemanagement.data.extension.FirestoreResource
import fit.tdc.edu.vn.cafemanagement.data.model.login.LoggedInUser
import fit.tdc.edu.vn.cafemanagement.data.model.user.UserType

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class LoginRepository(val dataSource: LoginDataSource) {

    // in-memory cache of the loggedInUser object
    var user: LoggedInUser? = null
        private set

    val isLoggedIn: Boolean
        get() = user != null

    var loginResult = MediatorLiveData<FirestoreResource<LoggedInUser>>()

    init {
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
        user = null
        loginResult.value = null
    }

    fun logout() {
        user = null
        dataSource.logout()
    }

    fun login(username: String, password: String) {
        loginResult.value = FirestoreResource.loading()
        // handle login
        if (user != null) {
            loginResult.value = FirestoreResource.error(Exception("Already login"))
        }
        dataSource.managerLogin(username, password, callback = {
            if (it is Result.Success) {
                setLoggedInUser(it.data)
            } else {
                loginResult.value = FirestoreResource.error(Exception("failed"))
            }
        })
    }

    private fun setLoggedInUser(loggedInUser: LoggedInUser) {
        this.user = loggedInUser
        loginResult.value = FirestoreResource.success(this.user)
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }
}
