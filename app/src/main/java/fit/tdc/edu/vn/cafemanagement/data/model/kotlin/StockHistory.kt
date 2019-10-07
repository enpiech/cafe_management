package fit.tdc.edu.vn.cafemanagement.data.model.kotlin

import com.google.firebase.Timestamp
import fit.tdc.edu.vn.cafemanagement.data.model.FirestoreModel

data class StockHistory(
    var materialId: String? = null,
    var materialName: String? = null,
    var amount: Int = 1,
    var actionTime: Timestamp? = null,
    var type: StockAction = StockAction.ISSUE
) : FirestoreModel() {
    companion object {
        enum class StockAction {
            RECEIPT,    // Nhập hàng
            ISSUE,      // Xuất hàng
            STOCKTAKE   // Kiểm kho
        }
    }
}