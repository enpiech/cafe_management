package fit.tdc.edu.vn.cafemanagement.fragment.zone

import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.adapter.ZoneAdapter
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.FireBaseDataSource
import fit.tdc.edu.vn.cafemanagement.data.model.zone.Zone
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.zone.ZoneListViewModel
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.zone.ZoneViewModelFactory
import fit.tdc.edu.vn.cafemanagement.fragment.BaseListFragment
import fit.tdc.edu.vn.cafemanagement.fragment.BaseListViewModel

class ZoneListFragment : BaseListFragment<Zone>(
    R.layout.fragment_list,
    viewAdapter = ZoneAdapter()
) {
    override fun setupFab(fab: FloatingActionButton) {
        fab.setOnClickListener {
            navController.navigate(ZoneListFragmentDirections.zoneViewAction(null, getString(R.string.title_zone_create)))
        }
    }

    override val viewModel: BaseListViewModel<Zone>
        get() = ViewModelProvider(
            this,
            ZoneViewModelFactory(FireBaseDataSource(), this)
        ).get<ZoneListViewModel>()
    override val navController: NavController
        get() = findNavController()

    override fun showDeleteNotifySnackBar(item: Zone, view: View) {
        Snackbar.make(
            view,
            "Khu vực ${item.name} đã bị xóa!",
            Snackbar.LENGTH_LONG
        ).show()
    }
}

