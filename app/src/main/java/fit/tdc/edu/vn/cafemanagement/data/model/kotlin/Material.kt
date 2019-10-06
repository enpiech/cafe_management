package fit.tdc.edu.vn.cafemanagement.data.model.kotlin

import fit.tdc.edu.vn.cafemanagement.data.extension.FirestoreModel

data class Material(
    var name: String? = null,
    var price: Long? = null,
    var unitId: String? = null,
    var sellable: Boolean = false
) : FirestoreModel()