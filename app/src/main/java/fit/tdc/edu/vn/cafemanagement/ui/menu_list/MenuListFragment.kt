package fit.tdc.edu.vn.cafemanagement.ui.menu_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import fit.tdc.edu.vn.cafemanagement.R


class MenuListFragment : Fragment(R.layout.fragment_list) {

    companion object {
        fun newInstance() = MenuListFragment()
    }

    private lateinit var viewModel: MenuListViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MenuListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
