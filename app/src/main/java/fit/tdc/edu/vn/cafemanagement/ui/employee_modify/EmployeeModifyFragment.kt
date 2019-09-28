package fit.tdc.edu.vn.cafemanagement.ui.employee_modify

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import fit.tdc.edu.vn.cafemanagement.R

class EmployeeModifyFragment : Fragment() {

    companion object {
        fun newInstance() = EmployeeModifyFragment()
    }

    private lateinit var viewModel: EmployeeModifyViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.employee_modify_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(EmployeeModifyViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
