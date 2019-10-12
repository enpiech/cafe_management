package fit.tdc.edu.vn.cafemanagement.data.repository

import fit.tdc.edu.vn.cafemanagement.data.extension.CollectionLiveData
import fit.tdc.edu.vn.cafemanagement.data.extension.DocumentLiveData
import fit.tdc.edu.vn.cafemanagement.data.extension.QueryLiveData
import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.Payment

interface PaymentRepositoryAPI {
    fun getListPaymentByState(state: Payment.State) : QueryLiveData<Payment>
    fun getAllPaymentList(): CollectionLiveData<Payment>
    fun getPayment(id:String) : DocumentLiveData<Payment>
}