package com.example.cafemanagement.ui.payment_check

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

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
        return inflater.inflate(R.layout.payment_confirm_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PaymentCheckViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
