package fit.tdc.edu.vn.cafemanagement.ui.employee_view

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import fit.tdc.edu.vn.cafemanagement.R

class EmployeeViewFragment : Fragment(R.layout.fragment_employee_view) {

    companion object {
        fun newInstance() = EmployeeViewFragment()
    }

    private lateinit var viewModel: EmployeeViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get()
        // TODO: Use the ViewModel
    }

}
