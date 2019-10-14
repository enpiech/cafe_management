package fit.tdc.edu.vn.cafemanagement.ui.zone_modify

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import fit.tdc.edu.vn.cafemanagement.R

class ZoneModifyFragment : Fragment() {

    companion object {
        fun newInstance() = ZoneModifyFragment()
    }

    private lateinit var viewModel: ZoneModifyViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.zone_create_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ZoneModifyViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
