package fit.tdc.edu.vn.cafemanagement.data.model.chef

import fit.tdc.edu.vn.cafemanagement.data.model.FormState

data class ChefViewFormState (
    var nameError: Int? = null,
    var paymentIdError: Int? = null
) : FormState()