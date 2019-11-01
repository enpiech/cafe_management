package fit.tdc.edu.vn.cafemanagement.fragment.unit

import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.adapter.UnitAdapter
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.FireBaseDataSource
import fit.tdc.edu.vn.cafemanagement.data.model.unit.Unit
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.unit.UnitListViewModel
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.unit.UnitViewModelFactory
import fit.tdc.edu.vn.cafemanagement.fragment.BaseListFragment
import fit.tdc.edu.vn.cafemanagement.fragment.BaseListViewModel
import kotlinx.android.synthetic.main.dropdown_menu_popup_item.*

//class UnitListFragment : BaseListFragment<Unit>(R.layout.fragment_list) {
//    override val viewAdapter: ListAdapter<Unit, RecyclerView.ViewHolder>
//        get() = UnitAdapter()
//    override val viewModel: BaseListViewModel<Unit>
//        get() = ViewModelProvider(this, UnitViewModelFactory()).get<UnitListViewModel>()
//    override val navController: NavController
//        get() = findNavController()
//
//    override fun showDeleteNotifySnackBar(item: Unit, view: View) {
//        Snackbar.make(
//            view,
//            "Đơn vị ${item.name} đã bị xóa!",
//            Snackbar.LENGTH_LONG
//        ).show()
//    }
//}

class UnitListFragment : BaseListFragment<Unit>(
    R.layout.fragment_list,
    viewAdapter = UnitAdapter()
) {
    override val viewModel: BaseListViewModel<Unit>
        get() = ViewModelProvider(
            this,
            UnitViewModelFactory(FireBaseDataSource(), this)
        ).get<UnitListViewModel>()

    override fun showDeleteNotifySnackBar(item: Unit, view: View) {
        Snackbar.make(
            view,
            "${item.name} đã bị xóa!",
            Snackbar.LENGTH_LONG
        ).show()
    }

    override val navController: NavController
        get() = findNavController()

    override fun setupFab(fab: FloatingActionButton) {
        fab.setOnClickListener {
            navController.navigate(
                UnitListFragmentDirections.unitViewAction(
                    unitId = null,
                    title = "Tạo Unit"
                )
            )
        }
    }


}
