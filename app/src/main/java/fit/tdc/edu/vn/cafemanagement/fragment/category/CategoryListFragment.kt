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
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.category.CategoryListViewModel
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.category.CategoryViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_list.*

//class CategoryListFragment : BaseListFragment<Category>(R.layout.fragment_list) {
//    override val viewAdapter: ListAdapter<Category, RecyclerView.ViewHolder>
//        get() = CategoryAdapter()
//    override val viewModel: BaseListViewModel<Category>
//        get() = ViewModelProvider(this, CategoryViewModelFactory()).get<CategoryListViewModel>()
//    override val navController: NavController
//        get() = findNavController()
//
//    override fun showDeleteNotifySnackBar(item: Category, view: View) {
//        Snackbar.make(
//            view,
//            "Danh mục ${item.name} đã bị xóa!",
//            Snackbar.LENGTH_LONG
//        ).show()
//    }
//}

class CategoryListFragment : Fragment(R.layout.fragment_list) {

    var viewAdapter = CategoryAdapter()

    private val viewModel by lazy {
        ViewModelProvider(this, CategoryViewModelFactory()).get<CategoryListViewModel>()
    }

    companion object {
        fun newInstance() = CategoryListFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().fab.setOnClickListener {
            findNavController().navigate(
                CategoryListFragmentDirections.categoryViewAction(
                    categoryId = null
                )
            )
        }

        recycler_view.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)

            adapter = viewAdapter
        }

        viewModel.getAllItems().observe(this, Observer {
            viewAdapter.submitList(it.data)
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
