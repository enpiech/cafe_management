package fit.tdc.edu.vn.cafemanagement.fragment.zone

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.adapter.ZoneAdapter
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.zone_viewmodel.ZoneViewModel
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.zone_viewmodel.ZoneViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_list.*

class ZoneListFragment : Fragment(R.layout.fragment_list) {

    var viewAdapter = ZoneAdapter()
    private val viewModel by lazy {
        ViewModelProvider(this, ZoneViewModelFactory()).get<ZoneViewModel>()
    }

    companion object {
        fun newInstance() = ZoneListFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().fab.setOnClickListener {
            findNavController().navigate(ZoneListFragmentDirections.zoneViewAction(null))
        }

        recycler_view.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)

            adapter = viewAdapter
        }

        viewModel.getAllZones().observe(this, Observer {
            viewAdapter.submitList(it.data)
        })

        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            0, ItemTouchHelper.LEFT.or(
                ItemTouchHelper.RIGHT
            )
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                MaterialAlertDialogBuilder(context)
                    .setTitle(R.string.dialog_title_delete)
                    .setMessage(R.string.warning_message_delete)
                    .setPositiveButton(R.string.btnOK) { _, _ ->
                        run {
                            viewAdapter.getZoneAt(viewHolder.adapterPosition).apply {
                                viewModel.delete(this)
                                Snackbar.make(
                                    viewHolder.itemView,
                                    "Khu vực ${this.name} đã bị xóa!",
                                    Snackbar.LENGTH_LONG
                                ).show()
                            }
                        }
                    }
                    .setNegativeButton(R.string.btnCancel) { _, _ ->
                        viewAdapter.notifyDataSetChanged()
                    }
                    .show()
            }
        }
        ).attachToRecyclerView(recycler_view)
    }
}
