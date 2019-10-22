package fit.tdc.edu.vn.cafemanagement.fragment.table

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
import fit.tdc.edu.vn.cafemanagement.data.adapter.TableAdapter
import fit.tdc.edu.vn.cafemanagement.data.model.table.Table
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.table_viewmodel.TableViewModel
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.table_viewmodel.TableViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.item_table_each_zone.*

class TableListFragment : Fragment(R.layout.fragment_list) {

    var adapter = TableAdapter()

    companion object {
        fun newInstance() = TableListFragment()
    }

    private lateinit var tableViewModel: TableViewModel
    private val btn by lazy {
        requireActivity().fab
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //add unit
        btn.setOnClickListener {
            activity?.let {
                findNavController().navigate(TableListFragmentDirections.tableViewAction(tableId = null, title = "Tạo Table"))
            }
        }

        recycler_view.layoutManager = LinearLayoutManager(context)
        recycler_view.setHasFixedSize(true)
        recycler_view.adapter = adapter

        tableViewModel =
            ViewModelProvider(this, TableViewModelFactory()).get(TableViewModel::class.java)
        tableViewModel.getAlltables().observe(this, Observer {
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
                            adapter.getTableAt(viewHolder.adapterPosition).apply {
                                tableViewModel.delete(this)
                                Snackbar.make(
                                    viewHolder.itemView,
                                    "${this.name} đã bị xóa!",
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

        adapter.setOnItemClickListener(object : TableAdapter.OnItemClickListener {

            override fun onItemClick(table: Table) {
                findNavController().navigate(TableListFragmentDirections.tableViewAction(tableId = table.id, title = "Chỉnh sửa"))
            }
        })
    }
}
