package fit.tdc.edu.vn.cafemanagement.ui.zone_modify

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.cafemanagement.R

class ZoneModifyFragment : Fragment() {

    companion object {
        fun newInstance() = ZoneModifyFragment()
    }

    private lateinit var viewModel: ZoneModifyViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.zone_modify_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ZoneModifyViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
