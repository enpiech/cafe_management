package fit.tdc.edu.vn.cafemanagement.ui.category_list

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.adapter.CategoryAdapter
import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.Category
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.category_viewmodel.CategoryViewModel
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.category_viewmodel.CategoryViewModelFactory
import fit.tdc.edu.vn.cafemanagement.ui.category_view.CategoryViewActivity
import kotlinx.android.synthetic.main.category_list_fragment.*

class CategoryListFragment : Fragment(R.layout.category_list_fragment) {

    var adapter = CategoryAdapter()

    companion object {

        fun newInstance(): CategoryListFragment {
            return CategoryListFragment()
        }

        const val ADD_CATEGORY_REQUEST = 1
        const val EDIT_CATEGORY_REQUEST = 2
    }

    private lateinit var viewModel: CategoryViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Add Category
        btnAddCategory.setOnClickListener {
            activity?.let {
                val intent = Intent(it, CategoryViewActivity::class.java)
                it.startActivity(intent)
            }
        }

        recycler_view.layoutManager = LinearLayoutManager(context)
        recycler_view.setHasFixedSize(true)

        recycler_view.adapter = adapter

        viewModel = ViewModelProviders.of(this, CategoryViewModelFactory())
            .get(CategoryViewModel::class.java)

        viewModel.getAllCategories().observe(this, Observer {
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
                with(builder) {
                    setTitle("Xóa")
                    setMessage("Bạn có muốn xóa thông tin này không?")
                    setPositiveButton("OK") { p0, p1 ->
                        viewModel.delete(adapter.getCategoryAt(viewHolder.adapterPosition))
                        Toast.makeText(
                            context,
                            "Danh mục bạn chọn đã bị xóa!",
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

        adapter.setOnItemClickListener(object : CategoryAdapter.OnItemClickListener {
            override fun onItemClick(category: Category) {
                val intent = Intent(context, CategoryViewActivity::class.java)
                intent.putExtra(CategoryViewActivity.EXTRA_ID, category.id)
                intent.putExtra(CategoryViewActivity.EXTRA_NAME, category.name)
                startActivityForResult(intent, EDIT_CATEGORY_REQUEST)
            }
        })
    }
}
