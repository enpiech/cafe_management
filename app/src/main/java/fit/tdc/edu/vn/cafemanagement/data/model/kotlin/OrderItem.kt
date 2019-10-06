package fit.tdc.edu.vn.cafemanagement.data.model.kotlin

import fit.tdc.edu.vn.cafemanagement.data.extension.FirestoreModel

data class OrderItem(
    var name: String? = null,
    var amount: Int = 1,
    var unitName: String? = null,
    var pricePerUnit: Long? = null,
    var totalPrice: Long? = null
) : FirestoreModel()