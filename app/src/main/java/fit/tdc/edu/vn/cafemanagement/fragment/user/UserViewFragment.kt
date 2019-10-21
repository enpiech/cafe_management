package fit.tdc.edu.vn.cafemanagement.fragment.user

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.fragment.employee_view.EmployeeViewModel

class UserViewFragment : Fragment(R.layout.fragment_user_view) {

    companion object {
        fun newInstance() = UserViewFragment()
    }

    private lateinit var viewModel: EmployeeViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get()
        // TODO: Use the ViewModel
    }

}
