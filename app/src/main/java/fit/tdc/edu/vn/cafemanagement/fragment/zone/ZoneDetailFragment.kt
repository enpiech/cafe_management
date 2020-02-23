package fit.tdc.edu.vn.cafemanagement.fragment.zone

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.zone.ZoneRemoteDataSourceImpl
import fit.tdc.edu.vn.cafemanagement.data.model.FormState
import fit.tdc.edu.vn.cafemanagement.data.model.zone.Zone
import fit.tdc.edu.vn.cafemanagement.data.model.zone.ZoneViewFormState
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.zone.ZoneDetailViewModel
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.zone.ZoneViewModelFactory
import fit.tdc.edu.vn.cafemanagement.fragment.BaseDetailFragment
import fit.tdc.edu.vn.cafemanagement.fragment.BaseDetailViewModel
import fit.tdc.edu.vn.cafemanagement.util.asEditText
import kotlinx.android.synthetic.main.fragment_detail_zone.*


class ZoneDetailFragment : BaseDetailFragment<Zone>(R.layout.fragment_detail_zone) {
    override val viewModel: BaseDetailViewModel<Zone>
        get() = ViewModelProvider(
            this,
            ZoneViewModelFactory(ZoneRemoteDataSourceImpl(), this)
        ).get<ZoneDetailViewModel>()
    override val navController: NavController
        get() = findNavController()
    override val itemId: String?
        get() = args.zoneId
    override val args by navArgs<ZoneDetailFragmentArgs>()

    override fun showError(state: FormState) {
        val zoneFormState = state as ZoneViewFormState
        if (zoneFormState.nameError != null) {
            zone_name.error = getString(zoneFormState.nameError!!)
        }
    }

    override fun getCurrentFormData() = Zone(
        name = zone_name.editText?.text.toString()
    )

    override fun updateUI(type: FormState.Type) {
        when (type) {
            FormState.Type.MODIFY, FormState.Type.ADD -> {
                zone_name.editText?.isEnabled = true
            }
            FormState.Type.VIEW -> {
                zone_name.editText?.isEnabled = false
            }
        }
        setupForm()
    }

    override fun fillFormWith(item: Zone) {
        zone_name.editText?.setText(item.name)
    }

    override fun setupForm() {
        zone_name.asEditText {
            viewModel.validate(
                Zone(
                    name = it
                )
            )
        }
    }
}
