package fit.tdc.edu.vn.cafemanagement.data.model.zone

import fit.tdc.edu.vn.cafemanagement.data.model.FirestoreModel
import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.Table

data class Zone(
    var name: String? = null,
    var tablesInZone: List<Table>? = null
) : FirestoreModel()