package fit.tdc.edu.vn.cafemanagement.data.model.kotlin

import fit.tdc.edu.vn.cafemanagement.data.extension.FirestoreModel

data class Table(
    var name: String? = null,
    var zoneId: String? = null,
    var stateId: TableState = TableState.FREE,
    override var collectionName: String = "tables"
) : FirestoreModel() {
    companion object {
        enum class TableState {
            ORDERING,
            FREE
        }
    }
}