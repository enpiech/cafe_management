package fit.tdc.edu.vn.cafemanagement.ui.material_list

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import fit.tdc.edu.vn.cafemanagement.R

class MaterialListFragment : Fragment() {

    companion object {
        fun newInstance() = MaterialListFragment()
    }

    private lateinit var viewModel: MaterialListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.material_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MaterialListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
