package com.example.cafemanagement.view.ui.tablelist

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import androidx.recyclerview.widget.RecyclerView
import com.example.cafemanagement.R
import com.example.cafemanagement.ui.tablelist.TableListViewModel
import com.example.cafemanagement.view.adapter.BaseBindingListAdapter

class TableListFragment : Fragment() {

    companion object {
        fun newInstance() = TableListFragment()
    }

    private lateinit var viewModel: TableListViewModel
    private lateinit var listTable: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_table_list, container, false)

        listTable = view?.findViewById(R.id.listTable)!!

        return inflater.inflate(R.layout.fragment_table_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TableListViewModel::class.java)
        // TODO: Use the ViewModel
    }
}
