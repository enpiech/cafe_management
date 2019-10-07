package fit.tdc.edu.vn.cafemanagement.ui.login

import android.app.Activity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.data_source.FireBaseDataSource
import fit.tdc.edu.vn.cafemanagement.data.extension.*
import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.Table
import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.Zone
import fit.tdc.edu.vn.cafemanagement.data.model.login.LoggedInUserView
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.table_viewmodel.TableViewModel
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.table_viewmodel.TableViewModelFactory
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.zone_viewmodel.ZoneViewModel
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.zone_viewmodel.ZoneViewModelFactory

class LoginActivity : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var zoneViewModel: ZoneViewModel
    private lateinit var tableViewModel: TableViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login2)


        val storeId = "EfzspceETNgWk56YDOOt"
        val src = FireBaseDataSource()
        val listZone: LiveData<List<Zone>?> = src.getZoneList(storeId, DocumentType.ALL).map {
            when (it.status) {
                Status.SUCCESS -> it.data
                else -> listOf()
            }
        }
        listZone.observe(this, Observer {
            it?.forEach {zone ->
               Log.d("test", zone.id + ": " + zone.toString())
            }
        })
        val listTable: LiveData<List<Table>?> = src.getTableList(storeId, DocumentType.ALL).map {
            when (it.status) {
                Status.SUCCESS -> it.data
                else -> listOf()
            }
        }
        listTable.observe(this, Observer {
            it?.forEach {table ->
                Log.d("test", table.id + ": " + table.toString())
            }
        })

        val filteredTableList = MediatorLiveData<List<Table>?>()
        filteredTableList.addSource(listTable) { filteredList ->
            filteredList?.let {
                filteredTableList.value = filteredList.filter { table ->
                    table.zoneId.equals("Il4QzqS8VvJIQ53ObtST")
                }
            }
        }
        filteredTableList.observe(this, Observer {
            Log.d("test", "table in Il4QzqS8VvJIQ53ObtST")
            it?.forEach {table ->
                Log.d("test", table.id + ": " + table.toString())
            }
        })



        val username = findViewById<EditText>(R.id.username)
        val password = findViewById<EditText>(R.id.password)
        val login = findViewById<Button>(R.id.login)
        val loading = findViewById<ProgressBar>(R.id.loading)

        loginViewModel = ViewModelProviders.of(this, LoginViewModelFactory())
            .get(LoginViewModel::class.java)

        loginViewModel.loginFormState.observe(this@LoginActivity, Observer {
            val loginState = it ?: return@Observer

            // disable managerLogin button unless both username / password is valid
            login.isEnabled = loginState.isDataValid

            if (loginState.usernameError != null) {
                username.error = getString(loginState.usernameError)
            }
            if (loginState.passwordError != null) {
                password.error = getString(loginState.passwordError)
            }
        })

        loginViewModel.loginResult.observe(this@LoginActivity, Observer {
            val loginResult = it ?: return@Observer

            when (loginResult.status) {
                Status.LOADING -> loading.visibility = View.VISIBLE
                Status.ERROR -> {
                    loading.visibility = View.GONE
                    loginResult.errorMessage?.let { message -> showLoginFailed(message) }
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
            setResult(Activity.RESULT_OK)

            //Complete and destroy managerLogin activity once successful
            //TODO Navigate
//            finish()
        })

        username.afterTextChanged {
            loginViewModel.loginDataChanged(
                username.text.toString(),
                password.text.toString()
            )
        }

        password.apply {
            afterTextChanged {
                loginViewModel.loginDataChanged(
                    username.text.toString(),
                    password.text.toString()
                )
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        loginViewModel.login(
                            username.text.toString(),
                            password.text.toString()
                        )
                }
                false
            }

            login.setOnClickListener {
                loading.visibility = View.VISIBLE
                loginViewModel.login(username.text.toString(), password.text.toString())
            }
        }
    }

    private fun updateUiWithUser(model: LoggedInUserView) {
        val welcome = getString(R.string.welcome)
        val displayName = model.displayName
        // TODO : initiate successful logged in experience
        Toast.makeText(
            applicationContext,
            "$welcome $displayName",
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }

    private fun showLoginFailed(errorString: String) {
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }
}

/**
 * Extension function to simplify setting an afterTextChanged action to EditText components.
 */
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}
