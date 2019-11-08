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
import com.google.android.material.circularreveal.coordinatorlayout.CircularRevealCoordinatorLayout
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.floatingactionbutton.FloatingActionButton
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.extension.Status
import fit.tdc.edu.vn.cafemanagement.data.model.FirestoreModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_list.*

abstract class BaseListFragment<T: FirestoreModel>(
    @LayoutRes resId: Int,
    val viewAdapter: ListAdapter<T, RecyclerView.ViewHolder>
) : Fragment(resId) {
    abstract val viewModel: BaseListViewModel<T>
    abstract val navController: NavController
    private val fab: FloatingActionButton by lazy {
        requireActivity().fab
    }
    private val loading: CircularRevealCoordinatorLayout by lazy {
        requireActivity().loading
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupFab(fab)
        setupRecyclerView(recycler_view, viewAdapter)
        setupSwipeToDelete()
    }

    protected abstract fun setupFab(fab: FloatingActionButton)

    open fun setupRecyclerView(recyclerView: RecyclerView, viewAdapter: ListAdapter<T, RecyclerView.ViewHolder>) {
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = viewAdapter
        }

        viewModel.itemList.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.ERROR -> {
                    //TODO("Show error")
                    loading.visibility = View.GONE
                }
                Status.LOADING -> {
                    loading.visibility = View.VISIBLE
                }
                Status.SUCCESS -> {
                    loading.visibility = View.GONE
                    if(!it.data.isNullOrEmpty()){
                        viewAdapter.submitList(it.data)
                    }
                    no_item.visibility = if (it.data.isNullOrEmpty()) View.VISIBLE else View.GONE
                    fab.visibility = if (!it.data.isNullOrEmpty()) View.VISIBLE else View.GONE
                }
            }
        })

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
                val item = viewAdapter.currentList[viewHolder.adapterPosition]
                onDeleteItem(item, viewHolder.itemView)

            }
        }
        ).attachToRecyclerView(recycler_view)
    }

    open fun onDeleteItem(item: T, view: View) {
        MaterialAlertDialogBuilder(context)
            .setTitle(R.string.dialog_title_delete)
            .setMessage(R.string.warning_message_delete)
            .setPositiveButton(R.string.btnOK) { _, _ ->
                viewModel.delete(item)
                showDeleteNotifySnackBar(item, view)
            }
            .setNegativeButton(R.string.btnCancel) { _, _ ->
                viewAdapter.notifyDataSetChanged()
            }
            .show()
    }

    protected open fun showDeleteNotifySnackBar(item: T, view: View) {

    }
}