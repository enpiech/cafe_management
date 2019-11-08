package fit.tdc.edu.vn.cafemanagement.data.data_source.firebase

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.extension.FirestoreResource
import fit.tdc.edu.vn.cafemanagement.data.model.user.User
import fit.tdc.edu.vn.cafemanagement.util.Constants
import javax.inject.Singleton

/**
 * Class that handles authentication w/ managerLogin credentials and retrieves loggedInUser information.
 */
@Singleton
class LoginDataSource {
    private val auth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }
    private val firestore: FirebaseFirestore by lazy {
        FirebaseFirestore.getInstance()
    }
    private val _result = MutableLiveData<FirestoreResource<User>>(null)
    val result: LiveData<FirestoreResource<User>> = _result

    fun managerLogin(username: String, password: String, context: Context) {
        auth.signInWithEmailAndPassword(username, password)
            .addOnSuccessListener {
                if (it.user == null) {
                    _result.value = FirestoreResource.error(Exception("User it not exist"))
                    return@addOnSuccessListener
                }
                val fireBaseUser = it.user!!
                val user = User(
                    role = User.Role.MANAGER,
                    username = fireBaseUser.email
                ).apply {
                    id = fireBaseUser.uid
                }
                try {
                    val sharedPref = context.getSharedPreferences(
                        context.getString(R.string.session_user),
                        MODE_PRIVATE
                    )
                    with(sharedPref.edit()) {
                        putString(context.getString(R.string.user_name_key), username)
                        putString(context.getString(R.string.user_password_key), password)
                        putInt(context.getString(R.string.user_type_key), User.Role.MANAGER.ordinal)
                        commit()
                    }
//                    val sharedReferenceEditor = context.getSharedPreferences(context.getString(
//                        R.string.session_user), MODE_PRIVATE).edit()
//                    sharedReferenceEditor.putString("username", username)
//                    sharedReferenceEditor.putString("password", password)
//                    sharedReferenceEditor.apply()
                } catch (e: Exception) {
                    Log.d("LoginDataSrc.mgrLogin", e.message!!)
                }
                _result.value = FirestoreResource.success(user)
            }
            .addOnFailureListener {
                _result.value = FirestoreResource.error(it)
            }
    }

    fun employeeLogin(
        username: String,
        password: String,
        context: Context
    ) {
        firestore.collection(Constants.USERS_KEY).whereEqualTo(Constants.USER_NAME_KEY, username)
            .get()
            .addOnSuccessListener { userDocument ->
                if (userDocument.isEmpty) {
                    _result.value = FirestoreResource.error(Exception("User is not exist"))
                    return@addOnSuccessListener
                }
                val doc = userDocument.documents[0]
                if (doc.exists()) {
                    if (doc.getString(Constants.USER_PASSWORD_KEY).equals(password)) {
                        val user = doc.toObject(User::class.java)?.apply {
                            id = doc.id
                        }
                        try {
                            val sharedPref = context.getSharedPreferences(
                                context.getString(R.string.session_user),
                                MODE_PRIVATE
                            )
                            with(sharedPref.edit()) {
                                putString(context.getString(R.string.user_name_key), username)
                                putString(context.getString(R.string.user_password_key), password)
                                putInt(context.getString(R.string.user_type_key), user?.role?.ordinal ?: User.Role.WAITER.ordinal)
                                commit()
                            }
//                            val sharedReferenceEditor = context.getSharedPreferences("savedUser", MODE_PRIVATE).edit()
//                            sharedReferenceEditor.putString("username", username)
//                            sharedReferenceEditor.putString("password", password)
//                            sharedReferenceEditor.apply()
//
//                            Log.d("employee login: ====", username + password)
                        } catch (e: Exception) {
                            Log.d("LoginDataSrc.mgrLogin", e.message!!)
                        }
                        _result.value = FirestoreResource.success(user)
                        return@addOnSuccessListener
                    } else {
                        _result.value = FirestoreResource.error(Exception("Username and Password is not match"))
                    }
                } else {
                    _result.value = FirestoreResource.error(Exception("User is not exist"))
                }
            }
            .addOnFailureListener {
                _result.value = FirestoreResource.error(it)
            }
    }

    fun logout() {
        auth.signOut()
        _result.value = null
    }
}

