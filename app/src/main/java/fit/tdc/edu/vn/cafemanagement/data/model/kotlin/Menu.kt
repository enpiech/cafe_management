package fit.tdc.edu.vn.cafemanagement.data.model.kotlin

import fit.tdc.edu.vn.cafemanagement.data.model.FirestoreModel

data class Menu(
    var name: String? = null,
    var listItems: List<MenuItem> = listOf()
) : FirestoreModel()