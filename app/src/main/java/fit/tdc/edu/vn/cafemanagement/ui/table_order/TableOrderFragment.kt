package fit.tdc.edu.vn.cafemanagement.ui.table_order

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

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
        return inflater.inflate(R.layout.table_order_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TableOrderViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
