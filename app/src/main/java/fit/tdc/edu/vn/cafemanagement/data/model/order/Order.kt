package fit.tdc.edu.vn.cafemanagement.data.model.order

import androidx.room.Entity
import androidx.room.PrimaryKey
import fit.tdc.edu.vn.cafemanagement.data.model.FirestoreModel

@Entity(tableName = "order_table")
data class Order(
    var name: String? = null
): FirestoreModel() {

    @PrimaryKey(autoGenerate = false)
    override var id: String = ""
}