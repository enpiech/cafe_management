package fit.tdc.edu.vn.cafemanagement.fragment.category

import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.FireBaseDataSource
import fit.tdc.edu.vn.cafemanagement.data.model.category.Category
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.category.CategoryListViewModel
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.category.CategoryViewModelFactory
import fit.tdc.edu.vn.cafemanagement.fragment.BaseListFragment
import fit.tdc.edu.vn.cafemanagement.fragment.BaseListViewModel
import kotlinx.android.synthetic.main.fragment_empty.*

class CategoryListFragment : BaseListFragment<Category>(
    R.layout.fragment_list,
    CategoryAdapter(
        resId = R.layout.item_category,
        onItemClick = { category: Category, it: View ->
            val direction =
                CategoryListFragmentDirections.actionViewCategory(category.id, "Chỉnh sửa danh mục")
            it.findNavController().navigate(direction)
        }
    )
) {
    override val emptyWarning: Int?
        get() = R.string.empty_category
    override val viewModel: BaseListViewModel<Category>
        get() = ViewModelProvider(
            this,
            CategoryViewModelFactory(FireBaseDataSource(), this)
        ).get<CategoryListViewModel>()
    override val navController: NavController
        get() = findNavController()

    override fun setupFab(fab: FloatingActionButton) {
        fab.setOnClickListener {
            navController.navigate(
                CategoryListFragmentDirections.actionViewCategory(
                    categoryId = null,
                    title = getString(R.string.title_category_create)
                )
            )
        }
        btn_add_new.setOnClickListener {
            navController.navigate(
                CategoryListFragmentDirections.actionViewCategory(
                    categoryId = null,
                    title = getString(R.string.title_category_create)
                )
            )
        }
    }

    override fun showDeleteNotifySnackBar(item: Category, view: View) {
        Snackbar.make(
            view,
            "Danh mục ${item.name} đã bị xóa!",
            Snackbar.LENGTH_LONG
        ).show()
    }
}
