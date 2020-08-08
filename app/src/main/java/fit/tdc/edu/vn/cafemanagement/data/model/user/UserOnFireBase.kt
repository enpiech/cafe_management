package fit.tdc.edu.vn.cafemanagement.data.model.user

import androidx.room.Ignore
import com.google.firebase.Timestamp
import fit.tdc.edu.vn.cafemanagement.data.model.FirestoreModel

data class UserOnFireBase(
    val id: String,
    var name: String? = null,
    @Ignore var birth: Timestamp? = null,
    @Ignore var gender: User.Gender = User.Gender.UNKNOWN,
    var identityId: String? = null,
    var phone: String? = null,
    var address: String? = null,
    @Ignore var role: User.Role? = null,
    var storeId: String? = null,
    var storeName: String? = null,
    var username: String? = null,
    var password: String? = null
) {
    companion object {
        const val COLLECTION_KEY = "employees"
        const val USER_NAME_KEY = "username"
    }
}