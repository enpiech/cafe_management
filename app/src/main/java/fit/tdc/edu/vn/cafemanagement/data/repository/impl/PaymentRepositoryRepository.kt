package fit.tdc.edu.vn.cafemanagement.data.repository.impl

import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.FireBaseAPI
import fit.tdc.edu.vn.cafemanagement.data.extension.DocumentType
import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.Payment
import fit.tdc.edu.vn.cafemanagement.data.repository.PaymentRepositoryAPI

class PaymentRepositoryRepository (val dataSource: FireBaseAPI) : PaymentRepositoryAPI{


    override fun getListPaymentByState(state: Payment.State) =
        dataSource.getPaymentListByState("",state,DocumentType.ALL)

    override fun getAllPaymentList() = dataSource.getAllPaymentList("", DocumentType.ALL)

    override fun getPayment(id: String) = dataSource.getPayment("", id, DocumentType.ALL)
}