package fit.tdc.edu.vn.cafemanagement.ui.zone

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.extension.Status
import fit.tdc.edu.vn.cafemanagement.data.extension.TaskStatus
import fit.tdc.edu.vn.cafemanagement.data.model.FormState
import fit.tdc.edu.vn.cafemanagement.data.model.zone.Zone
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.zone_viewmodel.ZoneViewModel
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.zone_viewmodel.ZoneViewModelFactory
import fit.tdc.edu.vn.cafemanagement.ui.login.afterTextChanged
import fit.tdc.edu.vn.cafemanagement.ui.zone_view.ZoneViewFragmentArgs
import kotlinx.android.synthetic.main.fragment_zone_view.*

class ZoneViewFragment : Fragment(R.layout.fragment_zone_view) {

    companion object {
        fun newInstance() = ZoneViewFragment()
    }

    private val viewModel by lazy {
        ViewModelProvider(this, ZoneViewModelFactory()).get(ZoneViewModel::class.java)
    }
    private val args: ZoneViewFragmentArgs by navArgs()
    private val zoneId by lazy {
        args.zoneId
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.viewState.observe(this, Observer {
            when (it) {
                FormState.Type.ADD -> {
                    btnModify.setText(R.string.btnAdd)
                    imgZoneImage.isEnabled = true
                }
                FormState.Type.MODIFY -> {
                    // TODO: editable
                    edtZoneName.isEnabled = true
                    imgZoneImage.isEnabled = false
                    btnModify.setText(R.string.btnUpdate)
                }
                FormState.Type.VIEW -> {
                    edtZoneName.isEnabled = false
                    imgZoneImage.isEnabled = true
                    btnModify.setText(R.string.btnModify)
                }
                null -> {
                    when (zoneId) {
                        null -> {
                            viewModel.setViewType(FormState.Type.ADD)
                        }
                        else -> {
                            viewModel.setViewType(FormState.Type.VIEW)
                            viewModel.getZone(zoneId!!)
                        }
                    }
                }
            }
        })
        viewModel.formState.observe(this, Observer {
            val state = it ?: return@Observer

            // disable managerLogin button unless both username / password is valid
            btnModify.isEnabled = state.isDataValid

            if (state.nameError != null) {
                edtZoneName.error = getString(state.nameError)
            }
        })
        viewModel.currentZone.observe(this, Observer {
            if (edtZoneName.text.isNullOrEmpty()) {
                when (it.status) {
                    Status.SUCCESS -> {
                        edtZoneName.setText(it.data!!.name)
                    }
                }
            }
        })

        edtZoneName.apply {
            afterTextChanged {
                viewModel.dataChange(
                    Zone(
                        name = edtZoneName.text.toString()
                    )
                )
            }
            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        btnModify.callOnClick()
                }
                false
            }
        }

        imgZoneImage.setOnClickListener {

        }

        btnModify.setOnClickListener {
            val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)

            when (viewModel.viewState.value) {
                FormState.Type.ADD -> {
                    viewModel.insert(
                        Zone(
                            name = edtZoneName.text.toString()
                        )
                    ).observe(this, Observer { result ->
                        when (result.status) {
                            TaskStatus.SUCCESS -> {
                                Snackbar.make(it, R.string.addComplete, Snackbar.LENGTH_LONG).show()
                            }
                            TaskStatus.FAILED -> {
                                if (result.exception != null) {
                                    Snackbar.make(it, result.exception.localizedMessage, Snackbar.LENGTH_LONG).show()
                                } else {
                                    Snackbar.make(it, R.string.addFailed, Snackbar.LENGTH_LONG).show()
                                }

                            }
                        }
                    })
                }
                FormState.Type.VIEW -> {
                    viewModel.setViewType(FormState.Type.MODIFY)
                }
                FormState.Type.MODIFY -> {
                    viewModel.update(
                        Zone(
                            name = edtZoneName.text.toString()
                        ).also { it.id = zoneId!! }
                    ).observe(this, Observer { result ->
                        when (result.status) {
                            TaskStatus.SUCCESS -> {
                                Snackbar.make(it, R.string.updateComplete, Snackbar.LENGTH_LONG).show()
                            }
                            TaskStatus.FAILED -> {
                                if (result.exception != null) {
                                    Snackbar.make(it, result.exception.localizedMessage, Snackbar.LENGTH_LONG).show()
                                } else {
                                    Snackbar.make(it, R.string.updateFailed, Snackbar.LENGTH_LONG).show()
                                }

                            }
                        }
                    })
                    viewModel.setViewType(FormState.Type.VIEW)
                }
            }
        }
        super.onViewCreated(view, savedInstanceState)
    }
}
