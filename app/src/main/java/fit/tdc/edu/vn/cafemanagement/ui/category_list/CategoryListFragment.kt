package fit.tdc.edu.vn.cafemanagement.ui.category_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import fit.tdc.edu.vn.cafemanagement.R

class CategoryListFragment : Fragment() {

    companion object {
        fun newInstance() = CategoryListFragment()
    }

    private lateinit var viewModel: CategoryListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.category_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CategoryListViewModel::class.java)
        // TODO: Use the ViewModel
    }
}
