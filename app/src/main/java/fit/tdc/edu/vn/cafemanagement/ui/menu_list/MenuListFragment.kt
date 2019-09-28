package fit.tdc.edu.vn.cafemanagement.ui.menu_list

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fit.tdc.edu.vn.cafemanagement.R


class MenuListFragment : Fragment() {

    companion object {
        fun newInstance() = MenuListFragment()
    }

    private lateinit var viewModel: MenuListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.menu_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MenuListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
