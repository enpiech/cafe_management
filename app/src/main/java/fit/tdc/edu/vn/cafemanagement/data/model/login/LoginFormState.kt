package fit.tdc.edu.vn.cafemanagement.data.model.login

/**
 * Data validation state of the employeeLogin form.
 */
data class LoginFormState(
    val usernameError: Int? = null,
    val passwordError: Int? = null,
    val isDataValid: Boolean = false
)
