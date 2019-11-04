package fit.tdc.edu.vn.cafemanagement.data.model.chef

import fit.tdc.edu.vn.cafemanagement.data.model.FirestoreModel

data class Chef(
    var name: String? = null,
    var amount: Int = 0,
    var paymentId: String? = null,
    var state: String? = null
) : FirestoreModel(){
    enum class State{
        DONE,
        DOING
    }
}