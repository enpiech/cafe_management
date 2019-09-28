package fit.tdc.edu.vn.cafemanagement.ui.material_modify

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.cafemanagement.R

class MaterialModifyFragment : Fragment() {

    companion object {
        fun newInstance() = MaterialModifyFragment()
    }

    private lateinit var viewModel: MaterialModifyViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.material_modify_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MaterialModifyViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
