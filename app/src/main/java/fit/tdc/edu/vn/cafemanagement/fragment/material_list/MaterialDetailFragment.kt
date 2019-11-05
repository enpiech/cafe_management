package fit.tdc.edu.vn.cafemanagement.fragment.material_list

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.FireBaseDataSource
import fit.tdc.edu.vn.cafemanagement.data.model.FormState
import fit.tdc.edu.vn.cafemanagement.data.model.material.Material
import fit.tdc.edu.vn.cafemanagement.data.model.material.MaterialViewFormState
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.material.MaterialDetailViewModel
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.material.MaterialViewModelFactory
import fit.tdc.edu.vn.cafemanagement.fragment.BaseDetailViewModel
import fit.tdc.edu.vn.cafemanagement.fragment.BaseViewFragmentTest
import fit.tdc.edu.vn.cafemanagement.util.asEditText
import fit.tdc.edu.vn.cafemanagement.util.setupForEnum
import fit.tdc.edu.vn.cafemanagement.util.setupForLiveList
import kotlinx.android.synthetic.main.fragment_material_detail.*

class MaterialDetailFragment : BaseViewFragmentTest<Material>(R.layout.fragment_material_detail) {
    override val viewModel: BaseDetailViewModel<Material>
        get() = ViewModelProvider(
            this,
            MaterialViewModelFactory(FireBaseDataSource(), this)
        ).get<MaterialDetailViewModel>()
    override val navController: NavController
        get() = findNavController()
    override val itemId: String?
        get() = args.materialId
    override val args by navArgs<MaterialDetailFragmentArgs>()

    override fun showError(state: FormState) {
        val materialFormState = state as MaterialViewFormState

        if (materialFormState.nameError != null) {
            material_edit_name.error = getString(materialFormState.nameError!!)
        } else {
            material_edit_name.error = null
            material_edit_name.isErrorEnabled = false
        }

        if (materialFormState.priceError != null) {
            material_edit_price.error = getString(materialFormState.priceError!!)
        } else {
            material_edit_price.error = null
            material_edit_price.isErrorEnabled = false
        }
    }

    override fun updateUI(type: FormState.Type) {
        when (type) {
            FormState.Type.MODIFY -> {
                enableForm(true)
                setupForm()
            }
            FormState.Type.ADD -> {
                enableForm(true)
            }
            FormState.Type.VIEW -> {
                enableForm(false)
            }
        }

        setupForm()
    }

    private fun enableForm(isEnabled: Boolean) {
        material_edit_name.editText?.isEnabled = isEnabled
        material_edit_price.editText?.isEnabled = isEnabled
        units.isEndIconVisible = isEnabled
        units_dropdown.isEnabled = isEnabled
        types.isEndIconVisible = isEnabled
        types_dropdown.isEnabled = isEnabled
        categorys.isEndIconVisible = isEnabled
        categorys_dropdown.isEnabled = isEnabled
    }

    override fun getCurrentFormData() = viewModel.saved.apply {
        name = material_edit_name?.editText?.text.toString()
        var inputPrice = material_edit_price?.editText?.text.toString()
        if (inputPrice.isBlank()) {
            inputPrice = "0"
        }
        price = inputPrice.toLong()
    }

    override fun fillFormWith(item: Material) {
        material_edit_name.editText?.setText(item.name)
        material_edit_price.editText?.setText(item.price.toString())
        units_dropdown.setText(when {
            item.unitId != null -> item.unitName
            else -> getString(R.string.warning_material_missing_unit)
        })
        categorys_dropdown.setText(when {
            item.categoryId != null -> item.categoryName
            else -> getString(R.string.warning_material_missing_category)
        })
        types_dropdown.setText(getString(item.type.resId), false)
    }

    override fun setupForm() {
        material_edit_name.asEditText {
            viewModel.validate(
                getCurrentFormData()
            )
        }
        material_edit_price.asEditText {
            viewModel.validate(
                getCurrentFormData()
            )
        }
        types_dropdown.setupForEnum(
            context = requireContext(),
            dataset = Material.Type.values().map { type -> getString(type.resId) },
            resId = R.layout.dropdown_menu_popup_item
        ) {position ->
            viewModel.validate(
                getCurrentFormData().apply {
                    type = Material.Type.values()[position]
                    sellable = when (type) {
                        Material.Type.INGREDIENT -> false
                        else -> true
                    }
                }
            )
        }
        (viewModel as MaterialDetailViewModel).unitList.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer {
                units_dropdown.setupForLiveList(
                    context = requireContext(),
                    resId = R.layout.dropdown_menu_popup_item,
                    layout = units,
                    emptySetString = R.string.warning_material_no_unit,
                    missingString = R.string.warning_material_missing_unit,
                    dataset = it.map { unit -> unit.name }
                ) {position ->
                    viewModel.validate(
                        getCurrentFormData().apply {
                            unitId = it[position].id
                            unitName = it[position].name
                        }
                    )
                }
            }
        )
        (viewModel as MaterialDetailViewModel).categoryList.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer {
                categorys_dropdown.setupForLiveList(
                    context = requireContext(),
                    resId = R.layout.dropdown_menu_popup_item,
                    layout = categorys,
                    emptySetString = R.string.warning_material_no_category,
                    missingString = R.string.warning_material_missing_category,
                    dataset = it.map { unit -> unit.name }
                ) {position ->
                    viewModel.validate(
                        getCurrentFormData().apply {
                            categoryId = it[position].id
                            categoryName = it[position].name
                        }
                    )
                }
            }
        )
    }
}