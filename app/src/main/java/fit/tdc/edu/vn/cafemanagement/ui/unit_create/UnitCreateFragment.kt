package com.example.cafemanagement.ui.unit_create

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cafemanagement.R

class UnitCreateFragment : Fragment() {

    companion object {
        fun newInstance() = UnitCreateFragment()
    }

    private lateinit var createViewModel: UnitCreateViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.create_unit_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        createViewModel = ViewModelProviders.of(this).get(UnitCreateViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
