package fit.tdc.edu.vn.cafemanagement.fragment.material_list

import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
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

    override fun getCurrentFormData() = Material(
        name = material_edit_name?.editText?.text.toString(),
        price = material_edit_price?.editText?.text.toString().toLong()
    ).also {
        it.unitId = (viewModel as MaterialDetailViewModel).currentUnit.value?.id
        it.categoryId = (viewModel as MaterialDetailViewModel).currentCategory.value?.id
    }

    override fun fillFormWith(item: Material) {
        material_edit_name.editText?.setText(item.name)
        material_edit_price.editText?.setText(item.price.toLong())
        (viewModel as MaterialDetailViewModel).currentUnit.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer { cunit ->
                cunit?.name?.let { name ->
                    units_dropdown.setText(name, false)
                    (viewModel as MaterialDetailViewModel).currentUnit.removeObservers(
                        viewLifecycleOwner
                    )
                }
            })
        (viewModel as MaterialDetailViewModel).currentCategory.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer { ccategory ->
                ccategory?.name?.let { name ->
                    categorys_dropdown.setText(name, false)
                    (viewModel as MaterialDetailViewModel).currentCategory.removeObservers(
                        viewLifecycleOwner
                    )
                }
            })
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
        (viewModel as MaterialDetailViewModel).unitList.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer {
                if (it.isNotEmpty()) {
                    val list = it.map { unit -> unit.name }
                    units_dropdown.setAdapter(
                        ArrayAdapter(
                            requireContext(),
                            R.layout.dropdown_menu_popup_item,
                            list
                        )
                    )
                }
            }
        )
        (viewModel as MaterialDetailViewModel).categoryList.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer {
                if (it.isNotEmpty()) {
                    val list = it.map { category ->
                        category.name
                    }
                    categorys_dropdown.setAdapter(
                        ArrayAdapter(
                            requireContext(),
                            R.layout.dropdown_menu_popup_item,
                            list
                        )
                    )
                }
            }
        )

        units_dropdown.setOnItemClickListener { parent, view, position, id ->
            viewModel.validate(
                getCurrentFormData().apply {
                    unitId =
                        (viewModel as MaterialDetailViewModel).unitList.value?.get(position)?.id
                }
            )
            (viewModel as MaterialDetailViewModel).currentUnit.value =
                (viewModel as MaterialDetailViewModel).unitList.value?.get(position)
        }

        categorys_dropdown.setOnItemClickListener { parent, view, position, id ->
            viewModel.validate(
                getCurrentFormData().apply {
                    categoryId =
                        (viewModel as MaterialDetailViewModel).categoryList.value?.get(position)?.id
                }
            )
            (viewModel as MaterialDetailViewModel).currentCategory.value =
                (viewModel as MaterialDetailViewModel).categoryList.value?.get(position)
        }
    }
}