package fit.tdc.edu.vn.cafemanagement.ui.zone_list

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.adapter.ZoneAdapter
import fit.tdc.edu.vn.cafemanagement.data.model.zone.Zone
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.zone_viewmodel.ZoneViewModel
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.zone_viewmodel.ZoneViewModelFactory
import fit.tdc.edu.vn.cafemanagement.ui.unit_create.UnitViewActivity
import kotlinx.android.synthetic.main.activity_unit_list.recycler_view
import kotlinx.android.synthetic.main.list_fragment.*

class ZoneListFragment : Fragment(R.layout.list_fragment) {

    var adapter = ZoneAdapter()

    companion object {
        fun newInstance() = ZoneListFragment()
    }

    private lateinit var viewModel: ZoneViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnAdd.setOnClickListener {
            activity?.let {
                val intent = Intent(it, UnitViewActivity::class.java)
                it.startActivity(intent)
            }
        }

        recycler_view.layoutManager = LinearLayoutManager(context)
        recycler_view.setHasFixedSize(true)
        recycler_view.adapter = adapter

        viewModel =
            ViewModelProvider(this, ZoneViewModelFactory()).get(ZoneViewModel::class.java)
        viewModel.getAllZones().observe(this, Observer {
//            Log.d("test", "${it.data?.size}")
            adapter.submitList(it.data)
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
                        viewModel.delete(adapter.getUnitAt(viewHolder.adapterPosition))
                        Toast.makeText(
                            context,
                            "Đơn vị bạn chọn đã bị xóa!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    setNegativeButton("Hủy") { p0, p1 ->
                        adapter.notifyDataSetChanged()
                        Toast.makeText(context, "Hủy", Toast.LENGTH_SHORT).show()
                    }
                    show()
                }
            }
        }
        ).attachToRecyclerView(recycler_view)

        adapter.setOnItemClickListener(object : ZoneAdapter.OnItemClickListener {
            override fun onItemClick(zone: Zone) {
                val action = ZoneListFragmentDirections.viewZoneAction(zone.id)
                findNavController().navigate(action)
//                val intent = Intent(context, UnitViewActivity::class.java)
//                intent.putExtra(UnitViewActivity.EXTRA_ID, zone.id)
//                intent.putExtra(UnitViewActivity.EXTRA_NAME, zone.name)
//                startActivity(intent)
            }
        })
    }
}
