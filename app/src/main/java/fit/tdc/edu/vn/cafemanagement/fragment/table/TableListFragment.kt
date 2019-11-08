package fit.tdc.edu.vn.cafemanagement.fragment.table

import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.FireBaseDataSource
import fit.tdc.edu.vn.cafemanagement.data.model.table.Table
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.table.TableListViewModel
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.table.TableViewModelFactory
import fit.tdc.edu.vn.cafemanagement.fragment.BaseListFragment
import fit.tdc.edu.vn.cafemanagement.fragment.BaseListViewModel
import kotlinx.android.synthetic.main.fragment_empty.*

class TableListFragment : BaseListFragment<Table>(
    R.layout.fragment_list,
    viewAdapter = TableAdapter()
) {
    override val navController: NavController
        get() = findNavController()

    override fun setupFab(fab: FloatingActionButton) {
        fab.setOnClickListener {
            navController.navigate(
                TableListFragmentDirections.actionViewTable(
                    tableId = null,
                    title = getString(R.string.title_table_create)
                )
            )
        }
        btn_add_new.setOnClickListener {
            navController.navigate(
                TableListFragmentDirections.actionViewTable(
                    tableId = null,
                    title = getString(R.string.title_table_create)
                )
            )
        }
    }

    override fun showDeleteNotifySnackBar(item: Table, view: View) {
        Snackbar.make(
            view,
            "${item.name} đã bị xóa!",
            Snackbar.LENGTH_LONG
        ).show()
    }

    override val viewModel: BaseListViewModel<Table>
        get() = ViewModelProvider(
            this,
            TableViewModelFactory(FireBaseDataSource(), this)
        ).get<TableListViewModel>()
}
