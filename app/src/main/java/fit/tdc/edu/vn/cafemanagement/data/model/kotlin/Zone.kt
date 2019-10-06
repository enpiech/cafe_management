package fit.tdc.edu.vn.cafemanagement.data.model.kotlin

import fit.tdc.edu.vn.cafemanagement.data.extension.FirestoreModel

data class Zone(
    var name: String?,
    override var collectionName: String = "zones"
) : FirestoreModel()