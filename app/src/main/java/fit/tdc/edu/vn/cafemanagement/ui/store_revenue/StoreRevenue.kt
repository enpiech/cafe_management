package fit.tdc.edu.vn.cafemanagement.ui.store_revenue

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import fit.tdc.edu.vn.cafemanagement.R

class StoreRevenue : Fragment() {

    companion object {
        fun newInstance() = StoreRevenue()
    }

    private lateinit var viewModel: StoreRevenueViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.store_revenue_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(StoreRevenueViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
