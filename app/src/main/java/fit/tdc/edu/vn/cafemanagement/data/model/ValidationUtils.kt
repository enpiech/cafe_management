package fit.tdc.edu.vn.cafemanagement.data.model

import android.util.Patterns
import com.google.firebase.Timestamp
import java.util.*

fun isNameValid(name: String?): Boolean {
    if (name.isNullOrEmpty()) {
        return false
    }
    return true
}

// A placeholder username validation check
fun isUserNameValid(username: String): Boolean {
    return if (username.contains('@')) {
        Patterns.EMAIL_ADDRESS.matcher(username).matches()
    } else {
        username.isNotBlank()
    }
}

// A placeholder password validation check
fun isPasswordValid(password: String): Boolean {
    return password.length > 5
}

fun String?.isValidUserName(): Boolean {
    return !isNullOrBlank()
}

fun String?.isValidPassword(): Boolean {
    return !isNullOrBlank() && this!!.length > 5
}

fun String?.isValidPersonalName(): Boolean {
    return !isNullOrBlank()
}

fun Timestamp?.isValidBirth(): Boolean {
    return this != null && toDate().before(Calendar.getInstance().time)
}

fun String?.isValidIdentityId(): Boolean {
    return !isNullOrBlank() && (this!!.length == 9 || this.length == 12)
}

fun String?.isValidPhoneNumber(): Boolean {
    return !isNullOrBlank() && (this!!.length == 10)
}

fun String?.isValidAddress(): Boolean {
    return !isNullOrBlank()
}

fun String?.isValidZoneId(): Boolean {
    return !isNullOrBlank()
}

fun String?.isValidUnitName(): Boolean {
    return !isNullOrBlank()
}

fun String?.isValidZoneName(): Boolean {
    return !isNullOrBlank()
}