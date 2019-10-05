package fit.tdc.edu.vn.cafemanagement.data.model.kotlin

import fit.tdc.edu.vn.cafemanagement.data.extension.FirestoreModel

data class OrderItem(
    var name: String?,
    var amount: Int?,
    var unitName: String?,
    var priceperUnit: Long?,
    var totalPrice: Long?
) : FirestoreModel()