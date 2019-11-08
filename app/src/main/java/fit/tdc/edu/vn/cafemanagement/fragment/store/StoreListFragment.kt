package fit.tdc.edu.vn.cafemanagement.fragment.store

import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.FireBaseDataSource
import fit.tdc.edu.vn.cafemanagement.data.model.store.Store
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.store.StoreListViewModel
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.store.StoreViewModelFactory
import fit.tdc.edu.vn.cafemanagement.fragment.BaseListFragment
import fit.tdc.edu.vn.cafemanagement.fragment.BaseListViewModel
import kotlinx.android.synthetic.main.fragment_empty.*

class StoreListFragment : BaseListFragment<Store>(
    R.layout.fragment_list,
    viewAdapter = StoreAdapter()
) {
    override fun setupFab(fab: FloatingActionButton) {
        fab.setOnClickListener {
            navController.navigate(
                StoreListFragmentDirections.actionViewStore(
                    null,
                    getString(R.string.title_store_create)
                )
            )
        }
        btn_add_new.setOnClickListener {
            navController.navigate(
                StoreListFragmentDirections.actionViewStore(
                    null,
                    getString(R.string.title_store_create)
                )
            )
        }
    }

    override val viewModel: BaseListViewModel<Store>
        get() = ViewModelProvider(
            this,
            StoreViewModelFactory(FireBaseDataSource(), this)
        ).get<StoreListViewModel>()
    override val navController: NavController
        get() = findNavController()

    override fun showDeleteNotifySnackBar(item: Store, view: View) {
        Snackbar.make(
            view,
            "Cửa hàng ${item.name} đã bị xóa!",
            Snackbar.LENGTH_LONG
        ).show()
    }

    override fun onDeleteItem(item: Store, view: View) {
        if (item.managerId == null) {
            super.onDeleteItem(item, view)
        } else {
            MaterialAlertDialogBuilder(context)
                .setTitle(R.string.dialog_title_delete)
                .setMessage("${getString(R.string.warning_message_delete)}. ${getString(R.string.warning_message_delete_store_has_manager)}")
                .setPositiveButton(R.string.btnOK) { _, _ ->
                    viewModel.delete(item)
                    showDeleteNotifySnackBar(item, view)
                }
                .setNegativeButton(R.string.btnCancel) { _, _ ->
                    viewAdapter.notifyDataSetChanged()
                }
                .show()
        }
    }
}
