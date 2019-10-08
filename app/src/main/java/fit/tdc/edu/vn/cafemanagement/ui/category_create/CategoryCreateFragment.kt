package fit.tdc.edu.vn.cafemanagement.ui.category_create

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fit.tdc.edu.vn.cafemanagement.R

class CategoryCreateFragment : Fragment() {

    companion object {
        fun newInstance() = CategoryCreateFragment()
    }

    private lateinit var viewModel: CategoryCreateViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_category_create, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CategoryCreateViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
