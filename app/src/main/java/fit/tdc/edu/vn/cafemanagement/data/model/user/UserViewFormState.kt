package fit.tdc.edu.vn.cafemanagement.data.model.user

import fit.tdc.edu.vn.cafemanagement.data.model.FormState

data class UserViewFormState(
    val nameError: Int? = null
) : FormState()