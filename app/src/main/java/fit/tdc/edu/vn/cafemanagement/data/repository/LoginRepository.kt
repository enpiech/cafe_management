package fit.tdc.edu.vn.cafemanagement.data.repository

import androidx.lifecycle.MediatorLiveData
import fit.tdc.edu.vn.cafemanagement.data.Result
import fit.tdc.edu.vn.cafemanagement.data.data_source.LoginDataSource
import fit.tdc.edu.vn.cafemanagement.data.extension.FirestoreResource
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

    private var _loginResult = MediatorLiveData<FirestoreResource<LoggedInUser>>()
    var loginResult = _loginResult

    init {
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
        user = null
        _loginResult.value = null
        _loginResult.addSource(dataSource.result) {
            when (it) {
                is Result.Success -> {
                    setLoggedInUser(it.data)
                }
                is Result.Error -> _loginResult.value = FirestoreResource.error(it.exception)
            }
        }
    }

    fun logout() {
        user = null
        dataSource.logout()
    }

    fun login(username: String, password: String) {
        _loginResult.value = FirestoreResource.loading()
        // handle login
        if (isLoggedIn) {
            _loginResult.value = FirestoreResource.error(Exception("Already login"))
        }

        dataSource.managerLogin(username, password)
    }

    private fun setLoggedInUser(loggedInUser: LoggedInUser) {
        this.user = loggedInUser
        _loginResult.value = FirestoreResource.success(this.user)
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }
}
