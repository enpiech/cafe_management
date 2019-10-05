package fit.tdc.edu.vn.cafemanagement.data.repository

import fit.tdc.edu.vn.cafemanagement.data.Result
import fit.tdc.edu.vn.cafemanagement.data.data_source.LoginDataSource
import fit.tdc.edu.vn.cafemanagement.data.model.login.LoggedInUser

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

    init {
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
        user = null
    }

    fun logout() {
        user = null
        dataSource.logout()
    }

    fun login(username: String, password: String, callback: (Result<LoggedInUser>) -> (Unit)) {
        // handle login
        dataSource.login(username, password, callback = {
            if (it is Result.Success) {
                setLoggedInUser(it.data)
            }

            callback(it)
        })
    }

    private fun setLoggedInUser(loggedInUser: LoggedInUser) {
        this.user = loggedInUser
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }
}
