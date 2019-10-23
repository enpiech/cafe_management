package fit.tdc.edu.vn.cafemanagement.data.model.user

import androidx.recyclerview.widget.DiffUtil
import androidx.room.Entity
import androidx.room.Ignore
import com.google.firebase.Timestamp
import fit.tdc.edu.vn.cafemanagement.data.model.FirestoreModel

@Entity(tableName = "user_table")
data class User(
    var name: String? = null,
    @Ignore var birth: Timestamp = Timestamp.now(),
    @Ignore var gender: Gender = Gender.UNKNOWN,
    var identityId: String? = null,
    var phone: String? = null,
    var address: String? = null,
    @Ignore var role: Type? = null,
    var storeId: String? = null,
    var username: String? = null
) : FirestoreModel() {
    enum class Type {
        MANAGER,
        STORE_MANAGER,
        WAITER,
        BARTENDER
    }

    enum class Gender {
        MALE,
        FEMALE,
        OTHER,
        UNKNOWN
    }

    enum class Role {
        MANAGER,
        STORE_MANAGER,
        BATENDER,
        WAITER
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem.name == newItem.name
            }
        }
    }
}