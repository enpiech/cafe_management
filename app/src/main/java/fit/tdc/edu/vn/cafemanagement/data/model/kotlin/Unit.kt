package fit.tdc.edu.vn.cafemanagement.data.model.kotlin

import androidx.room.Entity
import fit.tdc.edu.vn.cafemanagement.data.model.FirestoreModel

@Entity(tableName = "unit_table")
data class Unit(
    var name: String? = null
) : FirestoreModel()