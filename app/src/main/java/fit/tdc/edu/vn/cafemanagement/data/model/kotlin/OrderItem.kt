package fit.tdc.edu.vn.cafemanagement.data.model.kotlin

import fit.tdc.edu.vn.cafemanagement.data.model.FirestoreModel

data class OrderItem(
    var name: String? = null,
    var amount: Int = 0,
    var unitName: String? = null,
    var pricePerUnit: Long? = null,
    var totalPrice: Long? = null
) : FirestoreModel()