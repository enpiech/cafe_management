package fit.tdc.edu.vn.cafemanagement.ui.table_list

import androidx.fragment.app.Fragment
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.adapter.TableAdapter
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.table_viewmodel.TableViewModel

class TableListFragment : Fragment(R.layout.item_table_each_zone) {

    var adapter = TableAdapter()

    companion object {
        fun newInstance() = TableListFragment()
    }

    private lateinit var viewModel: TableViewModel

}
