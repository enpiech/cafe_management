package fit.tdc.edu.vn.cafemanagement.util

import android.content.Context
import android.util.Log
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.extension.FirestoreResource
import fit.tdc.edu.vn.cafemanagement.data.model.user.User

fun FirebaseUser.asUser(): User {
    val fireBaseUser = this
    return User(
        role = User.Role.MANAGER,
        username = fireBaseUser.email
    ).apply {
        id = fireBaseUser.uid
    }
}

fun User.saveInfoToLocal(context: Context, role: User.Role) {
    try {
        val sharedPref = context.getSharedPreferences(
            context.getString(R.string.session_user),
            Context.MODE_PRIVATE
        )
        with(sharedPref.edit()) {
            putString(
                context.getString(R.string.user_name_key),
                username
            )
            putString(
                context.getString(R.string.user_password_key),
                password
            )
            putInt(
                context.getString(R.string.user_type_key),
                role.ordinal
            )
            commit()
        }
    } catch (e: Exception) {
        Log.d("LoginDataSrc.mgrLogin", e.message!!)
    }
}

fun DocumentSnapshot.getUserIfUserNameMatchPassword(context: Context, password: String): FirestoreResource<User> {
    val userDocument = this
    if (!userDocument.exists()) {
        return FirestoreResource.error(Exception("User is not exist"))
    }

    if (!userDocument.getString(Constants.USER_PASSWORD_KEY).equals(password)) {
        return FirestoreResource.error(Exception("Username and Password is not match"))
    }

    val user = userDocument.toObject(User::class.java)?.apply {
        id = userDocument.id
    }
//
//    user?.saveInfoToLocal(context, user.role ?: User.Role.WAITER) ?: return FirestoreResource.error(Exception("Cannot parse user document to object"))

    return FirestoreResource.success(user)
}