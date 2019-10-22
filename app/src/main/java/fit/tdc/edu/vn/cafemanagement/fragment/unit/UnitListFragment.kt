package fit.tdc.edu.vn.cafemanagement.fragment.unit

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.adapter.UnitAdapter
import fit.tdc.edu.vn.cafemanagement.data.model.unit.Unit
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.unit_viewmodel.UnitViewModel
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.unit_viewmodel.UnitViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_list.*

class UnitListFragment : Fragment(R.layout.fragment_list) {

    var adapter = UnitAdapter()

    companion object {
        fun newInstance() = UnitListFragment()
    }

    private lateinit var unitViewModel: UnitViewModel

    private val btn by lazy {
        requireActivity().fab
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //add unit
        btn.setOnClickListener {
            activity?.let {
                val direction =
                    UnitListFragmentDirections.unitViewAction(unitId = null, title = "Tạo Unit")
                findNavController().navigate(direction)
//                findNavController().navigate(UnitListFragmentDirections.unitViewAction(unitId = null, title = "Tạo Unit"))
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
                MaterialAlertDialogBuilder(context)
                    .setTitle(R.string.dialog_title_delete)
                    .setMessage(R.string.warning_message_delete)
                    .setPositiveButton(R.string.btnOK) { _, _ ->
                        run {
                            adapter.getUnitAt(viewHolder.adapterPosition).apply {
                                unitViewModel.delete(this)
                                Snackbar.make(
                                    viewHolder.itemView,
                                    "Đơn vị ${this.name} đã bị xóa!",
                                    Snackbar.LENGTH_LONG
                                ).show()
                            }
                        }
                    }
                    .setNegativeButton(R.string.btnCancel) { _, _ ->
                        adapter.notifyDataSetChanged()
                    }
                    .show()
            }
        }
        ).attachToRecyclerView(recycler_view)

        adapter.setOnItemClickListener(object : UnitAdapter.OnItemClickListener {
            override fun onItemClick(unit: Unit) {
                findNavController().navigate(
                    UnitListFragmentDirections.unitViewAction(
                        unitId = unit.id,
                        title = "Chỉnh sửa"
                    )
                )
            }
        })
    }
}
