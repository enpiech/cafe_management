package fit.tdc.edu.vn.cafemanagement.fragment.zone

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
import fit.tdc.edu.vn.cafemanagement.data.model.zone.Zone
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.zone_viewmodel.ZoneViewModel
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.zone_viewmodel.ZoneViewModelFactory
import fit.tdc.edu.vn.cafemanagement.fragment.BaseViewFragment
import fit.tdc.edu.vn.cafemanagement.util.afterTextChanged
import fit.tdc.edu.vn.cafemanagement.util.setupFocusHandle
import kotlinx.android.synthetic.main.fragment_zone_view.*


class ZoneViewFragment : BaseViewFragment(R.layout.fragment_zone_view) {
    companion object {
        fun newInstance() = ZoneViewFragment()
    }

    private val viewModel by lazy {
        ViewModelProvider(this, ZoneViewModelFactory()).get<ZoneViewModel>()
    }

    private val args: ZoneViewFragmentArgs by navArgs()
    private val zoneId by lazy {
        args.zoneId
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.viewType.observe(this, Observer {
            if (it == null) {
                when (zoneId) {
                    null -> {
                        viewModel.setViewType(FormState.Type.ADD)
                    }
                    else -> {
                        viewModel.setViewType(FormState.Type.VIEW)
                        viewModel.getZone(zoneId!!)
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
                        Zone(
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
                        Zone(
                            name = edtName.editText?.text.toString()
                        ).also { it.id = zoneId!! }
                    )
                    findNavController().navigateUp()
                }
            }
        }
        super.onViewCreated(view, savedInstanceState)
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
            viewModel.currentZone.observe(this, Observer {
                if (it != null) {
                    edtName.editText?.setText(it.name)
                } else {
                    TODO("Notify user about this zone has been removed")
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
                    Zone(
                        name = name
                    )
                )
            }
            it.setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        viewModel.dataChange(
                            Zone(
                                name = edtName.editText?.text.toString()
                            )
                        )
                }
                false
            }
        }
    }

    override fun requestNavigateUp() {
        when (viewModel.viewType.value) {
            FormState.Type.MODIFY, FormState.Type.ADD -> {
                if (viewModel.formState.value?.isChanged == true) {
                    MaterialAlertDialogBuilder(context)
                        .setTitle(R.string.dialog_title_warning)
                        .setMessage(R.string.warning_message_unsaved_changed)
                        .setPositiveButton(R.string.btnOK) { _, _ -> viewModel.setViewType(FormState.Type.VIEW) }
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
            else -> {
                findNavController().navigateUp()
            }
        }
    }
}
