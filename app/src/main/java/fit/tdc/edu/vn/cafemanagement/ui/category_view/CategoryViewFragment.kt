package fit.tdc.edu.vn.cafemanagement.ui.category_view

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.extension.Status
import fit.tdc.edu.vn.cafemanagement.data.extension.TaskStatus
import fit.tdc.edu.vn.cafemanagement.data.model.FormState
import fit.tdc.edu.vn.cafemanagement.data.model.category.Category
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.category_viewmodel.CategoryViewModelFactory
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.category_viewmodel.CategoryViewViewModel
import fit.tdc.edu.vn.cafemanagement.ui.login.afterTextChanged
import kotlinx.android.synthetic.main.fragment_category_view.*

class CategoryViewFragment : Fragment(R.layout.fragment_category_view) {

    private val viewModel by lazy {
        ViewModelProvider(this, CategoryViewModelFactory()).get(CategoryViewViewModel::class.java)
    }
    private val args: CategoryViewFragmentArgs by navArgs()
    private val categoryId by lazy {
        args.categoryId
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.viewState.observe(this, Observer {
            when (it) {
                FormState.Type.ADD -> {
                    btn_modifyCategory.setText(R.string.btnAdd)
//                    imgCategoryImage.isEnabled = true
                }
                FormState.Type.MODIFY -> {
                    // TODO: editable
                    edit_category.isEnabled = true
//                    imgCategoryImage.isEnabled = false
                    btn_modifyCategory.isEnabled = false
                    btn_modifyCategory.setText(R.string.btnUpdate)
                }
                FormState.Type.VIEW -> {
                    edit_category.isEnabled = false
//                    imgCategoryImage.isEnabled = true
                    btn_modifyCategory.setText(R.string.btnModify)
                }
                null -> {
                    when (categoryId) {
                        null -> {
                            viewModel.setViewType(FormState.Type.ADD)
                        }
                        else -> {
                            viewModel.setViewType(FormState.Type.VIEW)
                            viewModel.getCategory(categoryId!!)
                        }
                    }
                }
            }
        })
        viewModel.formState.observe(this, Observer {
            val state = it ?: return@Observer

            // disable managerLogin button unless both username / password is valid
            btn_modifyCategory.isEnabled = state.isDataValid

            if (state.nameError != null) {
                edit_category.error = getString(state.nameError)
            }
        })
        viewModel.currentCategory.observe(this, Observer {
            if (edit_category.text.isNullOrEmpty()) {
                when (it.status) {
                    Status.SUCCESS -> {
                        edit_category.setText(it.data!!.name)
                    }
                }
            }
        })

        edit_category.apply {
            afterTextChanged {
                viewModel.dataChange(
                    Category(
                        name = edit_category.text.toString()
                    )
                )
            }
            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        btn_modifyCategory.callOnClick()
                }
                false
            }
        }

//        imgCategoryImage.setOnClickListener {
//
//        }

        btn_modifyCategory.setOnClickListener {
            val imm =
                view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)

            when (viewModel.viewState.value) {
                FormState.Type.ADD -> {
                    viewModel.insert(
                        Category(
                            name = edit_category.text.toString()
                        )
                    ).observe(this, Observer {
                        //back
                        findNavController().navigateUp()
                    })
                }
                FormState.Type.VIEW -> {
                    viewModel.setViewType(FormState.Type.MODIFY)
                }
                FormState.Type.MODIFY -> {
                    val builder = android.app.AlertDialog.Builder(context)
                    with(builder)
                    {
                        setTitle("Cập nhật")
                        setMessage("Bạn có muốn cập nhật thông tin này không?")
                        setPositiveButton("OK") { p0, p1 ->
                            viewModel.update(
                                Category(
                                    name = edit_category.text.toString()
                                ).also { it.id = categoryId!! }
                            ).observe(viewLifecycleOwner, Observer { result ->
                                when (result.status) {
                                    TaskStatus.SUCCESS -> {
                                        Snackbar.make(it, "Cập nhật thành công tên: "+edit_category.text, Snackbar.LENGTH_LONG).show()
                                        findNavController().navigateUp()
                                    }
                                }
                            })
                        }
                        setNegativeButton("Hủy") { p0, p1 ->
                            Snackbar.make(it, "Hủy", Snackbar.LENGTH_SHORT).show()
                            viewModel.setViewType(FormState.Type.MODIFY)
                        }
                        show()
                    }

                    viewModel.setViewType(FormState.Type.VIEW)
                }
            }
        }
        super.onViewCreated(view, savedInstanceState)
    }
}
