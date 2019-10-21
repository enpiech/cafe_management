package fit.tdc.edu.vn.cafemanagement.fragment.store_revenue

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import fit.tdc.edu.vn.cafemanagement.R

class StoreRevenue : Fragment(R.layout.fragment_store_revenue) {

    companion object {
        fun newInstance() = StoreRevenue()
    }

    private lateinit var viewModel: StoreRevenueViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(StoreRevenueViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
