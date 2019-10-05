package fit.tdc.edu.vn.cafemanagement.data.model.kotlin

import fit.tdc.edu.vn.cafemanagement.data.extension.FirestoreModel

data class MenuItem(
    var name: String?,
    var amount: Long?
) : FirestoreModel()