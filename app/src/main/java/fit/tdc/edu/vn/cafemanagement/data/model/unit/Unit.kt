package fit.tdc.edu.vn.cafemanagement.data.model.unit

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "unit_table")
data class Unit(var name: String) {
    //does it matter if these are private or not?
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}