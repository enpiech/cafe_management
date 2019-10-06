package fit.tdc.edu.vn.cafemanagement.data.model.kotlin

import com.google.firebase.Timestamp
import fit.tdc.edu.vn.cafemanagement.data.extension.FirestoreModel
import fit.tdc.edu.vn.cafemanagement.data.model.Gender

data class Employee(
    var name: String? = null,
    var birth: Timestamp? = null,
    var gender: Gender? = null,
    var identityId: String? = null,
    var phone: String? = null,
    var address: String? = null,
    var roleId: String? = null,
    var storeId: String? = null,
    var password: String? = null,
    override var collectionName: String = "employees"
) : FirestoreModel()