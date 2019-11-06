package fit.tdc.edu.vn.cafemanagement.data.model.kotlin

import com.google.firebase.Timestamp
import com.google.firebase.firestore.Exclude
import fit.tdc.edu.vn.cafemanagement.data.model.FirestoreModel
import fit.tdc.edu.vn.cafemanagement.data.model.order.Order

data class Payment(
    var storeId: String? = null,
    var storeName: String? = null,
    var tableId: String? = null,
    var confirmedEmployeeId: String? = null,
    var confirmedEmployeeName: String? = null,
    var paidTime: Timestamp? = null,
    var state: State? = null,
    @get:Exclude var orderList: List<Order> = listOf(),
    var total: Long = 0
) : FirestoreModel() {
    enum class State {
        ORDERING,
        ORDERED,
        PAID
    }
}
