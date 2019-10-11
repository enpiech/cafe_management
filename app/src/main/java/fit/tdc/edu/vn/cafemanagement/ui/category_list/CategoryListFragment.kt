package fit.tdc.edu.vn.cafemanagement.ui.category_list

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
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

class CategoryListFragment : /*Fragment()*/ AppCompatActivity() {

    var adapter = CategoryAdapter()

    companion object {
        //fun newInstance() = CategoryListFragment()
        const val ADD_CATEGORY_REQUEST = 1
        const val EDIT_CATEGORY_REQUEST = 2
    }

    private lateinit var viewModel: CategoryViewModel

    /*override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.category_list_fragment, container, false)
    }*/

    /*override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //Add Category
        btnAddCategory.setOnClickListener {
            startActivity(


            )
        }
        viewModel = ViewModelProviders.of(this, CategoryViewModelFactory()).get(CategoryViewModel::class.java)
        // TODO: Use the ViewModel
    }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.category_list_fragment)
        setTitle(R.string.danhMuc)
        btnAddCategory.setOnClickListener {
            startActivityForResult(
                Intent(this, CategoryViewActivity::class.java),
                ADD_CATEGORY_REQUEST
            )
        }

        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(true)

        recycler_view.adapter = adapter

        viewModel = ViewModelProviders.of(this, CategoryViewModelFactory()).get(CategoryViewModel::class.java)

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
                val builder = AlertDialog.Builder(this@CategoryListFragment)
                with(builder) {
                    setTitle("Xóa")
                    setMessage("Bạn có muốn xóa thông tin này không?")
                    setPositiveButton("OK") { p0, p1 ->
                        viewModel.delete(adapter.getCategoryAt(viewHolder.adapterPosition))
                        Toast.makeText(
                            baseContext,
                            "Danh mục bạn chọn đã bị xóa!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    setNegativeButton("Hủy") { p0, p1 ->
                        adapter.notifyDataSetChanged()
                        Toast.makeText(baseContext, "Hủy", Toast.LENGTH_SHORT).show()
                    }
                    show()
                }
            }
        }
        ).attachToRecyclerView(recycler_view)

        adapter.setOnItemClickListener(object : CategoryAdapter.OnItemClickListener {
            override fun onItemClick(category: Category) {
                val intent = Intent(baseContext, CategoryViewActivity::class.java)
                intent.putExtra(CategoryViewActivity.EXTRA_ID, category.id)
                intent.putExtra(CategoryViewActivity.EXTRA_NAME, category.name)
                startActivityForResult(intent, EDIT_CATEGORY_REQUEST)
            }


        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == ADD_CATEGORY_REQUEST && resultCode == Activity.RESULT_OK) {
            val newCate = Category(
                data!!.getStringExtra(CategoryViewActivity.EXTRA_NAME)
            )
            viewModel.insert(newCate)
            Toast.makeText(this, "Đã lưu danh mục!", Toast.LENGTH_SHORT).show()
        } else if (requestCode == EDIT_CATEGORY_REQUEST && resultCode == Activity.RESULT_OK) {
            val id = data?.getStringExtra(CategoryViewActivity.EXTRA_ID)

            if (id == "") {
                Toast.makeText(this, "Could not update! Error!", Toast.LENGTH_SHORT).show()
            }

            val updateCategory = Category(
                data!!.getStringExtra(CategoryViewActivity.EXTRA_NAME)
            )
            updateCategory.id = data.getStringExtra(CategoryViewActivity.EXTRA_ID) ?: ""
            viewModel.update(updateCategory)
        } else {
            Toast.makeText(this, "Chưa lưu danh mục!", Toast.LENGTH_SHORT).show()
        }
    }
}
