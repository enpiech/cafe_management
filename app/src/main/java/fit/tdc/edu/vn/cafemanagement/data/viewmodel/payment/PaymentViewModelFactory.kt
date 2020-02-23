package fit.tdc.edu.vn.cafemanagement.data.viewmodel.payment

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.category.CategoryRemoteDatasourceImpl
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.material.MaterialRemoteDataSourceImpl
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.order.OrderRemoteDataSourceImpl
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.payment.PaymentRemoteDataSourceImpl
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.table.TableRemoteDataSourceImpl
import fit.tdc.edu.vn.cafemanagement.data.repository.category.CategoryRepositoryImpl
import fit.tdc.edu.vn.cafemanagement.data.repository.material.MaterialRepositoryImpl
import fit.tdc.edu.vn.cafemanagement.data.repository.order.OrderRepositoryImpl
import fit.tdc.edu.vn.cafemanagement.data.repository.payment.PaymentRepositoryImpl
import fit.tdc.edu.vn.cafemanagement.data.repository.table.TableRepositoryImpl

class PaymentViewModelFactory (
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        if (modelClass.isAssignableFrom(OrderListViewModel::class.java)) {
            return OrderListViewModel(
                // LOOSE
                paymentRepository = PaymentRepositoryImpl(
                    dataSource = PaymentRemoteDataSourceImpl()
                ),
                materialRepository = MaterialRepositoryImpl(
                    dataSource = MaterialRemoteDataSourceImpl()
                ),
                categoryRepository = CategoryRepositoryImpl(
                    dataSource = CategoryRemoteDatasourceImpl()
                ),
                orderRepository = OrderRepositoryImpl(
                    dataSource = OrderRemoteDataSourceImpl()
                ),
                handle = handle
            ) as T
        } else
            if (modelClass.isAssignableFrom(PaymentListViewModel::class.java)) {
            return PaymentListViewModel(
                paymentRepository = PaymentRepositoryImpl(
                    dataSource = PaymentRemoteDataSourceImpl()
                ),
                tableRepository = TableRepositoryImpl(
                    dataSource = TableRemoteDataSourceImpl()
                ),
                orderRepository = OrderRepositoryImpl(
                    dataSource = OrderRemoteDataSourceImpl()
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}