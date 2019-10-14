package fit.tdc.edu.vn.cafemanagement.data.model

import android.util.Patterns

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