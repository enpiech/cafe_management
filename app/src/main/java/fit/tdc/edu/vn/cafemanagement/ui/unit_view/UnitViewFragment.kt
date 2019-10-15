package fit.tdc.edu.vn.cafemanagement.ui.unit_create

import android.content.Context
import android.os.Bundle
import android.util.Log
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
import fit.tdc.edu.vn.cafemanagement.data.model.unit.Unit
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.unit_viewmodel.UnitCreateViewModel
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.unit_viewmodel.UnitViewModelFactory
import fit.tdc.edu.vn.cafemanagement.ui.login.afterTextChanged
import kotlinx.android.synthetic.main.unit_view_fragment.*

class UnitViewFragment : Fragment(R.layout.unit_view_fragment) {

    private val viewModel by lazy {
        ViewModelProvider(this, UnitViewModelFactory()).get(UnitCreateViewModel::class.java)
    }
    private val args: UnitViewFragmentArgs by navArgs()
    private val unitId by lazy {
        args.unitId
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.viewState.observe(this, Observer {
            when (it) {
                FormState.Type.ADD -> {
                    btn_modifyUnit.setText(R.string.btnAdd)
//                    imgUnitImage.isEnabled = true
                }
                FormState.Type.MODIFY -> {
                    // TODO: editable
                    edit_unit.isEnabled = true
//                    imgUnitImage.isEnabled = false
                    btn_modifyUnit.isEnabled = false
                    btn_modifyUnit.setText(R.string.btnUpdate)
                }
                FormState.Type.VIEW -> {
                    edit_unit.isEnabled = false
//                    imgUnitImage.isEnabled = true
                    btn_modifyUnit.setText(R.string.btnModify)
                }
                null -> {
                    when (unitId) {
                        null -> {
                            viewModel.setViewType(FormState.Type.ADD)
                        }
                        else -> {
                            viewModel.setViewType(FormState.Type.VIEW)
                            viewModel.getUnit(unitId!!)
                        }
                    }
                }
            }
        })
        viewModel.formState.observe(this, Observer {
            val state = it ?: return@Observer

            // disable managerLogin button unless both username / password is valid
            btn_modifyUnit.isEnabled = state.isDataValid

            if (state.nameError != null) {
                edit_unit.error = getString(state.nameError)
            }
        })
        viewModel.currentUnit.observe(this, Observer {
            if (edit_unit.text.isNullOrEmpty()) {
                when (it.status) {
                    Status.SUCCESS -> {
                        edit_unit.setText(it.data!!.name)
                    }
                }
            }
        })

        edit_unit.apply {
            afterTextChanged {
                viewModel.dataChange(
                    Unit(
                        name = edit_unit.text.toString()
                    )
                )
            }
            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        btn_modifyUnit.callOnClick()
                }
                false
            }
        }

//        imgUnitImage.setOnClickListener {
//
//        }

        btn_modifyUnit.setOnClickListener {
            val imm =
                view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)

            when (viewModel.viewState.value) {
                FormState.Type.ADD -> {
                    viewModel.insert(
                        Unit(
                            name = edit_unit.text.toString()
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
                        setTitle("Xóa")
                        setMessage("Bạn có muốn cập nhật thông tin này không?")
                        setPositiveButton("OK") { p0, p1 ->
                            viewModel.update(
                                Unit(
                                    name = edit_unit.text.toString()
                                ).also { it.id = unitId!! }
                            ).observe(viewLifecycleOwner, Observer { result ->
                                when (result.status) {
                                    TaskStatus.SUCCESS -> {
                                        Snackbar.make(it, "Cập nhật thành công tên: "+edit_unit.text, Snackbar.LENGTH_LONG).show()
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
