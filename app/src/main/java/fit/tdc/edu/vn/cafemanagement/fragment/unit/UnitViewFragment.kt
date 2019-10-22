package fit.tdc.edu.vn.cafemanagement.fragment.unit

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.model.FormState
import fit.tdc.edu.vn.cafemanagement.data.model.unit.Unit
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.unit_viewmodel.UnitCreateViewModel
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.unit_viewmodel.UnitViewModelFactory
import fit.tdc.edu.vn.cafemanagement.fragment.BaseViewFragment
import fit.tdc.edu.vn.cafemanagement.util.afterTextChanged
import fit.tdc.edu.vn.cafemanagement.util.setupFocusHandle
import kotlinx.android.synthetic.main.fragment_unit_view.*

class UnitViewFragment : BaseViewFragment(R.layout.fragment_unit_view) {
    private val viewModel by lazy {
        ViewModelProvider(this, UnitViewModelFactory()).get<UnitCreateViewModel>()
    }
    private val args: UnitViewFragmentArgs by navArgs()
    private val unitId by lazy {
        args.unitId
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.viewType.observe(this, Observer {
            if (it == null) {
                when (unitId) {
                    null -> {
                        viewModel.setViewType(FormState.Type.ADD)
                    }
                    else -> {
                        viewModel.setViewType(FormState.Type.VIEW)
                        viewModel.getUnit(unitId!!)
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
                    edtName.error = getString(state.nameError)
                }
            }
        })

        fab.setOnClickListener {
            when (viewModel.viewType.value) {
                FormState.Type.ADD -> {
                    viewModel.insert(
                        Unit(
                            name = edtName.editText?.text.toString()
                        )
                    )
                    findNavController().navigateUp()
                }
                FormState.Type.VIEW -> {
                    viewModel.setViewType(FormState.Type.MODIFY)
                }
                FormState.Type.MODIFY -> {
                    viewModel.update(
                        Unit(
                            name = edtName.editText?.text.toString()
                        ).also { it.id = unitId!! }
                    )
                    findNavController().navigateUp()
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
                edtName.editText?.isEnabled = true
            }
            FormState.Type.VIEW -> {
                edtName.editText?.isEnabled = false
            }
        }

        if (type != FormState.Type.ADD) {
            viewModel.currentUnit.observe(this, Observer {
                if (it != null) {
                    edtName.editText?.setText(it.name)
                } else {
                    TODO("Notify user about this unit has been removed")
                }
            })
        }
        setupForm()
    }

    private fun setupForm() {
        edtName.setupFocusHandle()

        edtName.editText?.let {
            it.afterTextChanged { name ->
                viewModel.dataChange(
                    Unit(
                        name = name
                    )
                )
            }
            it.setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        viewModel.dataChange(
                            Unit(
                                name = edtName.editText?.text.toString()
                            )
                        )
                }
                false
            }
        }
    }
}
