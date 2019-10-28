package fit.tdc.edu.vn.cafemanagement.fragment.table_order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.adapter.TableOrderWaiterAdapter
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.table_order_waiter_viewmodel.TableOrderWaiterViewModel
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.table_order_waiter_viewmodel.TableOrderWaiterViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.item_table_order_waiter.*

class TableOrderWaiterFragment : Fragment(R.layout.fragment_list) {

    var viewAdapter = TableOrderWaiterAdapter()
    var soluong = 0
    private val viewModel by lazy {
        ViewModelProvider(this, TableOrderWaiterViewModelFactory()).get<TableOrderWaiterViewModel>()
    }

    companion object {
        fun newInstance() = TableOrderWaiterFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().fab.hide()

        recycler_view.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)

            adapter = viewAdapter
        }

        viewModel.getAllItems().observe(this, Observer {
            viewAdapter.submitList(it.data)
        })
    }

}
