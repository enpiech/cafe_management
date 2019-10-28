package fit.tdc.edu.vn.cafemanagement.ui.fragment.table_order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import fit.tdc.edu.vn.cafemanagement.R

class TableOrderFragment : Fragment() {

    companion object {
        fun newInstance() = TableOrderFragment()
    }

    private lateinit var viewModel: TableOrderViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_table_order, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TableOrderViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
