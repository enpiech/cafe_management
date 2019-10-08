package fit.tdc.edu.vn.cafemanagement.data.model.kotlin

import fit.tdc.edu.vn.cafemanagement.data.model.FirestoreModel

data class Zone(
    var name: String? = null,
    var tablesInZone: List<Table>? = null
) : FirestoreModel()