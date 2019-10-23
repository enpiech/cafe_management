package fit.tdc.edu.vn.cafemanagement.fragment.login


import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.data_source.user.UserDatabase
import fit.tdc.edu.vn.cafemanagement.data.extension.Status
import fit.tdc.edu.vn.cafemanagement.data.model.login.LoggedInUserView
import fit.tdc.edu.vn.cafemanagement.data.model.user.User
import fit.tdc.edu.vn.cafemanagement.util.afterTextChanged
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment(R.layout.fragment_login) {
    private lateinit var loginViewModel: LoginViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        loginViewModel = ViewModelProvider(this, LoginViewModelFactory(UserDatabase.getInstance(requireContext())!!))
            .get(LoginViewModel::class.java)

        loginViewModel.loginFormState.observe(this@LoginFragment, Observer {
            val loginState = it ?: return@Observer

            // disable managerLogin button unless both username / password is valid
            login.isEnabled = loginState.isDataValid

            if (loginState.usernameError != null) {
                username.error = getString(loginState.usernameError)
            }
            if (loginState.passwordError != null) {
                edtPassword.error = getString(loginState.passwordError)
            }
        })

        loginViewModel.loginResult.observe(this@LoginFragment, Observer {
            val loginResult = it ?: return@Observer

            when (loginResult.status) {
                Status.LOADING -> loading.visibility = View.VISIBLE
                Status.ERROR -> {
                    loading.visibility = View.GONE
                    loginResult.errorMessage?.let { msg -> showLoginFailed(msg) }
                }
                Status.SUCCESS -> {
                    loading.visibility = View.GONE
                    updateUiWithUser(
                        LoggedInUserView(
                            displayName = loginResult.data?.name ?: "Noname"
                        )
                    )
                }
            }
//            activity?.setResult(Activity.RESULT_OK)

            //Complete and destroy managerLogin activity once successful
            this.findNavController().navigate(R.id.zoneListFragment)
            when (loginResult.data?.role) {
                User.Role.BARTENDER -> null
                User.Role.MANAGER -> null
                User.Role.STORE_MANAGER -> {

                }
                User.Role.WAITER -> null
            }
        })
        username.afterTextChanged {
            loginViewModel.loginDataChanged(
                username.text.toString(),
                edtPassword.text.toString()
            )
        }

        edtPassword.apply {
            afterTextChanged {
                loginViewModel.loginDataChanged(
                    username.text.toString(),
                    edtPassword.text.toString()
                )

            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        loginViewModel.login(
                            username.text.toString(),
                            edtPassword.text.toString()
                        )
                }
                false
            }

            login.setOnClickListener {
                val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(windowToken, 0)
                loading.visibility = View.VISIBLE
                loginViewModel.login(username.text.toString(), edtPassword.text.toString())
            }
        }

        super.onViewCreated(view, savedInstanceState)
    }

    private fun updateUiWithUser(model: LoggedInUserView) {
        val welcome = getString(R.string.welcome)
        val displayName = model.displayName
        // TODO : initiate successful logged in experience
        Toast.makeText(
            context,
            "$welcome $displayName",
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun showLoginFailed(errorString: String) {
        Toast.makeText(context, errorString, Toast.LENGTH_SHORT).show()
    }
}

