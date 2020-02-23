package fit.tdc.edu.vn.cafemanagement.data.repository.payment

import com.google.firebase.firestore.DocumentReference
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.payment.PaymentRemoteDataSource
import fit.tdc.edu.vn.cafemanagement.data.extension.DocumentType
import fit.tdc.edu.vn.cafemanagement.data.extension.TaskLiveData
import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.Payment
import fit.tdc.edu.vn.cafemanagement.data.model.order.Order
import fit.tdc.edu.vn.cafemanagement.data.model.user.UserInfor

class PaymentRepositoryImpl(
    val dataSource: PaymentRemoteDataSource
) : PaymentRepository {
    override fun insert(payment: Payment): TaskLiveData<DocumentReference> {
        return dataSource.createPayment(UserInfor.getInstance().storeId!!, payment)
    }

    override fun update(oldPayment: Payment, newPayment: Payment): TaskLiveData<Void> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addOrder(orders: List<Order>) {
        return dataSource.updatePayment(UserInfor.getInstance().storeId!!, orders)
    }

    override fun complete(payment: Payment): TaskLiveData<Void> {
        return dataSource.checkoutPayment(UserInfor.getInstance().storeId!!, payment)
    }

    override fun getListPaymentByState(state: Payment.State) =
        dataSource.readPaymentsWithState("", state, DocumentType.ALL)

    override fun getList() =
        dataSource.readPayments(UserInfor.getInstance().storeId!!, DocumentType.ALL)

    override fun get(id: String) =
        dataSource.readPayment(UserInfor.getInstance().storeId!!, id, DocumentType.ALL)
}