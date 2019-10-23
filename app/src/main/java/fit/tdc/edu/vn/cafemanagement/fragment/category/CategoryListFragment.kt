package fit.tdc.edu.vn.cafemanagement.fragment.category

import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.adapter.CategoryAdapter
import fit.tdc.edu.vn.cafemanagement.data.model.category.Category
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.category_viewmodel.CategoryListViewModel
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.category_viewmodel.CategoryViewModelFactory
import fit.tdc.edu.vn.cafemanagement.fragment.BaseListFragment
import fit.tdc.edu.vn.cafemanagement.fragment.BaseListViewModel

class CategoryListFragment : BaseListFragment<Category>(R.layout.fragment_list) {
    override val viewAdapter: ListAdapter<Category, RecyclerView.ViewHolder>
        get() = CategoryAdapter()
    override val viewModel: BaseListViewModel<Category>
        get() = ViewModelProvider(this, CategoryViewModelFactory()).get<CategoryListViewModel>()
    override val navController: NavController
        get() = findNavController()

    override fun showDeleteNotifySnackBar(item: Category, view: View) {
        Snackbar.make(
            view,
            "Danh mục ${item.name} đã bị xóa!",
            Snackbar.LENGTH_LONG
        ).show()
    }
}
