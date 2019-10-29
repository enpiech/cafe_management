package fit.tdc.edu.vn.cafemanagement.fragment

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.floatingactionbutton.FloatingActionButton
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.model.FirestoreModel
import fit.tdc.edu.vn.cafemanagement.fragment.zone.ZoneListFragmentDirections
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_list.*

abstract class BaseListFragment<T: FirestoreModel>(
    @LayoutRes resId: Int,
    private val viewAdapter: ListAdapter<T, RecyclerView.ViewHolder>
) : Fragment(resId) {
    abstract val viewModel: BaseListViewModel<T>
    abstract val navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupFab(requireActivity().fab)
        setupRecyclerView(recycler_view, viewAdapter)
        setupSwipeToDelete()
    }

    protected open fun setupFab(fab: FloatingActionButton ) {
        fab.setOnClickListener {
            navController.navigate(ZoneListFragmentDirections.zoneViewAction(null))
        }
    }

    open fun setupRecyclerView(recyclerView: RecyclerView, viewAdapter: ListAdapter<T, RecyclerView.ViewHolder>) {
        recycler_view.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = viewAdapter
        }

        viewModel.itemList.observe(viewLifecycleOwner, Observer {
            viewAdapter.submitList(it)
        })

    }

    open fun deleteItem(item: T, view: View) {
        viewModel.delete(item)
        showDeleteNotifySnackBar(item, view)
    }

    open fun setupSwipeToDelete() {
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
                                deleteItem(this, viewHolder.itemView)
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

    protected abstract fun showDeleteNotifySnackBar(item: T, view: View)
}