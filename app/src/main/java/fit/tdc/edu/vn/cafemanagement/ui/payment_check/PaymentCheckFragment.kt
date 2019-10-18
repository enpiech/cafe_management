package fit.tdc.edu.vn.cafemanagement.ui.payment_check

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import fit.tdc.edu.vn.cafemanagement.R

class PaymentCheckFragment : Fragment() {

    companion object {
        fun newInstance() = PaymentCheckFragment()
    }

    private lateinit var viewModel: PaymentCheckViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_payment_check, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PaymentCheckViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
