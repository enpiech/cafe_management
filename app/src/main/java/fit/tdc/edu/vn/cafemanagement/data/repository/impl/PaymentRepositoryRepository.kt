package fit.tdc.edu.vn.cafemanagement.data.repository.impl

import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.FireBaseAPI
import fit.tdc.edu.vn.cafemanagement.data.extension.DocumentType
import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.Payment
import fit.tdc.edu.vn.cafemanagement.data.model.user.UserInfor
import fit.tdc.edu.vn.cafemanagement.data.repository.PaymentRepositoryAPI

class PaymentRepositoryRepository (val dataSource: FireBaseAPI) : PaymentRepositoryAPI{


    override fun getListPaymentByState(state: Payment.State) =
        dataSource.getPaymentListByState(UserInfor.getInstance().storeId!!,state,DocumentType.ALL)

    override fun getAllPaymentList() = dataSource.getAllPaymentList(UserInfor.getInstance().storeId!!, DocumentType.ALL)

    override fun getPayment(id: String) = dataSource.getPayment(UserInfor.getInstance().storeId!!, id, DocumentType.ALL)
}