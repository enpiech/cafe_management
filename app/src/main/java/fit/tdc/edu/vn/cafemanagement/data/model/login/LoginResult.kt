package fit.tdc.edu.vn.cafemanagement.data.model.login

/**
 * Authentication result : success (loggedInUser details) or error message.
 */
data class LoginResult(
    val success: LoggedInUserView? = null,
    val error: Int? = null,
    val loading: Boolean = false
)
