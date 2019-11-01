package fit.tdc.edu.vn.cafemanagement.fragment.table

import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.FireBaseDataSource
import fit.tdc.edu.vn.cafemanagement.data.model.FormState
import fit.tdc.edu.vn.cafemanagement.data.model.table.Table
import fit.tdc.edu.vn.cafemanagement.data.model.table.TableViewFormState
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.table.TableDetailViewModel
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.table.TableViewModelFactory
import fit.tdc.edu.vn.cafemanagement.fragment.BaseDetailViewModel
import fit.tdc.edu.vn.cafemanagement.fragment.BaseViewFragmentTest
import fit.tdc.edu.vn.cafemanagement.data.model.zone.Zone
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.table.TableDetailViewModel
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.table.TableViewModelFactory
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.zone.ZoneListViewModel
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.zone.ZoneViewModelFactory
import fit.tdc.edu.vn.cafemanagement.fragment.BaseViewFragment
import fit.tdc.edu.vn.cafemanagement.util.asEditText
import kotlinx.android.synthetic.main.form_table.*

class TableViewFragment : BaseViewFragmentTest<Table>(R.layout.fragment_table_detail) {
    override val args by navArgs<TableViewFragmentArgs>()
    override val viewModel: BaseDetailViewModel<Table>
        get() = ViewModelProvider(
            this,
            TableViewModelFactory(FireBaseDataSource(), this)
        ).get<TableDetailViewModel>()
    override val navController: NavController
        get() = findNavController()
    override val itemId: String?
        get() = args.tableId

    override fun showError(state: FormState) {
        val tableFormState = state as TableViewFormState
        if (tableFormState.nameError != null) {
            table_edit_name.error = getString(tableFormState.nameError!!)
        } else {
            table_edit_name.error = null
            table_edit_name.isErrorEnabled = false
        }
    }

    override fun updateUI(type: FormState.Type) {
        when (type) {
            FormState.Type.MODIFY, FormState.Type.ADD -> {
                enableForm(true)
            }
            FormState.Type.VIEW -> {
                enableForm(false)
            }
        }

        setupForm()
    }

    override fun getCurrentFormData() = Table(
        name = table_edit_name?.editText?.text.toString()
    ).also {
        it.zoneId = (viewModel as TableDetailViewModel).currentZone.value?.id
    }

    override fun fillFormWith(item: Table) {
        table_edit_name.editText?.setText(item.name)
        (viewModel as TableDetailViewModel).currentZone.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer { czone ->
                czone?.name?.let { name ->
                    table_spinner.setText(name, false)
                    (viewModel as TableDetailViewModel).currentZone.removeObservers(
                        viewLifecycleOwner
                    )
                }
            })
    }

    override fun setupForm() {
        table_edit_name.asEditText {
            viewModel.validate(
                getCurrentFormData()
            )
        }
        (viewModel as TableDetailViewModel).zoneList.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer {
                if (it.isNotEmpty()) {
                    val list = it.map { zone -> zone.name }
                    table_spinner.setAdapter(
                        ArrayAdapter(
                            requireContext(),
                            R.layout.dropdown_menu_popup_item,
                            list
                        )
                    )
                } else {
                    if ((viewModel as TableDetailViewModel).currentZone.value != null) {
                        // TODO notify no zone
//                        table_spinner.setText(getText(R.string.warning_store_no_free_manager), false)
                    } else {
                        // TODO notify not set zone
//                        table_spinner.setText(getText(R.string.warning_store_missing_manager), false)
                    }
                }
            })
        table_spinner.setOnItemClickListener { parent, view, position, id ->
            viewModel.validate(
                getCurrentFormData().apply {
                    zoneId = (viewModel as TableDetailViewModel).zoneList.value?.get(position)?.id
                }
            )
            (viewModel as TableDetailViewModel).currentZone.value =
                (viewModel as TableDetailViewModel).zoneList.value?.get(position)
        }
    }

    private fun enableForm(isEnabled: Boolean) {
        table_edit_name.editText?.isEnabled = isEnabled
        table_spinner.isEnabled = isEnabled
        table_spinner_layout.isEndIconVisible = isEnabled
    }

}

