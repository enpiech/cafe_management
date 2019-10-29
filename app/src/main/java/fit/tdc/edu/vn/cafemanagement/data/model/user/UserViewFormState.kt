package fit.tdc.edu.vn.cafemanagement.data.model.user

import fit.tdc.edu.vn.cafemanagement.data.model.FormState

data class UserViewFormState(
    var usernameError: Int? = null,
    var passwordError: Int? = null,
    var nameError: Int? = null,
    var birthError: Int? = null,
    var identityIdError: Int? = null,
    var phoneError: Int? = null,
    var addressError: Int? = null
) : FormState()