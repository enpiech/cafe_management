package fit.tdc.edu.vn.cafemanagement.fragment.table

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.model.FormState
import fit.tdc.edu.vn.cafemanagement.data.model.table.Table
import fit.tdc.edu.vn.cafemanagement.data.model.table.TableViewFormState
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.table.TableDetailViewModel
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.table.TableViewModelFactory
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.zone.ZoneListViewModel
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.zone.ZoneViewModelFactory
import fit.tdc.edu.vn.cafemanagement.fragment.BaseViewFragment
import fit.tdc.edu.vn.cafemanagement.util.asEditText
import kotlinx.android.synthetic.main.form_table.*

class TableViewFragment : BaseViewFragment(R.layout.fragment_table_detail) {

    //val spinnerAdapter = SpinnerZoneAdapter()

    companion object {
        fun newInstance() = TableViewFragment()
    }

    private val tableViewModel by lazy {
        ViewModelProvider(this, TableViewModelFactory()).get<TableDetailViewModel>()
    }

    private val zoneViewModel by lazy {
        ViewModelProvider(this, ZoneViewModelFactory()).get<ZoneListViewModel>()
    }

    private val args by navArgs<TableViewFragmentArgs>()
    private val tableId by lazy {
        args.tableId
    }

    private val navController by lazy {
        findNavController()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        zoneViewModel.getAllItems().observe(this, Observer {
            val options = it.data
            val spinnerAdapter = options?.let { it1 ->
                ArrayAdapter(
                    requireContext(), android.R.layout.simple_spinner_dropdown_item,
                    it1
                )
            }
            table_spinner.adapter = spinnerAdapter
        })

//        zoneViewModel.getAllItems().observe(this, Observer {
//            val items = ArrayList<Zone>()
//            it.data?.let { it1 -> items.addAll(it1) }
//            val spinnerAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item,items)
//            table_spinner.adapter = spinnerAdapter
//        })

        tableViewModel.viewType.observe(this, Observer {
            if (it == null) {
                when (tableId) {
                    null -> {
                        tableViewModel.setViewType(FormState.Type.ADD)
                    }
                    else -> {
                        tableViewModel.setViewType(FormState.Type.VIEW)
                        tableViewModel.getCurrentItem(tableId!!)
                    }
                }
            } else {
                changeViewType(it)
            }
        })

        tableViewModel.formState.observe(this, Observer {
            val state = it ?: return@Observer
            if (tableViewModel.viewType.value != FormState.Type.VIEW) {
                if (state.isDataValid && state.isChanged) {
                    fab.show()
                } else {
                    fab.hide()
                }
                val tableFormState = state as TableViewFormState
                if (tableFormState.nameError != null) {
                    table_edit_name.error = getString(tableFormState.nameError)
                }
            }
        })

        fab.setOnClickListener {
            when (tableViewModel.viewType.value) {
                FormState.Type.ADD -> {
                    tableViewModel.insert(
                        Table(
                            name = table_edit_name.editText?.text.toString()
                        )
                    )
                    navController.navigateUp()
                }
                FormState.Type.VIEW -> {
                    tableViewModel.setViewType(FormState.Type.MODIFY)
                }
                FormState.Type.MODIFY -> {
                    tableViewModel.update(
                        Table(
                            name = table_edit_name.editText?.text.toString()
                        ).also { it.id = tableId!! }
                    )
                    navController.navigateUp()
                }
            }
        }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun requestNavigateUp() {
        if (tableViewModel.viewType.value == FormState.Type.VIEW) {
            findNavController().navigateUp()
            return
        }

        if (tableViewModel.formState.value?.isChanged == true) {
            MaterialAlertDialogBuilder(context)
                .setTitle(R.string.dialog_title_warning)
                .setMessage(R.string.warning_message_unsaved_changed)
                .setPositiveButton(R.string.btnOK) { _, _ ->
                    when (tableViewModel.viewType.value) {
                        FormState.Type.MODIFY -> tableViewModel.setViewType(FormState.Type.VIEW)
                        FormState.Type.ADD -> findNavController().navigateUp()
                    }
                }
                .setNegativeButton(R.string.btnCancel, null)
                .show()
        } else {
            if (tableViewModel.viewType.value == FormState.Type.ADD) {
                findNavController().navigateUp()
            } else {
                tableViewModel.setViewType(FormState.Type.VIEW)
            }
        }
    }

    override fun updateUI(type: FormState.Type) {
        when (type) {
            FormState.Type.MODIFY, FormState.Type.ADD -> {
                table_edit_name.isEnabled = true
                table_spinner.isEnabled = true
            }
            FormState.Type.VIEW -> {
                table_edit_name.isEnabled = false
                table_spinner.isEnabled = false
            }
        }

        if (type != FormState.Type.ADD) {
            tableViewModel.currentItem.observe(this, Observer {
                if (it != null) {
                    table_edit_name.editText?.setText(it.name)
                } else {
                    MaterialAlertDialogBuilder(context)
                        .setTitle(R.string.dialog_title_modifying_removed_item)
                        .setMessage(R.string.warning_message_modifying_removed_item)
                        .setPositiveButton(R.string.btnOK) { _, _ ->
                            tableViewModel.setViewType(FormState.Type.ADD)
                            tableViewModel.currentItem.value = Table(
                                name = table_edit_name.editText?.text.toString()
                            )
                        }
                        .setNegativeButton(R.string.btnCancel) { _, _ ->
                            navController.navigateUp()
                        }
                        .show()
                }
            })
        }
        setupForm()
    }

    private fun setupForm() {
        table_edit_name.asEditText {
            tableViewModel.validate(
                Table(
                    name = it
                )
            )
        }
    }
}

