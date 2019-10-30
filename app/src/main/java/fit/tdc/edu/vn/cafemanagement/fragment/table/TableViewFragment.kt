package fit.tdc.edu.vn.cafemanagement.fragment.table

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.NavArgs
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.model.FormState
import fit.tdc.edu.vn.cafemanagement.data.model.table.Table
import fit.tdc.edu.vn.cafemanagement.data.model.table.TableViewFormState
import fit.tdc.edu.vn.cafemanagement.data.model.zone.Zone
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.table_viewmodel.TableDetailViewModel
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.table_viewmodel.TableListViewModel
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.table_viewmodel.TableViewModelFactory
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.zone_viewmodel.ZoneListViewModel
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.zone_viewmodel.ZoneViewModelFactory
import fit.tdc.edu.vn.cafemanagement.fragment.BaseDetailViewModel
import fit.tdc.edu.vn.cafemanagement.fragment.BaseViewFragment
import fit.tdc.edu.vn.cafemanagement.fragment.BaseViewFragmentTest
import fit.tdc.edu.vn.cafemanagement.util.asEditText
import kotlinx.android.synthetic.main.dropdown_menu_popup_item.*
import kotlinx.android.synthetic.main.form_table.*
import kotlinx.android.synthetic.main.layout_tab_list.*

class TableViewFragment : BaseViewFragmentTest<Table>(R.layout.fragment_table_view) {
    override val args by navArgs<TableViewFragmentArgs>()
    override val viewModel: BaseDetailViewModel<Table>
        get() = ViewModelProvider(this, TableViewModelFactory()).get<TableDetailViewModel>()
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

        if (type == FormState.Type.MODIFY) {
            table_edit_name.editText?.isEnabled = false
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
    }

    override fun setupForm() {
        table_edit_name.asEditText {
            viewModel.validate(
                getCurrentFormData()
            )
        }
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
    }

}

