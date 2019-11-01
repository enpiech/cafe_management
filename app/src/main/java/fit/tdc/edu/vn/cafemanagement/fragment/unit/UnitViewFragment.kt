package fit.tdc.edu.vn.cafemanagement.fragment.unit

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.FireBaseDataSource
import fit.tdc.edu.vn.cafemanagement.data.model.FormState
import fit.tdc.edu.vn.cafemanagement.data.model.unit.Unit
import fit.tdc.edu.vn.cafemanagement.data.model.unit.UnitViewFormState
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.unit.UnitDetailViewModel
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.unit.UnitViewModelFactory
import fit.tdc.edu.vn.cafemanagement.fragment.BaseDetailViewModel
import fit.tdc.edu.vn.cafemanagement.fragment.BaseViewFragmentTest
import fit.tdc.edu.vn.cafemanagement.util.asEditText
import kotlinx.android.synthetic.main.fragment_unit_detail.*

class UnitViewFragment : BaseViewFragmentTest<Unit>(R.layout.fragment_unit_detail) {
    override val args by navArgs<UnitViewFragmentArgs>()
    override val viewModel: BaseDetailViewModel<Unit>
        get() = ViewModelProvider(
            this,
            UnitViewModelFactory(FireBaseDataSource(), this)
        ).get<UnitDetailViewModel>()
    override val navController: NavController
        get() = findNavController()
    override val itemId: String?
        get() = args.unitId

    override fun showError(state: FormState) {
        val unitFormState = state as UnitViewFormState
        if (unitFormState.nameError != null) {
            edtName.error = getString(unitFormState.nameError!!)
        }
    }

    override fun getCurrentFormData() = Unit(
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

    override fun fillFormWith(item: Unit) {
        edtName.editText?.setText(item.name)
    }

    override fun setupForm() {
        edtName.asEditText {
            viewModel.validate(
                Unit(
                    name = it
                )
            )
        }
    }
}
