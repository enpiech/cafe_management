package fit.tdc.edu.vn.cafemanagement.data.model.kotlin

import androidx.room.Entity
import androidx.room.PrimaryKey
import fit.tdc.edu.vn.cafemanagement.data.model.FirestoreModel

@Entity(tableName = "category_table")
data class Category(
    var name: String? = null
): FirestoreModel() {

    @PrimaryKey(autoGenerate = true)
    override var id: String = ""
}