package fit.tdc.edu.vn.cafemanagement.fragment.zone

import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.adapter.ZoneAdapter
import fit.tdc.edu.vn.cafemanagement.data.model.zone.Zone
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.zone_viewmodel.ZoneListViewModel
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.zone_viewmodel.ZoneViewModelFactory
import fit.tdc.edu.vn.cafemanagement.fragment.BaseListFragment
import fit.tdc.edu.vn.cafemanagement.fragment.BaseListViewModel

class ZoneListFragment : BaseListFragment<Zone>(R.layout.fragment_list) {
    override val viewAdapter: ListAdapter<Zone, RecyclerView.ViewHolder>
        get() = ZoneAdapter()
    override val viewModel: BaseListViewModel<Zone>
        get() = ViewModelProvider(this, ZoneViewModelFactory()).get<ZoneListViewModel>()
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
