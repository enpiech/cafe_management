package fit.tdc.edu.vn.cafemanagement.fragment.order_list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.adapter.OrderAdapter
import fit.tdc.edu.vn.cafemanagement.data.adapter.TableAdapter
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.FireBaseDataSource
import fit.tdc.edu.vn.cafemanagement.data.model.category.Category
import fit.tdc.edu.vn.cafemanagement.data.model.order.Order
import fit.tdc.edu.vn.cafemanagement.data.model.table.Table
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.payment.PaymentListViewModel
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.payment.PaymentViewModelFactory
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.table.TableListViewModel
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.table.TableViewModelFactory
import fit.tdc.edu.vn.cafemanagement.fragment.BaseListFragment
import fit.tdc.edu.vn.cafemanagement.fragment.BaseListViewModel
import fit.tdc.edu.vn.cafemanagement.fragment.table.TableListFragmentDirections

class OrderListFragment : BaseListFragment<Category>(
    R.layout.fragment_list,
    viewAdapter = OrderAdapter()
) {
    override val viewModel: BaseListViewModel<Category>
        get() = ViewModelProvider(
            this,
            PaymentViewModelFactory(FireBaseDataSource(), this)
        ).get<PaymentListViewModel>()

    override fun showDeleteNotifySnackBar(item: Category, view: View) {

    }

    override val navController: NavController
        get() = findNavController()

    override fun setupFab(fab: FloatingActionButton) {
        fab.setOnClickListener {
//            navController.navigate(
//                TableListFragmentDirections.tableViewAction(
//                    tableId = null,
//                    title = "Tạo Table"
//                )
//            )
            Log.d("test", "Bạn vừa bấm vào button")
        }
    }


}
