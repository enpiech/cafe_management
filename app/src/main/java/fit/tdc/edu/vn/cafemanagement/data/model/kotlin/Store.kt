package fit.tdc.edu.vn.cafemanagement.data.model.kotlin

import fit.tdc.edu.vn.cafemanagement.data.model.FirestoreModel

data class Store(
    var name: String? = null,
    var address: String? = null,
    var managerId: String? = null,
    var managerName: String? = null,
    var zonesInStore: List<Zone>? = null
) : FirestoreModel()