package fit.tdc.edu.vn.cafemanagement.fragment.table

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
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
import fit.tdc.edu.vn.cafemanagement.data.model.zone.Zone
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.table_viewmodel.TableDetailViewModel
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.table_viewmodel.TableViewModelFactory
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.zone_viewmodel.ZoneListViewModel
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.zone_viewmodel.ZoneViewModelFactory
import fit.tdc.edu.vn.cafemanagement.fragment.BaseViewFragment
import fit.tdc.edu.vn.cafemanagement.util.asEditText
import kotlinx.android.synthetic.main.form_table.*

class TableViewFragment : BaseViewFragment(R.layout.fragment_table_view) {

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

    private var tableName: String? = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var listZones: List<Zone>? = null
        var listTable: List<Table>? = null
        var currentTable: Table = Table()

        tableViewModel.getAllItems().observe(this, Observer {
            listTable = it.data
            listTable!!.forEach {
                if (it.name.equals(tableName)) {
                    currentTable = it
                }
            }
        })

        zoneViewModel.getAllItems().observe(this, Observer {
            listZones = it.data
            var arrZoneName: Array<String?>

            val spinnerAdapter = listZones?.let { it1 ->
                var i = 0
                arrZoneName = Array<String?>(listZones!!.size) {""}
                listZones!!.forEach {
                    arrZoneName[i] = it.name
                    if(it.id.equals(currentTable.zoneId)) {
                        table_spinner.setSelection(i)
                    }
                    i++
                }
                ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item,
                    arrZoneName
                )
            }
            table_spinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onNothingSelected(p0: AdapterView<*>?) {
                    Toast.makeText(requireContext(), "Nothing Selected", Toast.LENGTH_SHORT).show()
                }

                override fun onItemSelected(items: AdapterView<*>?, view: View?, position: Int, p3: Long) {
                    Log.d("test", "adawdw "+items!!.getItemAtPosition(position)).toString()
                    var zoneID = items!!.getItemAtPosition(position)
                }

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
            var zoneID = ""
            listZones!!.forEach {
                if(it.name.equals(table_spinner.selectedItem.toString())) {
                    zoneID = it.id
                    Log.d("test", "aaaaaaaaaaaa "+zoneID)
                }
            }

            when (tableViewModel.viewType.value) {

                FormState.Type.ADD -> {
                    tableViewModel.insert(
                        Table(
                            name = table_edit_name.editText?.text.toString(),
                            zoneId = zoneID,
                            state = Table.Companion.TableState.FREE
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
                            name = table_edit_name.editText?.text.toString(),
                            zoneId = zoneID
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
                    tableName = it.name
                } else {
                    MaterialAlertDialogBuilder(context)
                        .setTitle(R.string.dialog_title_modifying_removed_item)
                        .setMessage(R.string.warning_message_modifying_removed_item)
                        .setPositiveButton(R.string.btnOK) { _, _ ->
                            tableViewModel.setViewType(FormState.Type.ADD)
                            tableViewModel.validate(
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
            tableViewModel.validate(
                Table(
                    name = it
                )
            )
        }
    }
}

