package fit.tdc.edu.vn.cafemanagement.fragment.table_waiter

import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.adapter.TableWaiterAdapter
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.FireBaseDataSource
import fit.tdc.edu.vn.cafemanagement.data.model.table.Table
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.table_waiter.TableWaiterListViewModel
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.table_waiter.TableWaiterViewModelFactory
import fit.tdc.edu.vn.cafemanagement.fragment.BaseListFragment
import fit.tdc.edu.vn.cafemanagement.fragment.BaseListViewModel

class TableWaiterListFragment: BaseListFragment<Table>(
    R.layout.fragment_list,
    viewAdapter = TableWaiterAdapter()
) {
    override val navController: NavController
        get() = findNavController()

    override fun setupFab(fab: FloatingActionButton) {
        fab.setOnClickListener {
            //TODO: Hiển thị dialog các món đã đặt và button xác nhận
        }
    }

    override fun showDeleteNotifySnackBar(item: Table, view: View) {

    }

    override fun setupSwipeToDelete() {

    }

    override val viewModel: BaseListViewModel<Table>
        get() = ViewModelProvider(
            this,
            TableWaiterViewModelFactory(FireBaseDataSource(), this)
        ).get<TableWaiterListViewModel>()
}
