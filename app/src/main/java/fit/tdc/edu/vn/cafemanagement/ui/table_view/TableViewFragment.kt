package fit.tdc.edu.vn.cafemanagement.ui.table_view

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.table_viewmodel.TableCreateViewModel

class TableViewFragment : Fragment() {

    companion object {
        fun newInstance() = TableViewFragment()
    }

    private lateinit var viewModel: TableCreateViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.item_table, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TableCreateViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
