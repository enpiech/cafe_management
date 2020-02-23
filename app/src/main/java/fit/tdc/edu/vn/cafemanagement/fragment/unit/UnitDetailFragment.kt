package fit.tdc.edu.vn.cafemanagement.fragment.unit

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.unit.UnitRemoteDataSourceImpl
import fit.tdc.edu.vn.cafemanagement.data.model.FormState
import fit.tdc.edu.vn.cafemanagement.data.model.unit.Unit
import fit.tdc.edu.vn.cafemanagement.data.model.unit.UnitViewFormState
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.unit.UnitDetailViewModel
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.unit.UnitViewModelFactory
import fit.tdc.edu.vn.cafemanagement.fragment.BaseDetailFragment
import fit.tdc.edu.vn.cafemanagement.fragment.BaseDetailViewModel
import fit.tdc.edu.vn.cafemanagement.util.asEditText
import kotlinx.android.synthetic.main.fragment_detail_unit.*

class UnitDetailFragment : BaseDetailFragment<Unit>(R.layout.fragment_detail_unit) {
    override val args by navArgs<UnitDetailFragmentArgs>()
    override val viewModel: BaseDetailViewModel<Unit>
        get() = ViewModelProvider(
            this,
            UnitViewModelFactory(UnitRemoteDataSourceImpl(), this)
        ).get<UnitDetailViewModel>()
    override val navController: NavController
        get() = findNavController()
    override val itemId: String?
        get() = args.unitId

    override fun showError(state: FormState) {
        val unitFormState = state as UnitViewFormState
        if (unitFormState.nameError != null) {
            unit_name.error = getString(unitFormState.nameError!!)
        }
    }

    override fun getCurrentFormData() = Unit(
        name = unit_name.editText?.text.toString()
    )

    override fun updateUI(type: FormState.Type) {
        when (type) {
            FormState.Type.MODIFY, FormState.Type.ADD -> {
                unit_name.editText?.isEnabled = true
            }
            FormState.Type.VIEW -> {
                unit_name.editText?.isEnabled = false
            }
        }
        setupForm()
    }

    override fun fillFormWith(item: Unit) {
        unit_name.editText?.setText(item.name)
    }

    override fun setupForm() {
        unit_name.asEditText {
            viewModel.validate(
                Unit(
                    name = it
                )
            )
        }
    }
}
