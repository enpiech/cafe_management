package fit.tdc.edu.vn.cafemanagement.fragment.dish

import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.material.MaterialRemoteDataSourceImpl
import fit.tdc.edu.vn.cafemanagement.data.model.material.Material
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.dish.DishListViewModel
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.dish.DishViewModelFactory
import fit.tdc.edu.vn.cafemanagement.fragment.BaseListFragment
import fit.tdc.edu.vn.cafemanagement.fragment.BaseListViewModel
import fit.tdc.edu.vn.cafemanagement.fragment.material.MaterialListFragmentDirections
import kotlinx.android.synthetic.main.fragment_empty.*

class DishListFragment : BaseListFragment<Material>(
    R.layout.fragment_list,
    viewAdapter = DishAdapter()
) {
    override val emptyWarning: Int?
        get() = R.string.empty_material

    override fun setupFab(fab: FloatingActionButton) {
        fab.setOnClickListener {
            navController.navigate(
                MaterialListFragmentDirections.actionViewMaterial(
                    materialId = null,
                    title = getString(R.string.title_material_create)
                )
            )
        }
        btn_add_new.setOnClickListener {
            navController.navigate(
                MaterialListFragmentDirections.actionViewMaterial(
                    materialId = null,
                    title = getString(R.string.title_material_create)
                )
            )
        }
    }

    override val viewModel: BaseListViewModel<Material>
        get() = ViewModelProvider(
            this,
            DishViewModelFactory(
                MaterialRemoteDataSourceImpl(),
                this
            )
        ).get<DishListViewModel>()
    override val navController: NavController
        get() = findNavController()

    override fun showDeleteNotifySnackBar(item: Material, view: View) {
        Snackbar.make(
            view,
            "${item.name} đã bị xóa!",
            Snackbar.LENGTH_LONG
        ).show()
    }
}
