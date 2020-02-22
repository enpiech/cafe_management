package fit.tdc.edu.vn.cafemanagement.data.data_source.firebase

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import fit.tdc.edu.vn.cafemanagement.data.extension.FirestoreResource
import fit.tdc.edu.vn.cafemanagement.data.model.user.User
import fit.tdc.edu.vn.cafemanagement.util.Constants
import fit.tdc.edu.vn.cafemanagement.util.asUser
import fit.tdc.edu.vn.cafemanagement.util.getUserIfUserNameMatchPassword
import fit.tdc.edu.vn.cafemanagement.util.saveInfoToLocal
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

    /**
     * Quản lý quán đăng nhập
     *
     * @param username
     * @param password
     * @param context Để get SharePreferences
     */
    fun managerLogin(username: String, password: String, context: Context) {
        /**
         * Thử đăng nhập bằng FirebaseAuth
         */
        auth.signInWithEmailAndPassword(username, password)
            /**
             * Nếu có kết quả trả về
             */
            .addOnSuccessListener {
                if (it.user == null) {
                    _result.value = FirestoreResource.error(Exception("User it not exist"))
                    return@addOnSuccessListener
                } else {
                    val fireBaseUser = it.user!!
                    val user = fireBaseUser.asUser()
                    user.saveInfoToLocal(context, User.Role.MANAGER)
                    _result.value = FirestoreResource.success(user)
                }
            }
            .addOnFailureListener {
                _result.value = FirestoreResource.error(it)
            }
    }

    /**
     * Nhân viên quán đăng nhập
     *
     * @param username
     * @param password
     * @param context Để get SharePreferences
     */
    fun employeeLogin(
        username: String,
        password: String,
        context: Context
    ) {
        firestore
            .collection(Constants.USERS_KEY)
            .whereEqualTo(Constants.USER_NAME_KEY, username)
            .get()
            .addOnSuccessListener { userDocument ->
                if (userDocument.isEmpty) {
                    _result.value = FirestoreResource.error(Exception("User is not exist"))
                    return@addOnSuccessListener
                }
                _result.value = userDocument.documents.first().getUserIfUserNameMatchPassword(context, password)
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

