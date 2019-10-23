package fit.tdc.edu.vn.cafemanagement.fragment.category

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
import fit.tdc.edu.vn.cafemanagement.data.adapter.CategoryAdapter
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.category_viewmodel.CategoryViewModel
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.category_viewmodel.CategoryViewModelFactory
import fit.tdc.edu.vn.cafemanagement.fragment.zone.ZoneListFragmentDirections
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_list.*

class CategoryListFragment : Fragment(R.layout.fragment_list) {

    private val categoryAdapter = CategoryAdapter()

    companion object {
        fun newInstance() =
            CategoryListFragment()
    }

    private lateinit var viewModel: CategoryViewModel

    private val btn by lazy {
        requireActivity().fab
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn.setOnClickListener {
            findNavController().navigate(ZoneListFragmentDirections.zoneViewAction(null))
        }

        recycler_view.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = categoryAdapter
        }
        viewModel =
            ViewModelProvider(this, CategoryViewModelFactory()).get()
        viewModel.getAllItems().observe(this, Observer {
            categoryAdapter.submitList(it.data)
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
                            categoryAdapter.getCategoryAt(viewHolder.adapterPosition).apply {
                                viewModel.delete(this)
                                Snackbar.make(
                                    viewHolder.itemView,
                                    "Danh mục ${this.name} đã bị xóa!",
                                    Snackbar.LENGTH_LONG
                                ).show()
                            }
                        }
                    }
                    .setNegativeButton(R.string.btnCancel) { _, _ ->
                        categoryAdapter.notifyDataSetChanged()
                    }
                    .show()
            }
        }
        ).attachToRecyclerView(recycler_view)
    }
}
