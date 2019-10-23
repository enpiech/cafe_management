package fit.tdc.edu.vn.cafemanagement.fragment.category

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
import fit.tdc.edu.vn.cafemanagement.data.model.category.Category
import fit.tdc.edu.vn.cafemanagement.data.model.category.CategoryViewFormState
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.category_viewmodel.CategoryViewModelFactory
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.category_viewmodel.CategoryViewViewModel
import fit.tdc.edu.vn.cafemanagement.fragment.BaseViewFragment
import fit.tdc.edu.vn.cafemanagement.util.asEditText
import kotlinx.android.synthetic.main.fragment_category_view.*

class CategoryViewFragment : BaseViewFragment(R.layout.fragment_category_view) {

    private val viewModel by lazy {
        ViewModelProvider(this, CategoryViewModelFactory()).get<CategoryViewViewModel>()
    }
    private val args: CategoryViewFragmentArgs by navArgs()
    private val categoryId by lazy {
        args.categoryId
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.viewType.observe(this, Observer {
            if (it == null) {
                    when (categoryId) {
                    null -> {
                        viewModel.setViewType(FormState.Type.ADD)
                    }
                    else -> {
                        viewModel.setViewType(FormState.Type.VIEW)
                        viewModel.getItem(categoryId!!)
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
                val categoryFormState = state as CategoryViewFormState
                if (categoryFormState.nameError != null) {
                    edtName.error = getString(categoryFormState.nameError)
                }
            }
        })

        fab.setOnClickListener {
            when (viewModel.viewType.value) {
                FormState.Type.ADD -> {
                    viewModel.insert(
                        Category(
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
                        Category(
                            name = edtName.editText?.text.toString()
                        ).also { it.id = categoryId!! }
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
            viewModel.currentItem.observe(this, Observer {
                if (it != null) {
                    edtName.editText?.setText(it.name)
                } else {
                    TODO("Notify user about this category has been removed")
                }
            })
        }
        setupForm()
    }

    private fun setupForm() {
        edtName.asEditText {
            viewModel.dataChange(
                Category(
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
