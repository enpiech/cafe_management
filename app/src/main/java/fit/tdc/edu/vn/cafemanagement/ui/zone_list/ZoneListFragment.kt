package fit.tdc.edu.vn.cafemanagement.ui.zone_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import fit.tdc.edu.vn.cafemanagement.R

class ZoneListFragment : Fragment() {

    companion object {
        fun newInstance() = ZoneListFragment()
    }

    private lateinit var viewModel: ZoneListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.zone_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ZoneListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
