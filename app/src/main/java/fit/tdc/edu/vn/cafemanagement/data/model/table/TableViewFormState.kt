package fit.tdc.edu.vn.cafemanagement.data.model.table

import fit.tdc.edu.vn.cafemanagement.data.model.FormState

data class TableViewFormState (
    var nameError: Int? = null,
    var zoneIdError: Int? = null
) : FormState()