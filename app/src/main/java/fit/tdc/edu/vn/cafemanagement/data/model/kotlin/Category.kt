package fit.tdc.edu.vn.cafemanagement.data.model.kotlin

import androidx.room.Entity
import fit.tdc.edu.vn.cafemanagement.data.model.FirestoreModel

@Entity(tableName = "category_table")
data class Category(
    var name: String? = null
): FirestoreModel()