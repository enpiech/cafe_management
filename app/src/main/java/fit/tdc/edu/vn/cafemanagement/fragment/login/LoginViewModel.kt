package fit.tdc.edu.vn.cafemanagement.fragment.login

import android.app.Application
import android.content.Context
import android.util.Patterns
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.extension.FirestoreResource
import fit.tdc.edu.vn.cafemanagement.data.extension.Status
import fit.tdc.edu.vn.cafemanagement.data.model.login.LoginFormState
import fit.tdc.edu.vn.cafemanagement.data.repository.login.LoginRepository
import fit.tdc.edu.vn.cafemanagement.util.isPasswordValid
import fit.tdc.edu.vn.cafemanagement.util.isUserNameValid

class LoginViewModel(
    private val loginRepository: LoginRepository,
    application: Application
) : AndroidViewModel(application) {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = loginRepository.loginResult
    val loginResult = _loginResult

    fun login(username: String, password: String, context: Context) {
        if (_loginResult.value?.status == Status.LOADING) {
            return
        }
        if (loginRepository.isLoggedIn) {
            _loginResult.value = FirestoreResource.error(Exception("Already employeeLogin"))
            return
        }

        if (Patterns.EMAIL_ADDRESS.matcher(username).matches()) {
            loginRepository.managerLogin(username, password, context)
        } else {
            loginRepository.employeeLogin(username, password, context)
        }
    }

    fun loginDataChanged(username: String, password: String) {
        when {
            !isUserNameValid(username) && !isPasswordValid(
                password
            ) -> _loginForm.value =
                LoginFormState(
                    usernameError = R.string.invalid_username,
                    passwordError = R.string.invalid_password,
                    isDataValid = false
                )
            !isUserNameValid(username) && isPasswordValid(
                password
            ) -> _loginForm.value =
                LoginFormState(
                    usernameError = R.string.invalid_username,
                    passwordError = null,
                    isDataValid = false
                )
            isUserNameValid(username) && !isPasswordValid(
                password
            ) -> _loginForm.value =
                LoginFormState(
                    usernameError = null,
                    passwordError = R.string.invalid_password,
                    isDataValid = false
                )
            else -> _loginForm.value =
                LoginFormState(usernameError = null, passwordError = null, isDataValid = true)
        }
    }

    fun logout() = loginRepository.logout()
}
