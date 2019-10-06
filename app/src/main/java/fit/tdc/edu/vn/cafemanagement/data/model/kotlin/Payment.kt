package fit.tdc.edu.vn.cafemanagement.data.model.kotlin

import com.google.firebase.Timestamp
import fit.tdc.edu.vn.cafemanagement.data.extension.FirestoreModel

data class Payment(
    var storeId: String? = null,
    var storeName: String? = null,
    var tableId: String? = null,
    var confirmedEmployeeId: String? = null,
    var confirmedEmployeeName: String? = null,
    var paidTime: Timestamp? = null,
    var isPaid: Boolean = false,
    var orderList: List<OrderItem> = listOf(),
    override var collectionName: String = "payments"
) : FirestoreModel()
