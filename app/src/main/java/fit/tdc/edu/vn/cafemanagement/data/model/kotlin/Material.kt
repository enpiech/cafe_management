package fit.tdc.edu.vn.cafemanagement.data.model.kotlin

import fit.tdc.edu.vn.cafemanagement.data.extension.FirestoreModel

data class Material(
    var name: String?,
    var price: Long?,
    var unitId: String,
    var sellable: Boolean?
) : FirestoreModel()