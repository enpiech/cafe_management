package fit.tdc.edu.vn.cafemanagement.data.model.ware_house

import fit.tdc.edu.vn.cafemanagement.data.model.FormState

data class WareHouseViewFormState (
    var nameError: Int? = null,
    var stockError: Int? = null
) : FormState()