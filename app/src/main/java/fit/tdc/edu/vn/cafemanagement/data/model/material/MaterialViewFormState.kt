package fit.tdc.edu.vn.cafemanagement.data.model.material

import fit.tdc.edu.vn.cafemanagement.data.model.FormState

data class MaterialViewFormState(
    var nameError: Int? = null,
    var priceError: Int? = null
) : FormState()