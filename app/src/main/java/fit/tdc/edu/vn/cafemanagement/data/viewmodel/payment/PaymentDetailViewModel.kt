package fit.tdc.edu.vn.cafemanagement.data.viewmodel.payment

import com.google.firebase.firestore.DocumentReference
import fit.tdc.edu.vn.cafemanagement.data.extension.TaskLiveData
import fit.tdc.edu.vn.cafemanagement.data.model.category.CategoryViewFormState
import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.Payment
import fit.tdc.edu.vn.cafemanagement.data.repository.PaymentRepositoryAPI
import fit.tdc.edu.vn.cafemanagement.fragment.BaseDetailViewModel

class PaymentDetailViewModel(
    private val paymentRepository: PaymentRepositoryAPI
) : BaseDetailViewModel<Payment>() {
    override fun insert(item: Payment): TaskLiveData<DocumentReference> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override var saved: Payment
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
        set(value) {}

    override fun getItem(id: String) = paymentRepository.get(id)

//    override fun insert(item: Payment) = paymentRepository.insert(item)
//    override fun insert(item: Payment) = paymentRepository.insert(item)

    override fun update(item: Payment) = paymentRepository.update(
        oldPayment = currentItem.value!!,
        newPayment = item
    )

    override fun validate(item: Payment?) {
        when {
            item == currentItem.value -> {
                _formState.value = CategoryViewFormState(
                    nameError = null
                ).apply {
                    isChanged = false
                    isDataValid = true
                }
            }
        }
    }
}