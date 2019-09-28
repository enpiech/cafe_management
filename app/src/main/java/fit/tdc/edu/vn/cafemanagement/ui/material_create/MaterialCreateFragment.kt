package com.example.cafemanagement.ui.material_create

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import fit.tdc.edu.vn.cafemanagement.R

class MaterialCreateFragment : Fragment() {

    companion object {
        fun newInstance() = MaterialCreateFragment()
    }

    private lateinit var viewModel: MaterialCreateViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.material_create_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MaterialCreateViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
