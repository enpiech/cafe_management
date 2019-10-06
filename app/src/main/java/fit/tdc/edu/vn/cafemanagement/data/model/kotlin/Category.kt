package fit.tdc.edu.vn.cafemanagement.data.model.kotlin

import fit.tdc.edu.vn.cafemanagement.data.extension.FirestoreModel

data class Category(
    var name: String? = null,
    override var collectionName: String = "categories"
): FirestoreModel()