package fit.tdc.edu.vn.cafemanagement.fragment.table

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.table_viewmodel.TableCreateViewModel
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.table_viewmodel.TableViewModel

class TableViewFragment : Fragment(R.layout.fragment_table_view) {

    companion object {
        fun newInstance() = TableViewFragment()
    }

    private lateinit var viewModel: TableViewModel
}
