package fit.tdc.edu.vn.cafemanagement.fragment.table

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.adapter.TableWaiterAdapter
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.FireBaseDataSource
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.table.TableListViewModel
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.table.TableViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_list.*

class TableListWaiterFragment : Fragment(R.layout.fragment_list) {

    var viewAdapter = TableWaiterAdapter()

    private val viewModel by lazy {
        ViewModelProvider(
            this,
            TableViewModelFactory(FireBaseDataSource(), this)
        ).get<TableListViewModel>()
    }

    companion object {
        fun newInstance() = TableListWaiterFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().fab.hide()

        recycler_view.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)

            adapter = viewAdapter
        }

        viewModel.getAllItems().observe(viewLifecycleOwner, Observer {
            viewAdapter.submitList(it.data)
        })
    }
}