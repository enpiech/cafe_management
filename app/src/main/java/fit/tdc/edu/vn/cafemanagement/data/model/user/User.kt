package fit.tdc.edu.vn.cafemanagement.data.model.user

import androidx.annotation.StringRes
import androidx.recyclerview.widget.DiffUtil
import androidx.room.Entity
import androidx.room.Ignore
import com.google.firebase.Timestamp
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.model.FirestoreModel

@Entity(tableName = "user_table")
data class User(
    var name: String? = null,
    @Ignore var birth: Timestamp? = null,
    @Ignore var gender: Gender = Gender.UNKNOWN,
    var identityId: String? = null,
    var phone: String? = null,
    var address: String? = null,
    @Ignore var role: Role? = null,
    var storeId: String? = null,
    var storeName: String? = null,
    var username: String? = null,
    var password: String? = null
) : FirestoreModel() {
    enum class Role(@StringRes val nameResId: Int) {
        MANAGER(R.string.lbl_manager),
        STORE_MANAGER(R.string.lbl_store_manager),
        WAITER(R.string.lbl_waiter),
        BARTENDER(R.string.lbl_bartender);


    }

    enum class Gender(@StringRes val nameResId: Int) {
        MALE(R.string.lbl_male),
        FEMALE(R.string.lbl_female),
        OTHER(R.string.lbl_gender_other),
        UNKNOWN(R.string.lbl_gender_unknown)
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