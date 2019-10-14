package fit.tdc.edu.vn.cafemanagement.ui.unit_list

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.adapter.UnitAdapter
import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.Unit
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.unit_viewmodel.UnitViewModel
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.unit_viewmodel.UnitViewModelFactory
import fit.tdc.edu.vn.cafemanagement.ui.unit_create.UnitViewActivity
import kotlinx.android.synthetic.main.activity_unit_list.*

class UnitListFragment : Fragment() {

    var adapter = UnitAdapter()

    companion object {
        fun newInstance() = UnitListFragment()
    }

    private lateinit var unitViewModel: UnitViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater!!.inflate(R.layout.activity_unit_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.setTitle(R.string.donVi)

        //add unit
        btnAddUnit.setOnClickListener {
            activity?.let {
                val intent = Intent(it, UnitViewActivity::class.java)
                it.startActivity(intent)
            }
        }

        recycler_view.layoutManager = LinearLayoutManager(context)
        recycler_view.setHasFixedSize(true)
        recycler_view.adapter = adapter

        unitViewModel =
            ViewModelProviders.of(this, UnitViewModelFactory()).get(UnitViewModel::class.java)
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
                val intent = Intent(context, UnitViewActivity::class.java)
                intent.putExtra(UnitViewActivity.EXTRA_ID, unit.id)
                intent.putExtra(UnitViewActivity.EXTRA_NAME, unit.name)
                startActivity(intent)
            }
        })
    }
}
