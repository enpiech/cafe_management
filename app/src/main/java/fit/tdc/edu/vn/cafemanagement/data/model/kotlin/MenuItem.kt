package fit.tdc.edu.vn.cafemanagement.data.model.kotlin

import fit.tdc.edu.vn.cafemanagement.data.extension.FirestoreModel

data class MenuItem(
    var name: String? = null,
    var amount: Int = 1,
    override var collectionName: String = "menuItems"
) : FirestoreModel()