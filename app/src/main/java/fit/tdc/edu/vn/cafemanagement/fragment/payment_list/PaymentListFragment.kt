package fit.tdc.edu.vn.cafemanagement.fragment.payment_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import fit.tdc.edu.vn.cafemanagement.R

class PaymentListFragment : Fragment(R.layout.fragment_list) {

    companion object {
        fun newInstance() = PaymentListFragment()
    }

    private lateinit var viewModel: PaymentListViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PaymentListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
