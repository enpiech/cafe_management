package fit.tdc.edu.vn.cafemanagement.ui.table_view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
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
        viewModel = ViewModelProvider(this).get(TableCreateViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
