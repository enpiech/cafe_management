package fit.tdc.edu.vn.cafemanagement.data.repository

import com.hadilq.liveevent.LiveEvent
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.LoginDataSource
import fit.tdc.edu.vn.cafemanagement.data.extension.FirestoreResource
import fit.tdc.edu.vn.cafemanagement.data.extension.Status
import fit.tdc.edu.vn.cafemanagement.data.model.user.User

/**
 * Class that requests authentication and loggedInUser information from the remote data source and
 * maintains an in-memory cache of login status and loggedInUser credentials information.
 */

class LoginRepository(val dataSource: LoginDataSource) {

    // in-memory cache of the loggedInUser object
    // TODO Local db or reference to make session
    private var loggedInUser: User? = null

    val isLoggedIn: Boolean
        get() = loggedInUser != null

    private var _loginResult = LiveEvent<FirestoreResource<User>>()
    var loginResult = _loginResult

    init {
        // If loggedInUser credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
        loggedInUser = null
        _loginResult.value = null
        _loginResult.addSource(dataSource.result) {
            if (it == null) {
                return@addSource
            }
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { user ->
                        setLoggedInUser(user) }
                }
                Status.ERROR -> _loginResult.value = FirestoreResource.error(Exception(it.errorMessage))
                Status.LOADING -> _loginResult.value = FirestoreResource.loading()
            }
        }
    }

    fun logout() {
        loggedInUser = null
        dataSource.logout()
    }

    fun login(username: String, password: String) {
        _loginResult.value = FirestoreResource.loading()
        // handle login
        dataSource.managerLogin(username, password)
    }

    private fun setLoggedInUser(loggedInUser: User) {
        this.loggedInUser = loggedInUser
        _loginResult.value = FirestoreResource.success(this.loggedInUser)
        // If loggedInUser credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }
}
