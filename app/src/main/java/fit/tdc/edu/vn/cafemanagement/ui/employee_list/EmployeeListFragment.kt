package fit.tdc.edu.vn.cafemanagement.ui.employee_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import fit.tdc.edu.vn.cafemanagement.R

class EmployeeListFragment : Fragment(R.layout.fragment_list) {

    companion object {
        fun newInstance() = EmployeeListFragment()
    }

    private lateinit var viewModel: EmployeeListViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(EmployeeListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
