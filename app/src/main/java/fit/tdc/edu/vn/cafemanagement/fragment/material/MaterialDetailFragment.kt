package fit.tdc.edu.vn.cafemanagement.fragment.material

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
import fit.tdc.edu.vn.cafemanagement.fragment.BaseDetailFragment
import fit.tdc.edu.vn.cafemanagement.util.asEditText
import fit.tdc.edu.vn.cafemanagement.util.setupForEnum
import fit.tdc.edu.vn.cafemanagement.util.setupForLiveList
import kotlinx.android.synthetic.main.fragment_detail_material.*

class MaterialDetailFragment : BaseDetailFragment<Material>(R.layout.fragment_detail_material) {
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
            material_name.error = getString(materialFormState.nameError!!)
        } else {
            material_name.error = null
            material_name.isErrorEnabled = false
        }

        if (materialFormState.priceError != null) {
            material_price.error = getString(materialFormState.priceError!!)
        } else {
            material_price.error = null
            material_price.isErrorEnabled = false
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
        material_name.editText?.isEnabled = isEnabled
        material_price.editText?.isEnabled = isEnabled
        material_unit_layout.isEndIconVisible = isEnabled
        material_unit.isEnabled = isEnabled
        material_type_layout.isEndIconVisible = isEnabled
        material_type.isEnabled = isEnabled
        material_category_layout.isEndIconVisible = isEnabled
        material_category.isEnabled = isEnabled
    }

    override fun getCurrentFormData() = viewModel.saved.apply {
        name = material_name?.editText?.text.toString()
        var inputPrice = material_price?.editText?.text.toString()
        if (inputPrice.isBlank()) {
            inputPrice = "0"
        }
        price = inputPrice.toLong()
    }

    override fun fillFormWith(item: Material) {
        material_name.editText?.setText(item.name)
        material_price.editText?.setText(item.price.toString())
        material_unit.setText(when {
            item.unitId != null -> item.unitName
            else -> getString(R.string.warning_material_missing_unit)
        }, false)
        material_category.setText(when {
            item.categoryId != null -> item.categoryName
            else -> getString(R.string.warning_material_missing_category)
        }, false)
        material_type.setText(getString(item.type.resId), false)
    }

    override fun setupForm() {
        material_name.asEditText {
            viewModel.validate(
                getCurrentFormData()
            )
        }
        material_price.asEditText {
            viewModel.validate(
                getCurrentFormData()
            )
        }
        material_type.setupForEnum(
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
                material_unit.setupForLiveList(
                    context = requireContext(),
                    resId = R.layout.dropdown_menu_popup_item,
                    layout = material_unit_layout,
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
                material_category.setupForLiveList(
                    context = requireContext(),
                    resId = R.layout.dropdown_menu_popup_item,
                    layout = material_category_layout,
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