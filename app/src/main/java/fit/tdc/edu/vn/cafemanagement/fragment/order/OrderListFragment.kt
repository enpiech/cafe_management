package fit.tdc.edu.vn.cafemanagement.fragment.order

import android.os.Bundle
import android.util.Log
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
import fit.tdc.edu.vn.cafemanagement.data.adapter.OrderAdapter
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.FireBaseDataSource
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.payment.PaymentListViewModel
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.payment.PaymentViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_list.*

class OrderListFragment : Fragment(R.layout.fragment_list) {

    lateinit var viewModel: PaymentListViewModel
    val navController: NavController
        get() = findNavController()

    private val viewAdapter = OrderAdapter { item ->
        viewModel.updateOrders(item)
        if (viewModel.saved.isNullOrEmpty()) {
            fab.hide()
        } else {
            fab.show()
        }
    }

    private val args by navArgs<OrderListFragmentArgs>()

    val tableId: String? by lazy {
        args.tableId
    }

    private val fab by lazy {
        requireActivity().fab
    }

    private val toolbar: Toolbar by lazy {
        requireActivity().toolbar
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(
            requireActivity(),
            PaymentViewModelFactory(FireBaseDataSource(), this)
        ).get()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val callback = requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
//            viewModel.clearSaveState()
            if (viewModel.currentOrder.value == null || viewModel.currentOrder.value!!.isEmpty()) {
                navController.navigate(OrderListFragmentDirections.cancelOrderAction())
            } else {
                navController.navigateUp()
            }
        }
        callback.isEnabled = true
        toolbar.setNavigationOnClickListener {
            Log.d("test", viewModel.currentOrder.value.toString())
            if (viewModel.currentOrder.value == null || viewModel.currentOrder.value!!.isEmpty()) {
                navController.navigate(OrderListFragmentDirections.cancelOrderAction())
            } else {
                navController.navigateUp()
            }
            viewModel.saved = mapOf()
        }

        recycler_view.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = viewAdapter
        }

        fab.setOnClickListener {
            MaterialAlertDialogBuilder(context)
                .setTitle(R.string.dialog_title_confirm)
                .setPositiveButton(R.string.btnOK) { _, _ ->
                    viewModel.createOrders(tableId)
//            viewModel.currentPayment.observe(viewLifecycleOwner, Observer {
//                viewModel.clearSaveState()
//                navController.navigateUp()
//            })
                    navController.navigateUp()
                }
                .setNegativeButton(R.string.btnCancel) { _, _ ->
//                    viewAdapter.notifyDataSetChanged()
                }
                .show()

        }

        viewModel.orders.observe(viewLifecycleOwner, Observer {
            viewAdapter.submitList(it)
        })
    }
}

