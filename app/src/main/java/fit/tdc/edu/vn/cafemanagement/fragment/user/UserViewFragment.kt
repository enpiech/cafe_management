package fit.tdc.edu.vn.cafemanagement.fragment.user

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
import fit.tdc.edu.vn.cafemanagement.data.model.user.User
import fit.tdc.edu.vn.cafemanagement.data.model.user.UserViewFormState
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.user_viewmodel.UserViewModel
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.user_viewmodel.UserViewModelFactory
import fit.tdc.edu.vn.cafemanagement.fragment.BaseViewFragment
import fit.tdc.edu.vn.cafemanagement.util.asDatePicker
import fit.tdc.edu.vn.cafemanagement.util.asEditText
import kotlinx.android.synthetic.main.fragment_user_view.*


class UserViewFragment : BaseViewFragment(R.layout.fragment_user_view) {
    companion object {
        fun newInstance() = UserViewFragment()
    }

    private val viewModel by lazy {
        ViewModelProvider(this, UserViewModelFactory()).get<UserViewModel>()
    }

    private val args by navArgs<UserViewFragmentArgs>()
    private val userId by lazy {
        args.userId
    }

    private val navController by lazy {
        findNavController()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.viewType.observe(this, Observer {
            if (it == null) {
                when (userId) {
                    null -> {
                        viewModel.setViewType(FormState.Type.ADD)
                    }
                    else -> {
                        viewModel.setViewType(FormState.Type.VIEW)
                        viewModel.getCurrentItem(userId!!)
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

                val userFormState = state as UserViewFormState
                if (userFormState.nameError != null) {
                    edtName.error = getString(userFormState.nameError)
                }
            }
        })

        fab.setOnClickListener {
            when (viewModel.viewType.value) {
                FormState.Type.ADD -> {
                    viewModel.insert(
                        User(
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
                        User(
                            name = edtName.editText?.text.toString()
                        ).also { it.id = userId!! }
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
                edtId.editText?.isEnabled = true
                edtPassword.editText?.isEnabled = true
                edtName.editText?.isEnabled = true
                edtBirth.editText?.isEnabled = true
                edtBirth.isEndIconVisible = true
            }
            FormState.Type.VIEW -> {
                edtId.editText?.isEnabled = false
                edtPassword.editText?.isEnabled = false
                edtName.editText?.isEnabled = false
                edtBirth.editText?.isEnabled = false
//                edtBirth.setEndIconActivated(false)
                edtBirth.isEndIconVisible = false
            }
        }

        if (type == FormState.Type.MODIFY) {
            edtId.editText?.isEnabled = false
        }

        if (type != FormState.Type.ADD) {
            viewModel.currentItem.observe(this, Observer {
                if (it != null) {
                    edtId.editText?.setText(it.id)
                    edtPassword.editText?.setText(R.string.hiddenPassword)
                    edtName.editText?.setText(it.name)
                    //TODO
                    edtBirth.editText?.setText(it.birth?.toDate().toString())
                } else {
                    MaterialAlertDialogBuilder(context)
                        .setTitle(R.string.dialog_title_modifying_removed_item)
                        .setMessage(R.string.warning_message_modifying_removed_item)
                        .setPositiveButton(R.string.btnOK) { _, _ ->
                            viewModel.setViewType(FormState.Type.ADD)
                            viewModel.dataChange(
                                User(
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
                User(
                    name = edtName.editText?.text.toString()
                )
            )
        }

        edtBirth.asDatePicker(requireContext()) {
            viewModel.dataChange(
                User(
                    birth = it
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
