package fit.tdc.edu.vn.cafemanagement.fragment.table_order_waiter

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.adapter.TableOrderWaiterAdapter
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.table_order_waiter.TableOrderWaiterListViewModel
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.table_order_waiter.TableOrderWaiterViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_list.*

class TableOrderWaiterFragment : Fragment(R.layout.fragment_list) {

    var viewAdapter = TableOrderWaiterAdapter()
    private val viewModel by lazy {
        ViewModelProvider(
            this,
            TableOrderWaiterViewModelFactory()
        ).get<TableOrderWaiterListViewModel>()
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
