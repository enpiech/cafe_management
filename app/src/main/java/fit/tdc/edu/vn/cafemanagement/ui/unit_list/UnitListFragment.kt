package com.example.cafemanagement.ui.unit_list

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cafemanagement.R


class UnitListFragment : Fragment() {

    companion object {
        fun newInstance() = UnitListFragment()
    }

    private lateinit var listViewModel: UnitListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.list_unit_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        listViewModel = ViewModelProviders.of(this).get(UnitListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
