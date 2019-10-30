package fit.tdc.edu.vn.cafemanagement.data.model.store

import fit.tdc.edu.vn.cafemanagement.data.model.FormState

data class StoreViewFormState(
    var nameError: Int? = null,
    var addressError: Int? = null
) : FormState()