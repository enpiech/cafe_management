package fit.tdc.edu.vn.cafemanagement.data.model.order

import fit.tdc.edu.vn.cafemanagement.data.model.FirestoreModel

data class Order(
    var materialId: String? = null,
    var name: String? = null,
    var amount: Int = 0,
    var price: Long = 0,
    var unitId: String? = null,
    var unitName: String? = null,
    var state: State = State.DOING,
    var paymentId: String? = null
): FirestoreModel() {
    enum class State {
        DOING,
        DONE
    }
}