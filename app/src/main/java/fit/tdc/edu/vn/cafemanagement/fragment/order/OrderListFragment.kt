package fit.tdc.edu.vn.cafemanagement.fragment.order

import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.adapter.CategoryAdapter
import fit.tdc.edu.vn.cafemanagement.data.adapter.OrderAdapter
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.FireBaseDataSource
import fit.tdc.edu.vn.cafemanagement.data.extension.Status
import fit.tdc.edu.vn.cafemanagement.data.extension.TaskStatus
import fit.tdc.edu.vn.cafemanagement.data.extension.observeUntil
import fit.tdc.edu.vn.cafemanagement.data.model.category.Category
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.payment.OrderListViewModel
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.payment.PaymentViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.fragment_order_new.*

class OrderListFragment : Fragment(R.layout.fragment_order_new) {

    val viewModel: OrderListViewModel by lazy {
        ViewModelProvider(
            requireActivity(),
            PaymentViewModelFactory(FireBaseDataSource(), this)
        ).get<OrderListViewModel>()
    }
    val navController: NavController
        get() = findNavController()

    private val orderAdapter = OrderAdapter { item ->
        viewModel.updateOrders(item)
        if (viewModel.saved.isNullOrEmpty()) {
            fab.hide()
        } else {
            fab.show()
        }
    }

    private val categoryAdapter = CategoryAdapter(
        resId = R.layout.item_category_filter
    ) { category: Category, _: View ->
        viewModel.filterCategory(category.id)
    }

    private val args by navArgs<OrderListFragmentArgs>()

    val tableId: String? by lazy {
        args.tableId
    }

    val paymentId: String? by lazy {
        args.paymentId
    }

    private val fab by lazy {
        requireActivity().fab
    }

    private val toolbar: Toolbar by lazy {
        requireActivity().toolbar
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val callback = requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            //            viewModel.clearSaveState()
            if (paymentId == null) {
                navController.popBackStack(R.id.tableListWaiterFragment, true)
                navController.navigate(R.id.tableListWaiterFragment)
            } else {
                navController.navigateUp()
            }
        }
        callback.isEnabled = true
        toolbar.setNavigationOnClickListener {
            if (paymentId == null) {
                navController.popBackStack(R.id.tableListWaiterFragment, true)
                navController.navigate(R.id.tableListWaiterFragment)
            } else {
                navController.navigateUp()
            }
        }

        recycler_view.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = orderAdapter
        }

        list_category.apply {
            setHasFixedSize(false)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = categoryAdapter
        }

        fab.setOnClickListener {
            MaterialAlertDialogBuilder(context)
                .setTitle(R.string.dialog_title_confirm)
                .setPositiveButton(R.string.btnOK) { _, _ ->
                    viewModel.createOrders(tableId, paymentId)
                    viewModel.completeOrder.observeUntil(viewLifecycleOwner, Observer {
                        if (it?.status == TaskStatus.SUCCESS) navController.navigate(
                            OrderListFragmentDirections.actionOrderListFragmentToOrderDetailFragment(
                                paymentId = paymentId ?: it.data!!.id,
                                tableId = tableId!!,
                                title = "Thanh toán"
                            )
                        )
                    }) {
                        it?.status != TaskStatus.FAILED
                    }
                }
                .setNegativeButton(R.string.btnCancel) { _, _ ->

                }
                .show()

        }

        viewModel.orders.observe(viewLifecycleOwner, Observer {
            orderAdapter.submitList(it)
        })

        viewModel.categories.observe(viewLifecycleOwner, Observer {
            if (it.status == Status.SUCCESS) {
                categoryAdapter.submitList(it.data)
            }
        })
    }
}

