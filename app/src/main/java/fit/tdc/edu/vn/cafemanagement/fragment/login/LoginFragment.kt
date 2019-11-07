package fit.tdc.edu.vn.cafemanagement.fragment.login


import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import fit.tdc.edu.vn.cafemanagement.MainActivity
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.extension.Status
import fit.tdc.edu.vn.cafemanagement.data.model.login.LoggedInUserView
import fit.tdc.edu.vn.cafemanagement.data.model.user.User
import fit.tdc.edu.vn.cafemanagement.data.model.user.UserInfor
import fit.tdc.edu.vn.cafemanagement.util.afterTextChanged
import kotlinx.android.synthetic.main.fragment_login.*


class LoginFragment : Fragment(R.layout.fragment_login) {
    private lateinit var loginViewModel: LoginViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        loginViewModel = ViewModelProvider(
            this,
            LoginViewModelFactory()
        ).get(LoginViewModel::class.java)

        loginViewModel.loginFormState.observe(viewLifecycleOwner, Observer {
            val loginState = it ?: return@Observer

            // disable managerLogin button unless both username / password is valid
            login.isEnabled = loginState.isDataValid

            if (loginState.usernameError != null) {
                username.error = getString(loginState.usernameError)
            } else {
                username.error = null
                username.isEndIconVisible = false
            }
            if (loginState.passwordError != null) {
                password.error = getString(loginState.passwordError)
            } else {
                password.error = null
                password.isEndIconVisible = false
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
                    loginResult.data?.storeId?.let { id ->
                        rememberStoreId(id)
                        UserInfor.getInstance().storeId = getStoreId()
                    }
                    loginResult.data?.role?.let { role ->
                        (requireActivity() as MainActivity).changeUserRole(role)
                        rememberUserType(role)
                        UserInfor.getInstance().userType = getUserType()

                        when (role) {
                            User.Role.STORE_MANAGER -> {
                                findNavController().navigate(R.id.zoneListFragment)
                            }
                            User.Role.WAITER -> {
                                findNavController().navigate(R.id.tableListWaiterFragment)
                            }
                            User.Role.MANAGER -> {
                                findNavController().navigate(R.id.storeListFragment)
                            }
                            User.Role.CHEF -> {
                                findNavController().navigate(R.id.chefListFragment)
                            }
                        }
                    }

                }
            }
            activity?.setResult(AppCompatActivity.RESULT_OK)

        })
        username.afterTextChanged {
            loginViewModel.loginDataChanged(
                username.editText!!.text.toString(),
                password.editText!!.text.toString()
            )
        }

        password.editText!!.apply {
            afterTextChanged {
                loginViewModel.loginDataChanged(
                    username.editText!!.text.toString(),
                    password.editText!!.text.toString()
                )

            }

            setOnEditorActionListener { _, actionId, _ ->
                loading.visibility = View.VISIBLE
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        loginViewModel.login(
                            username.editText!!.text.toString(),
                            password.editText!!.text.toString(),
                            this.context
                        )
                }
                false
            }

            login.setOnClickListener {
                val imm =
                    context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(windowToken, 0)
                loading.visibility = View.VISIBLE
                loginViewModel.login(
                    username.editText!!.text.toString(),
                    password.editText!!.text.toString(),
                    this.context
                )
            }
        }

        super.onViewCreated(view, savedInstanceState)

        try {
            val sharedPreferences = context!!.getSharedPreferences("savedUser", MODE_PRIVATE)
            val strUsername = sharedPreferences.getString("username", "aaa")
            val strPassword = sharedPreferences.getString("password", "aaa")

            if (!strUsername.equals("aaa") && !strPassword.equals("aaa")) {
                username.editText!!.setText(strUsername!!.toCharArray(), 0, strUsername.length)
                password.editText!!.setText(strPassword!!.toCharArray(), 0, strPassword.length)

                login.callOnClick()
                loading.width
            }
        } catch (e: Exception) {
            Log.d("LoginFragment: ", e.message!!)
        }


    }

    private fun rememberUserType(type: User.Role?) {
        val sharedPreferences = context!!.getSharedPreferences("userInfo", MODE_PRIVATE).edit()
        sharedPreferences.putInt("userType", type!!.ordinal)
        sharedPreferences.apply()
    }

    private fun rememberStoreId(storeId: String?) {
        val sharedPreferences = context!!.getSharedPreferences("userInfo", MODE_PRIVATE).edit()
        sharedPreferences.putString("storeId", storeId)
        sharedPreferences.apply()

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

    private fun getUserType(): Int {
//        return requireActivity().getPreferences(MODE_PRIVATE)
//            .getInt(getString(R.string.user_type_key), resources.getInteger(R.integer.no_user_type))
        val sharedPreferences = context!!.getSharedPreferences("userInfo", MODE_PRIVATE)
        return sharedPreferences.getInt("userType", -1)
    }

    private fun getStoreId(): String? {
        val sharedPreferences = context!!.getSharedPreferences("userInfo", MODE_PRIVATE)
        return sharedPreferences.getString("storeId", "ko tim thay")
    }
}

