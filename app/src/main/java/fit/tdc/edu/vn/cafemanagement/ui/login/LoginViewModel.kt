package fit.tdc.edu.vn.cafemanagement.ui.login

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.*
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.Result
import fit.tdc.edu.vn.cafemanagement.data.extension.Status
import fit.tdc.edu.vn.cafemanagement.data.model.login.LoggedInUserView
import fit.tdc.edu.vn.cafemanagement.data.model.login.LoginFormState
import fit.tdc.edu.vn.cafemanagement.data.model.login.LoginResult
import fit.tdc.edu.vn.cafemanagement.data.repository.LoginRepository

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = loginRepository.loginResult.map {
        if (it == null) {
            Log.d("test", "init")
            LoginResult()
        } else {
            when (it.status) {
                Status.SUCCESS -> {
                    LoginResult(
                        success = LoggedInUserView(
                            displayName = it.data!!.displayName!!
                        ),
                        loading = false
                    )
                }
                Status.LOADING -> {
                    LoginResult(
                        error = null,
                        loading = true
                    )
                }
                Status.ERROR -> {
                    LoginResult(
                        error = R.string.login_failed,
                        loading = false
                    )
                }
            }
        }
    }
    val loginResult: LiveData<LoginResult> = _loginResult

    fun login(username: String, password: String) {
        if (_loginResult.value!!.loading) {
            Log.d("test", "is loading")
            return
        }
        if (_loginResult.value!!.success != null) {
            Log.d("test", "already login")
            return
        }

        loginRepository.login(username, password)
    }

    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value =
                LoginFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _loginForm.value =
                LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value =
                LoginFormState(isDataValid = true)
        }
    }

    // A placeholder username validation check
    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }
}
