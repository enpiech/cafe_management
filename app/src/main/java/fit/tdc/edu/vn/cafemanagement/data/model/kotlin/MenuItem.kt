package fit.tdc.edu.vn.cafemanagement.data.model.kotlin

import fit.tdc.edu.vn.cafemanagement.data.model.FirestoreModel

data class MenuItem(
    var name: String? = null,
    var amount: Int = 1
) : FirestoreModel()