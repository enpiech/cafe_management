package fit.tdc.edu.vn.cafemanagement.ui.unit_modify

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fit.tdc.edu.vn.cafemanagement.R


class UnitModifyFragment : Fragment() {

    companion object {
        fun newInstance() = UnitModifyFragment()
    }

    private lateinit var viewModel: UnitModifyViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.modify_unit_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(UnitModifyViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
