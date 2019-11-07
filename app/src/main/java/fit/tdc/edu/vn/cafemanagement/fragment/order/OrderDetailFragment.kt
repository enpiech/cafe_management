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
import com.google.android.material.floatingactionbutton.FloatingActionButton
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.adapter.OrderAdapter
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.FireBaseDataSource
import fit.tdc.edu.vn.cafemanagement.data.extension.Status
import fit.tdc.edu.vn.cafemanagement.data.extension.TaskStatus
import fit.tdc.edu.vn.cafemanagement.data.extension.observeUntil
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.payment.PaymentListViewModel
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.payment.PaymentViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.fragment_detail_order.*

class OrderDetailFragment : Fragment(R.layout.fragment_detail_order) {

    val viewModel by lazy {
        ViewModelProvider(
            this,
            PaymentViewModelFactory(FireBaseDataSource(), this)
        ).get<PaymentListViewModel>()
    }
    val navController: NavController
        get() = findNavController()

    val fab: FloatingActionButton by lazy {
        requireActivity().fab
    }

    private val toolbar: Toolbar by lazy {
        requireActivity().toolbar
    }

    private val args by navArgs<OrderDetailFragmentArgs>()

    val paymentId: String? by lazy {
        args.paymentId
    }

    val tableId: String? by lazy {
        args.tableId
    }

    val viewAdapter = OrderAdapter(OrderAdapter.LayoutType.CHECKOUT)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getCurrentTable(tableId)
        viewModel.currentTable.observe(viewLifecycleOwner, Observer { table ->
            if (table.status == Status.SUCCESS) {
                viewModel.getCurrentPayment(table.data?.paymentId)
                viewModel.currentOrder.observe(viewLifecycleOwner, Observer { list ->
                    Log.d("test", list.toString())
                    if (!list.isNullOrEmpty()) {
                        viewAdapter.submitList(list)
                        input_price.text = "${list.map { it.amount * it.price }.sum()}"
                    } else {
                        lbl_list.visibility = View.GONE
                        list_layout.visibility = View.GONE
                        checkout_panel.visibility = View.GONE
                    }
                })
            }
        })

        fab.setOnClickListener {
            navController.navigateUp()
        }
        fab.hide()

        val callback = requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            viewModel.clearSaveState()
            navController.navigateUp()
        }
        callback.isEnabled
        toolbar.setNavigationOnClickListener {
            navController.navigateUp()
//            viewModel.clearSaveState()
        }

        recycler_view.apply {
            setHasFixedSize(false)
            layoutManager = LinearLayoutManager(context)
            adapter = viewAdapter
        }

        viewModel.currentPayment.observe(viewLifecycleOwner, Observer { payment ->
            //            viewAdapter.submitList(payment.orderList)
//            price.text = payment.total.toString()
            btn_checkout.isEnabled = payment.data != null
            if (payment.data != null) {
                btn_checkout.setOnClickListener {
                    MaterialAlertDialogBuilder(context)
                        .setTitle(R.string.dialog_title_delete)
                        .setMessage(R.string.warning_message_delete)
                        .setPositiveButton(R.string.btnOK) { _, _ ->
                            viewModel.saved = mapOf()
                            viewModel.checkout(payment.data)

                        }
                        .setNegativeButton(R.string.btnCancel) { _, _ ->
                            viewAdapter.notifyDataSetChanged()
                        }
                        .show()

                }
            } else {
                btn_checkout.setOnClickListener { }
            }
        })

        viewModel.completeCheckout.observeUntil(viewLifecycleOwner, Observer {
            navController.navigateUp()
        }) {
            it == TaskStatus.SUCCESS
        }

        btn_add.setOnClickListener {
            viewModel.saved = mapOf()
            navController.navigate(
                OrderDetailFragmentDirections.addOrderAction(
                    tableId = tableId!!,
                    paymentId = paymentId,
                    title = "Thêm món"
                )
            )
        }
    }
}