package fit.tdc.edu.vn.cafemanagement.fragment.zone

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.model.FormState
import fit.tdc.edu.vn.cafemanagement.data.model.zone.Zone
import fit.tdc.edu.vn.cafemanagement.data.model.zone.ZoneViewFormState
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.zone_viewmodel.ZoneDetailViewModel
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.zone_viewmodel.ZoneViewModelFactory
import fit.tdc.edu.vn.cafemanagement.fragment.BaseDetailViewModel
import fit.tdc.edu.vn.cafemanagement.fragment.BaseViewFragmentTest
import fit.tdc.edu.vn.cafemanagement.util.asEditText
import kotlinx.android.synthetic.main.fragment_zone_view.*


class ZoneViewFragment : BaseViewFragmentTest<Zone>(R.layout.fragment_zone_view) {
    override val viewModel: BaseDetailViewModel<Zone>
        get() = ViewModelProvider(this, ZoneViewModelFactory()).get<ZoneDetailViewModel>()
    override val navController: NavController
        get() = findNavController()
    override val itemId: String?
        get() = args.zoneId
    override val args by navArgs<ZoneViewFragmentArgs>()

    override fun showError(state: FormState) {
        val zoneFormState = state as ZoneViewFormState
        if (zoneFormState.nameError != null) {
            edtName.error = getString(zoneFormState.nameError)
        }
    }

    override fun getCurrentFormData() = Zone(
        name = edtName.editText?.text.toString()
    )

    override fun updateUI(type: FormState.Type) {
        when (type) {
            FormState.Type.MODIFY, FormState.Type.ADD -> {
                edtName.editText?.isEnabled = true
            }
            FormState.Type.VIEW -> {
                edtName.editText?.isEnabled = false
            }
        }
        setupForm()
    }

    override fun fillFormWith(item: Zone) {
        edtName.editText?.setText(item.name)
    }

    override fun setupForm() {
        edtName.asEditText {
            viewModel.validate(
                Zone(
                    name = it
                )
            )
        }
    }
}
