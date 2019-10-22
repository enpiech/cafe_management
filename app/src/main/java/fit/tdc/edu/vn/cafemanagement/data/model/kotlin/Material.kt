package fit.tdc.edu.vn.cafemanagement.data.model.kotlin

import androidx.room.Entity
import fit.tdc.edu.vn.cafemanagement.data.model.FirestoreModel

@Entity(tableName = "material_table")
data class Material(
    var name: String? = null,
    var price: Long? = null,
    var unitId: String? = null,
    var categoryID : String? = null,
    var image: Int? = 0,
    var sellable: Boolean = false
) : FirestoreModel()