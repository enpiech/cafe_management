package fit.tdc.edu.vn.cafemanagement.fragment.table

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import android.widget.Spinner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.adapter.SpinnerZoneAdapter
import fit.tdc.edu.vn.cafemanagement.data.adapter.SpinnerZoneHolder
import fit.tdc.edu.vn.cafemanagement.data.adapter.TableAdapter
import fit.tdc.edu.vn.cafemanagement.data.adapter.ZoneHolder
import fit.tdc.edu.vn.cafemanagement.data.model.FormState
import fit.tdc.edu.vn.cafemanagement.data.model.table.Table
import fit.tdc.edu.vn.cafemanagement.data.model.table.TableViewFormState
import fit.tdc.edu.vn.cafemanagement.data.model.zone.Zone
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.table_viewmodel.TableViewModel
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.table_viewmodel.TableViewModelFactory
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.zone_viewmodel.ZoneViewModel
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.zone_viewmodel.ZoneViewModelFactory
import fit.tdc.edu.vn.cafemanagement.fragment.BaseViewFragment
import fit.tdc.edu.vn.cafemanagement.util.asEditText
import kotlinx.android.synthetic.main.form_table.*

class TableViewFragment : BaseViewFragment(R.layout.fragment_table_view) {

    val spinnerAdapter = SpinnerZoneAdapter()

    companion object {
        fun newInstance() = TableViewFragment()
    }

    private val tableViewModel by lazy {
        ViewModelProvider(this, TableViewModelFactory()).get<TableViewModel>()
    }

    private val zoneViewModel by lazy {
        ViewModelProvider(this, ZoneViewModelFactory()).get<ZoneViewModel>()
    }

    private val args by navArgs<TableViewFragmentArgs>()
    private val tableId by lazy {
        args.tableId
    }

    private val navController by lazy {
        findNavController()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        spinnerAdapter.fetchSpinnerItems().observe(this, Observer {
//            val spinnerAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, it)
//            table_spinner.adapter = spinnerAdapter
//        })

//        zoneViewModel.getAllItems().observe(this, Observer {
////            val spinner = spinnerAdapter.submitList(it.data)
////            table_spinner.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinnerAdapter.submitList(it.data))
////            Log.d("test", "testtt "+it.data.toString())
//            val options = arrayOf(Zone.apply {
//                var zone:Zone
//                return@apply
//                zone.name
//            })
//
//            val adapter = options?.let { it1 ->
//                ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item,
//                    it1
//                )
//            }
//            table_spinner.adapter = adapter
//        })

        zoneViewModel.getAllItems().observe(this, Observer {
            spinnerAdapter.submitList(it.data)
            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, spinnerAdapter.currentList)
            Log.d("test", "casdads"+spinnerAdapter.currentList)
            table_spinner.adapter = adapter
        })

//        zoneViewModel.getAllItems().observe(this, Observer {
//            val options = it.data
//            val spinnerAdapter = options?.let { it1 ->
//                ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item,
//                    it1
//                )
//            }
//            table_spinner.adapter = spinnerAdapter
//        })

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
                            tableViewModel.dataChange(
                                Table(
                                    name = table_edit_name.editText?.text.toString()
                                )
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
            tableViewModel.dataChange(
                Table(
                    name = it
                )
            )
        }
    }
}

