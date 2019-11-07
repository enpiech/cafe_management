package fit.tdc.edu.vn.cafemanagement.data.repository.impl

import com.google.firebase.firestore.DocumentReference
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.FireBaseAPI
import fit.tdc.edu.vn.cafemanagement.data.extension.DocumentType
import fit.tdc.edu.vn.cafemanagement.data.extension.TaskLiveData
import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.Payment
import fit.tdc.edu.vn.cafemanagement.data.repository.PaymentRepositoryAPI

class PaymentRepository(
    val dataSource: FireBaseAPI
) : PaymentRepositoryAPI {
    override fun insert(payment: Payment): TaskLiveData<DocumentReference> {
        return dataSource.createPayment("EfzspceETNgWk56YDOOt", payment)
    }

    override fun update(oldPayment: Payment, newPayment: Payment): TaskLiveData<Void> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun complete(payment: Payment): TaskLiveData<Void> {
        return dataSource.checkout("EfzspceETNgWk56YDOOt", payment)
    }

    override fun getListPaymentByState(state: Payment.State) =
        dataSource.getPaymentListByState("", state, DocumentType.ALL)

    override fun getList() = dataSource.getAllPaymentList("EfzspceETNgWk56YDOOt", DocumentType.ALL)

    override fun get(id: String) =
        dataSource.getPayment("EfzspceETNgWk56YDOOt", id, DocumentType.ALL)
}