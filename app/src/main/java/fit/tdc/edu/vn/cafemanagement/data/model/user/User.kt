package fit.tdc.edu.vn.cafemanagement.data.model.user

import androidx.room.Entity
import com.google.firebase.Timestamp
import com.google.firebase.firestore.ServerTimestamp
import fit.tdc.edu.vn.cafemanagement.data.model.FirestoreModel
import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.Gender

@Entity(tableName = "user_table")
data class User(
    var name: String? = null,
    @ServerTimestamp var birth: Timestamp? = null,
    var gender: Gender? = null,
    var identityId: String? = null,
    var phone: String? = null,
    var address: String? = null,
    var role: Type? = null,
    var storeId: String? = null,
    var username: String? = null,
    @ServerTimestamp var lastLogin: Timestamp? = null
) : FirestoreModel() {
    enum class Type {
        MANAGER,
        STORE_MANAGER,
        WAITER,
        BARTENDER
    }
}