package fit.tdc.edu.vn.cafemanagement.data.model.ware_house

import androidx.room.Entity
import androidx.room.Ignore
import com.google.firebase.Timestamp
import fit.tdc.edu.vn.cafemanagement.data.model.FirestoreModel

@Entity(tableName = "warehouse_table")
data class WareHouse (
    var name: String? = null,
    var amount: Int? = 0,
    var state: State? = null,
    @Ignore var inDate: Timestamp? = null,
    @Ignore var outDate: Timestamp? = null,
    var unitId: String? = null,
    var unitNmae: String? = null,
    var materialId: String? = null
    ): FirestoreModel() {
    enum class State {
        INPUT,
        OUTPUT
    }
}