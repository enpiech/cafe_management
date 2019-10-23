package fit.tdc.edu.vn.cafemanagement.fragment.zone

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.model.FormState
import fit.tdc.edu.vn.cafemanagement.data.model.zone.Zone
import fit.tdc.edu.vn.cafemanagement.data.model.zone.ZoneViewFormState
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.zone_viewmodel.ZoneViewModel
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.zone_viewmodel.ZoneViewModelFactory
import fit.tdc.edu.vn.cafemanagement.fragment.BaseViewFragment
import fit.tdc.edu.vn.cafemanagement.util.asEditText
import kotlinx.android.synthetic.main.fragment_zone_view.*


class ZoneViewFragment : BaseViewFragment(R.layout.fragment_zone_view) {
    companion object {
        fun newInstance() = ZoneViewFragment()
    }

    private val viewModel by lazy {
        ViewModelProvider(this, ZoneViewModelFactory()).get<ZoneViewModel>()
    }

    private val args by navArgs<ZoneViewFragmentArgs>()
    private val zoneId by lazy {
        args.zoneId
    }

    private val navController by lazy {
        findNavController()
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
                        viewModel.getCurrentItem(zoneId!!)
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
                val zoneFormState = state as ZoneViewFormState
                if (zoneFormState.nameError != null) {
                    edtName.error = getString(zoneFormState.nameError)
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
                    navController.navigateUp()
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
                    navController.navigateUp()
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
            viewModel.currentItem.observe(this, Observer {
                if (it != null) {
                    edtName.editText?.setText(it.name)
                } else {
                    MaterialAlertDialogBuilder(context)
                        .setTitle(R.string.dialog_title_modifying_removed_item)
                        .setMessage(R.string.warning_message_modifying_removed_item)
                        .setPositiveButton(R.string.btnOK) { _, _ ->
                            viewModel.setViewType(FormState.Type.ADD)
                            viewModel.dataChange(
                                Zone(
                                    name = edtName.editText?.text.toString()
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
        edtName.asEditText {
            viewModel.dataChange(
                Zone(
                    name = it
                )
            )
        }
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
}
