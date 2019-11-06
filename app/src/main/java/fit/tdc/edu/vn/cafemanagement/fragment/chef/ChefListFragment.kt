package fit.tdc.edu.vn.cafemanagement.fragment.chef

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.adapter.ChefAdapter
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.FireBaseDataSource
import fit.tdc.edu.vn.cafemanagement.data.model.order.Order
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.chef.ChefListViewModel
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.chef.ChefViewModelFactory
import fit.tdc.edu.vn.cafemanagement.fragment.BaseListFragment
import fit.tdc.edu.vn.cafemanagement.fragment.BaseListViewModel

class ChefListFragment : BaseListFragment<Order>(
    R.layout.fragment_list,
    viewAdapter = ChefAdapter()
) {
    override val viewModel: BaseListViewModel<Order>
        get() = ViewModelProvider(
            this,
            ChefViewModelFactory(FireBaseDataSource(), this)
        ).get<ChefListViewModel>()
    override val navController: NavController
        get() = findNavController()

    override fun setupFab(fab: FloatingActionButton) {
    }

    override fun setupSwipeToDelete() {

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (viewAdapter as ChefAdapter).onComplete = {order ->
            (viewModel as ChefListViewModel).delete(order)
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}