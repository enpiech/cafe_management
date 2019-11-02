package fit.tdc.edu.vn.cafemanagement.ui.zone

import android.app.AlertDialog
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
import com.google.android.material.snackbar.Snackbar
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.adapter.ZoneAdapter
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.zone_viewmodel.ZoneViewModel
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.zone_viewmodel.ZoneViewModelFactory
import fit.tdc.edu.vn.cafemanagement.ui.zone_list.ZoneListFragmentDirections
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

        btnAdd.setOnClickListener {
            findNavController().navigate(ZoneListFragmentDirections.viewZoneAction(null))
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
                val builder = AlertDialog.Builder(context)
                with(builder)
                {
                    setTitle("Xóa")
                    setMessage("Bạn có muốn xóa thông tin này không?")
                    setPositiveButton("OK") { p0, p1 ->
                        viewModel.delete(viewAdapter.getUnitAt(viewHolder.adapterPosition))
                        Snackbar.make(
                            viewHolder.itemView,
                            "Đơn vị bạn chọn đã bị xóa!",
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                    setNegativeButton("Hủy") { p0, p1 ->
                        viewAdapter.notifyDataSetChanged()
                        Snackbar.make(
                            viewHolder.itemView,
                            "Hủy",
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                    show()
                }
            }
        }
        ).attachToRecyclerView(recycler_view)
    }
}
