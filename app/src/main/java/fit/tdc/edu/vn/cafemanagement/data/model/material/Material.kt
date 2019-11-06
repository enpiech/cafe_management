package fit.tdc.edu.vn.cafemanagement.data.model.material

import androidx.annotation.StringRes
import androidx.recyclerview.widget.DiffUtil
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.model.FirestoreModel

data class Material(
    var name: String? = null,
    var price: Long = 0,
    var unitId: String? = null,
    var unitName: String? = null,
    var categoryId: String? = null,
    var categoryName: String? = null,
    var sellable: Boolean = false,
    var type: Type = Type.INGREDIENT
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

    enum class Type(@StringRes val resId: Int) {
        DRINK(R.string.material_type_drink),
        FOOD(R.string.material_type_food),
        INGREDIENT(R.string.material_type_ingredient)
    }
}