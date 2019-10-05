package fit.tdc.edu.vn.cafemanagement.data.model.kotlin

import fit.tdc.edu.vn.cafemanagement.data.extension.FirestoreModel

data class Menu(
    var name: String?,
    var listItems: ArrayList<MenuItem>
) : FirestoreModel()