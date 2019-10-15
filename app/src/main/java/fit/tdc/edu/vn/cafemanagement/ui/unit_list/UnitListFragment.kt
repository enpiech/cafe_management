package fit.tdc.edu.vn.cafemanagement.ui.unit_list

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
import fit.tdc.edu.vn.cafemanagement.data.adapter.UnitAdapter
import fit.tdc.edu.vn.cafemanagement.data.model.unit.Unit
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.unit_viewmodel.UnitViewModel
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.unit_viewmodel.UnitViewModelFactory
import kotlinx.android.synthetic.main.unit_list_fragment.*

class UnitListFragment : Fragment(R.layout.unit_list_fragment) {

    var adapter = UnitAdapter()

    companion object {
        fun newInstance() = UnitListFragment()
    }

    private lateinit var unitViewModel: UnitViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //add unit
        btnAddUnit.setOnClickListener {
            activity?.let {
                findNavController().navigate(UnitListFragmentDirections.unitViewAction(unitId = null, title = "Tạo Unit"))
            }
        }

        recycler_view.layoutManager = LinearLayoutManager(context)
        recycler_view.setHasFixedSize(true)
        recycler_view.adapter = adapter

        unitViewModel =
            ViewModelProvider(this, UnitViewModelFactory()).get(UnitViewModel::class.java)
        unitViewModel.getAllUnits().observe(this, Observer {
            //Log.d("test", "${it.data?.size}")
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
                val builder = android.app.AlertDialog.Builder(context)
                with(builder)
                {
                    setTitle("Xóa")
                    setMessage("Bạn có muốn xóa thông tin này không?")
                    setPositiveButton("OK") { p0, p1 ->
                        unitViewModel.delete(adapter.getUnitAt(viewHolder.adapterPosition))
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

        adapter.setOnItemClickListener(object : UnitAdapter.OnItemClickListener {
            override fun onItemClick(unit: Unit) {
                findNavController().navigate(UnitListFragmentDirections.unitViewAction(unitId = unit.id, title = "Chỉnh sửa"))
            }
        })
    }
}
