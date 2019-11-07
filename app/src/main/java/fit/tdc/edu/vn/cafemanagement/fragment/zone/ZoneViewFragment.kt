package fit.tdc.edu.vn.cafemanagement.fragment.zone

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.FireBaseDataSource
import fit.tdc.edu.vn.cafemanagement.data.model.FormState
import fit.tdc.edu.vn.cafemanagement.data.model.zone.Zone
import fit.tdc.edu.vn.cafemanagement.data.model.zone.ZoneViewFormState
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.zone.ZoneDetailViewModel
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.zone.ZoneViewModelFactory
import fit.tdc.edu.vn.cafemanagement.fragment.BaseDetailViewModel
import fit.tdc.edu.vn.cafemanagement.fragment.BaseViewFragmentTest
import fit.tdc.edu.vn.cafemanagement.util.asEditText
import kotlinx.android.synthetic.main.fragment_zone_detail.*


class ZoneViewFragment : BaseViewFragmentTest<Zone>(R.layout.fragment_zone_detail) {
    override val viewModel: BaseDetailViewModel<Zone>
        get() = ViewModelProvider(
            this,
            ZoneViewModelFactory(FireBaseDataSource(), this)
        ).get<ZoneDetailViewModel>()
    override val navController: NavController
        get() = findNavController()
    override val itemId: String?
        get() = args.zoneId
    override val args by navArgs<ZoneViewFragmentArgs>()

    override fun showError(state: FormState) {
        val zoneFormState = state as ZoneViewFormState
        if (zoneFormState.nameError != null) {
            editZoneName.error = getString(zoneFormState.nameError!!)
        }
    }

    override fun getCurrentFormData() = Zone(
        name = editZoneName.editText?.text.toString()
    )

    override fun updateUI(type: FormState.Type) {
        when (type) {
            FormState.Type.MODIFY, FormState.Type.ADD -> {
                editZoneName.editText?.isEnabled = true
            }
            FormState.Type.VIEW -> {
                editZoneName.editText?.isEnabled = false
            }
        }
        setupForm()
    }

    override fun fillFormWith(item: Zone) {
        editZoneName.editText?.setText(item.name)
    }

    override fun setupForm() {
        editZoneName.asEditText {
            viewModel.validate(
                Zone(
                    name = it
                )
            )
        }
    }
}
