package fit.tdc.edu.vn.cafemanagement.data.model.material

import androidx.recyclerview.widget.DiffUtil
import fit.tdc.edu.vn.cafemanagement.data.model.FirestoreModel

data class Material(
    var name: String? = null,
    var price: Long? = null,
    var unitId: String? = null,
    var categoryId: String? = null,
    var sellable: Boolean = false
) : FirestoreModel() {
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Material>() {
            override fun areItemsTheSame(oldItem: Material, newItem: Material): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Material, newItem: Material): Boolean {
                return oldItem.name == newItem.name
            }
        }
    }
}