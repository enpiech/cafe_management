package fit.tdc.edu.vn.cafemanagement.data.model.user

import androidx.recyclerview.widget.DiffUtil
import com.google.firebase.Timestamp
import com.google.firebase.firestore.ServerTimestamp
import fit.tdc.edu.vn.cafemanagement.data.model.FirestoreModel
import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.Gender

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