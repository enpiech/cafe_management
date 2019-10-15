package fit.tdc.edu.vn.cafemanagement.ui.table_list

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.adapter.TableAdapter
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.table_viewmodel.TableViewModel

class TableListFragment : Fragment(R.layout.item_table_each_zone) {

    var adapter = TableAdapter()

    companion object {
        fun newInstance() = TableListFragment()
    }

    private lateinit var viewModel: TableViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}
