package fit.tdc.edu.vn.cafemanagement.data.model.table

import fit.tdc.edu.vn.cafemanagement.data.model.FirestoreModel

data class Table(
    var name: String? = null,
    var zoneId: String? = null,
    var state: State = State.FREE
) : FirestoreModel() {
    enum class State {
        ORDERING,
        FREE,
        BOOKED
    }
}