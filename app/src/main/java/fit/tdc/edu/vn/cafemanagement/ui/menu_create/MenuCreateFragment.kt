package fit.tdc.edu.vn.cafemanagement.ui.menu_create

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.cafemanagement.R

class MenuCreateFragment : Fragment() {

    companion object {
        fun newInstance() = MenuCreateFragment()
    }

    private lateinit var viewModel: MenuCreateViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.menu_create_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MenuCreateViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
