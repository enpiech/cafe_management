package fit.tdc.edu.vn.cafemanagement.data.viewmodel.payment

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.FireBaseAPI
import fit.tdc.edu.vn.cafemanagement.data.repository.impl.MaterialRepository
import fit.tdc.edu.vn.cafemanagement.data.repository.impl.OrderRepository
import fit.tdc.edu.vn.cafemanagement.data.repository.impl.PaymentRepository

class PaymentViewModelFactory (
    private val dataSource: FireBaseAPI,
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
//        if (modelClass.isAssignableFrom(PaymentDetailViewModel::class.java)) {
//            return PaymentDetailViewModel(
//                paymentRepository = PaymentRepository(
//                    dataSource = dataSource
//                )
//            ) as T
//        } else
            if (modelClass.isAssignableFrom(PaymentListViewModel::class.java)) {
            return PaymentListViewModel(
                paymentRepository = PaymentRepository(
                    dataSource = dataSource
                ),
                materialRepository = MaterialRepository(
                    dataSource
                ),
                orderRepository = OrderRepository(dataSource),
                handle = handle
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}