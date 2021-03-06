package fit.tdc.edu.vn.cafemanagement.fragment.table_waiter

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.table.TableRemoteDataSourceImpl
import fit.tdc.edu.vn.cafemanagement.data.model.table.Table
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.table_waiter.TableWaiterListViewModel
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.table_waiter.TableWaiterViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_list.*

class TableWaiterListFragment :
    Fragment(R.layout.fragment_list),
    BottomNavigationView.OnNavigationItemSelectedListener {

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.all -> {
                viewModel.filterList(null)
                return true
            }
            R.id.checkout -> {
                viewModel.filterList(Table.State.BOOKED)
                return true
            }
        }
        return false
    }

    val navController: NavController
        get() = findNavController()

    fun setupFab(fab: FloatingActionButton) {
        fab.hide()
    }

    val viewModel: TableWaiterListViewModel by lazy {
        ViewModelProvider(
            this,
            TableWaiterViewModelFactory(TableRemoteDataSourceImpl(), this)
        ).get<TableWaiterListViewModel>()
    }
    private val viewAdapter =
        TableWaiterAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().loading.visibility = View.GONE

        val callback = requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {

        }
        callback.isEnabled = true
        setupFab(requireActivity().fab)
        setupRecyclerView(recycler_view, viewAdapter)
        requireActivity().bottom_navigation.setOnNavigationItemSelectedListener(this)
    }

    private fun setupRecyclerView(
        recyclerView: RecyclerView,
        viewAdapter: ListAdapter<Table, RecyclerView.ViewHolder>
    ) {
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = viewAdapter
        }

        viewModel.items.observe(viewLifecycleOwner, Observer {
            viewAdapter.submitList(it)
            no_item.visibility = if (it.isEmpty()) View.VISIBLE else View.GONE
        })
    }
}
