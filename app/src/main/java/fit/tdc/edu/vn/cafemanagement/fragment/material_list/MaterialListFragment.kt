package fit.tdc.edu.vn.cafemanagement.fragment.material_list

import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.adapter.MaterialAdapter
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.FireBaseDataSource
import fit.tdc.edu.vn.cafemanagement.data.model.material.Material
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.material.MaterialListViewModel
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.material.MaterialViewModelFactory
import fit.tdc.edu.vn.cafemanagement.fragment.BaseListFragment
import fit.tdc.edu.vn.cafemanagement.fragment.BaseListViewModel

class MaterialListFragment : BaseListFragment<Material>(
    R.layout.fragment_list,
    viewAdapter = MaterialAdapter()
) {
    override fun setupFab(fab: FloatingActionButton) {
        fab.setOnClickListener {
            navController.navigate(MaterialListFragmentDirections.materialViewAction(materialId = null, title = getString(R.string.title_material_create)))
        }
    }

    override val viewModel: BaseListViewModel<Material>
        get() = ViewModelProvider(
            this,
            MaterialViewModelFactory(FireBaseDataSource(), this)
        ).get<MaterialListViewModel>()
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
