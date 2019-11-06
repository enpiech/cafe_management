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
import com.google.android.material.floatingactionbutton.FloatingActionButton
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.adapter.OrderAdapter
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.FireBaseDataSource
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.payment.PaymentListViewModel
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.payment.PaymentViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.fragment_order_detail.*

class OrderDetailFragment : Fragment(R.layout.fragment_order_detail) {

    lateinit var viewModel: PaymentListViewModel
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(
            requireActivity(),
            PaymentViewModelFactory(FireBaseDataSource(), this)
        ).get()

        if (paymentId.isNullOrBlank()) {
            navController.navigate(
                OrderDetailFragmentDirections.addOrderAction(
                    tableId!!,
                    "Thêm món"
                )
            )
        } else {
            viewModel.getCurrentPayment(paymentId)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



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
            viewModel.clearSaveState()
            navController.navigateUp()
        }

        recycler_view.apply {
            setHasFixedSize(false)
            layoutManager = LinearLayoutManager(context)
            adapter = viewAdapter
        }

//        viewModel.currentPayment.observe(viewLifecycleOwner, Observer { payment ->
//            viewAdapter.submitList(payment.orderList)
//            price.text = payment.total.toString()
//        })

        viewModel.currentOrder.observe(viewLifecycleOwner, Observer {list ->
            viewAdapter.submitList(list)
            price.text = "${list.map { it.amount * it.price }.sum()}"
        })

        btn_add.setOnClickListener {
            viewModel.saved = mapOf()
            navController.navigate(
                OrderDetailFragmentDirections.addOrderAction(
                    tableId = tableId!!,
                    title = "Thêm món"
                )
            )
        }

        btn_checkout.setOnClickListener {
            MaterialAlertDialogBuilder(context)
                .setTitle(R.string.dialog_title_delete)
                .setMessage(R.string.warning_message_delete)
                .setPositiveButton(R.string.btnOK) { _, _ ->
                    viewModel.saved = mapOf()
                    navController.navigateUp()
                }
                .setNegativeButton(R.string.btnCancel) { _, _ ->
                    viewAdapter.notifyDataSetChanged()
                }
                .show()

        }
    }
}