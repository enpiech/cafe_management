package fit.tdc.edu.vn.cafemanagement.fragment.ware_house

import android.view.View
import androidx.navigation.NavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.model.ware_house.WareHouse
import fit.tdc.edu.vn.cafemanagement.fragment.BaseListFragment
import fit.tdc.edu.vn.cafemanagement.fragment.BaseListViewModel

class WareHouseListFragment : BaseListFragment<WareHouse>(
    R.layout.fragment_list,
    WareHouseAdapter()
) {
    override val viewModel: BaseListViewModel<WareHouse>
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
    override val navController: NavController
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun setupFab(fab: FloatingActionButton) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showDeleteNotifySnackBar(item: WareHouse, view: View) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}