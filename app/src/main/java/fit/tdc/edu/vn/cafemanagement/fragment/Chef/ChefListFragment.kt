package fit.tdc.edu.vn.cafemanagement.fragment.Chef

import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.adapter.ChefAdapter
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.FireBaseDataSource
import fit.tdc.edu.vn.cafemanagement.data.model.chef.Chef
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.chef.ChefListViewModel
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.chef.ChefViewModelFactory
import fit.tdc.edu.vn.cafemanagement.fragment.BaseListFragment
import fit.tdc.edu.vn.cafemanagement.fragment.BaseListViewModel

class ChefListFragment : BaseListFragment<Chef>(
    R.layout.fragment_list,
    viewAdapter = ChefAdapter()
) {
    override val viewModel: BaseListViewModel<Chef>
        get() = ViewModelProvider(
            this,
            ChefViewModelFactory(FireBaseDataSource(), this)
        ).get<ChefListViewModel>()
    override val navController: NavController
        get() = findNavController()

    override fun setupFab(fab: FloatingActionButton) {
    }

    override fun showDeleteNotifySnackBar(item: Chef, view: View) {

    }

    override fun setupSwipeToDelete() {

    }
}