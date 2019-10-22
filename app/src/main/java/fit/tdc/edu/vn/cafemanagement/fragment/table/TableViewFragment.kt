package fit.tdc.edu.vn.cafemanagement.fragment.table

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.model.FormState
import fit.tdc.edu.vn.cafemanagement.data.model.table.Table
import fit.tdc.edu.vn.cafemanagement.data.model.zone.Zone
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.table_viewmodel.TableViewModel
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.table_viewmodel.TableViewModelFactory
import fit.tdc.edu.vn.cafemanagement.fragment.BaseViewFragment
import fit.tdc.edu.vn.cafemanagement.util.afterTextChanged
import fit.tdc.edu.vn.cafemanagement.util.setupFocusHandle
import kotlinx.android.synthetic.main.form_table.*
import kotlinx.android.synthetic.main.fragment_zone_view.*

class TableViewFragment : BaseViewFragment(R.layout.fragment_table_view) {


    companion object {
        fun newInstance() = TableViewFragment()
    }

    private val viewModel by lazy {
        ViewModelProvider(this, TableViewModelFactory()).get<TableViewModel>()
    }

    private val args by navArgs<TableViewFragmentArgs>()
    private val tableId by lazy {
        args.tableId
    }

    private val navController by lazy {
        findNavController()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.viewType.observe(this, Observer {
            if (it == null) {
                when (tableId) {
                    null -> {
                        viewModel.setViewType(FormState.Type.ADD)
                    }
                    else -> {
                        viewModel.setViewType(FormState.Type.VIEW)
                        viewModel.getCurrentTable(tableId!!)
                    }
                }
            } else {
                changeViewType(it)
            }
        })

        viewModel.formState.observe(this, Observer {
            val state = it ?: return@Observer
            if (viewModel.viewType.value != FormState.Type.VIEW) {
                if (state.isDataValid && state.isChanged) {
                    fab.show()
                } else {
                    fab.hide()
                }

                if (state.nameError != null) {
                    table_edit_name.error = getString(state.nameError)
                }
            }
        })

        fab.setOnClickListener {
            when (viewModel.viewType.value) {
                FormState.Type.ADD -> {
                    viewModel.insert(
                        Table(
                            name = table_edit_name.editText?.text.toString()
                        )
                    )
                    navController.navigateUp()
                }
                FormState.Type.VIEW -> {
                    viewModel.setViewType(FormState.Type.MODIFY)
                }
                FormState.Type.MODIFY -> {
                    viewModel.update(
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
        if (viewModel.viewType.value == FormState.Type.VIEW) {
            findNavController().navigateUp()
            return
        }

        if (viewModel.formState.value?.isChanged == true) {
            MaterialAlertDialogBuilder(context)
                .setTitle(R.string.dialog_title_warning)
                .setMessage(R.string.warning_message_unsaved_changed)
                .setPositiveButton(R.string.btnOK) { _, _ ->
                    when (viewModel.viewType.value) {
                        FormState.Type.MODIFY -> viewModel.setViewType(FormState.Type.VIEW)
                        FormState.Type.ADD -> findNavController().navigateUp()
                    }
                }
                .setNegativeButton(R.string.btnCancel, null)
                .show()
        } else {
            if (viewModel.viewType.value == FormState.Type.ADD) {
                findNavController().navigateUp()
            } else {
                viewModel.setViewType(FormState.Type.VIEW)
            }
        }
    }

    override fun updateUI(type: FormState.Type) {
        when (type) {
            FormState.Type.MODIFY, FormState.Type.ADD -> {
                table_edit_name.isEnabled = true
            }
            FormState.Type.VIEW -> {
                table_edit_name.isEnabled = false
            }
        }

        if (type != FormState.Type.ADD) {
            viewModel.currentTable.observe(this, Observer {
                if (it != null) {
                    table_edit_name.editText?.setText(it.name)
                } else {
                    MaterialAlertDialogBuilder(context)
                        .setTitle(R.string.dialog_title_modifying_removed_item)
                        .setMessage(R.string.warning_message_modifying_removed_item)
                        .setPositiveButton(R.string.btnOK) { _, _ ->
                            viewModel.setViewType(FormState.Type.ADD)
                            viewModel.dataChange(
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
        table_edit_name.setupFocusHandle()

        table_edit_name.editText?.let {
            it.afterTextChanged { name ->
                viewModel.dataChange(
                    Table(
                        name = name
                    )
                )
            }
            it.setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        viewModel.dataChange(
                            Table(
                                name = table_edit_name.editText?.text.toString()
                            )
                        )
                }
                false
            }
        }
    }



}
