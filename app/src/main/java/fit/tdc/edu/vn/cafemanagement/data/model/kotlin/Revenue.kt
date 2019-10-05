package fit.tdc.edu.vn.cafemanagement.data.model.kotlin

import com.google.firebase.Timestamp
import fit.tdc.edu.vn.cafemanagement.data.extension.FirestoreModel

data class Revenue(
    var storeId: String? = null,
    var storeName: String? = null,
    var income: Long? = null,
    var outcome: Long? = null,
    var date: Timestamp? = null
) : FirestoreModel()