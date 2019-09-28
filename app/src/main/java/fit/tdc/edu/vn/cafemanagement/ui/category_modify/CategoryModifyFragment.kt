package fit.tdc.edu.vn.cafemanagement.ui.category_modify

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import fit.tdc.edu.vn.cafemanagement.R

class CategoryModifyFragment : Fragment() {

    companion object {
        fun newInstance() = CategoryModifyFragment()
    }

    private lateinit var viewModel: CategoryModifyViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.category_modify_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CategoryModifyViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
