package fit.tdc.edu.vn.cafemanagement.fragment.table

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
import fit.tdc.edu.vn.cafemanagement.data.adapter.TableAdapter
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.table.TableListViewModel
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.table.TableViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_list.*

class TableListFragment : Fragment(R.layout.fragment_list) {

    var viewAdapter = TableAdapter()

    private val viewModel by lazy {
        ViewModelProvider(this, TableViewModelFactory()).get<TableListViewModel>()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().fab.setOnClickListener {
            findNavController().navigate(TableListFragmentDirections.tableViewAction(tableId = null, title = "Tạo Table"))
        }

        recycler_view.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)

            adapter = viewAdapter
        }

        viewModel.itemList.observe(this, Observer {
            viewAdapter.submitList(it)
            //Log.d("test", "cao"+it.data.toString())
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
                            viewAdapter.currentList[viewHolder.adapterPosition].apply {
                                viewModel.delete(this)
                                Snackbar.make(
                                    viewHolder.itemView,
                                    "${this.name} đã bị xóa!",
                                    Snackbar.LENGTH_LONG
                                ).show()
                            }
                        }
                    }
                    .setNegativeButton(R.string.btnCancel) { _, _ ->
                        viewAdapter.notifyItemChanged(viewHolder.adapterPosition)
                    }
                    .show()
            }
        }
        ).attachToRecyclerView(recycler_view)
    }
}
