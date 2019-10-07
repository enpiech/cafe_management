package fit.tdc.edu.vn.cafemanagement.data.model.kotlin

import com.google.firebase.Timestamp
import fit.tdc.edu.vn.cafemanagement.data.model.FirestoreModel

data class Revenue(
    var storeId: String? = null,
    var storeName: String? = null,
    var income: Long = 0,
    var outcome: Long = 0,
    var date: Timestamp? = null
) : FirestoreModel()