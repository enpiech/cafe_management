package fit.tdc.edu.vn.cafemanagement.data.model.table

import fit.tdc.edu.vn.cafemanagement.data.model.FirestoreModel

data class Table(
    var name: String? = null,
    var zoneId: String? = null,
    var state: TableState = TableState.FREE
) : FirestoreModel() {
    companion object {
        enum class TableState {
            ORDERING,
            FREE,
            BOOKED
        }
    }
}