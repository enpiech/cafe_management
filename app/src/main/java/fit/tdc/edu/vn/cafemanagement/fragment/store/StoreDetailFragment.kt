package fit.tdc.edu.vn.cafemanagement.fragment.store

import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.model.FormState
import fit.tdc.edu.vn.cafemanagement.data.model.store.Store
import fit.tdc.edu.vn.cafemanagement.data.model.store.StoreViewFormState
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.store.StoreDetailViewModel
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.store.StoreViewModelFactory
import fit.tdc.edu.vn.cafemanagement.fragment.BaseDetailViewModel
import fit.tdc.edu.vn.cafemanagement.fragment.BaseViewFragmentTest
import fit.tdc.edu.vn.cafemanagement.util.asEditText
import kotlinx.android.synthetic.main.fragment_store_detail.*

class StoreDetailFragment : BaseViewFragmentTest<Store>(R.layout.fragment_store_detail) {
    override val viewModel: BaseDetailViewModel<Store>
        get() = ViewModelProvider(this, StoreViewModelFactory()).get<StoreDetailViewModel>()
    override val navController: NavController
        get() = findNavController()
    override val itemId: String?
        get() = args.storeId
    override val args by navArgs<StoreDetailFragmentArgs>()

    override fun showError(state: FormState) {
        val userFormState = state as StoreViewFormState

        if (userFormState.nameError != null) {
            edtName.error = getString(userFormState.nameError!!)
        } else {
            edtName.error = null
            edtName.isErrorEnabled = false
        }
        if (userFormState.addressError != null) {
            edtAddress.error = getString(userFormState.addressError!!)
        } else {
            edtAddress.error = null
            edtAddress.isErrorEnabled = false
        }
    }

    override fun updateUI(type: FormState.Type) {
        when (type) {
            FormState.Type.MODIFY, FormState.Type.ADD -> {
                enableForm(true)
            }
            FormState.Type.VIEW -> {
                enableForm(false)
            }
        }

        setupForm()
    }

    private fun enableForm(isEnabled: Boolean) {
        edtName.editText?.isEnabled = isEnabled
        edtAddress.editText?.isEnabled = isEnabled
        tilManager.isEndIconVisible = isEnabled
        manager.isEnabled = isEnabled
    }

    override fun getCurrentFormData(): Store {
        return Store(
            name = edtName.editText?.text.toString(),
            address = edtAddress.editText?.text.toString()
        ).also {
            it.managerId = (viewModel as StoreDetailViewModel).currentStoreManager.value?.id
            it.managerName = (viewModel as StoreDetailViewModel).currentStoreManager.value?.name
        }
    }

    override fun fillFormWith(item: Store) {
        edtName.editText?.setText(item.name)
        edtAddress.editText?.setText(item.address)
        (viewModel as StoreDetailViewModel).currentStoreManager.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer { cmanager ->
                cmanager?.name?.let { name ->
                    manager.setText(name, false)
                    (viewModel as StoreDetailViewModel).currentStoreManager.removeObservers(
                        viewLifecycleOwner
                    )
                }
            })
    }

    override fun setupForm() {
        edtName.asEditText {
            viewModel.validate(
                getCurrentFormData()
            )
        }
        edtAddress.asEditText {
            viewModel.validate(
                getCurrentFormData()
            )
        }
        (viewModel as StoreDetailViewModel).userList.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer {
                if (it.isNotEmpty()) {
                    val list = it.map { user -> user.name }
                    manager.setAdapter(
                        ArrayAdapter(
                            requireContext(),
                            R.layout.dropdown_menu_popup_item,
                            list
                        )
                    )
                } else {
                    if ((viewModel as StoreDetailViewModel).currentStoreManager.value != null) {
                        manager.setText(getText(R.string.warning_store_no_free_manager), false)
                    } else {
                        manager.setText(getText(R.string.warning_store_missing_manager), false)
                    }
                    tilManager.isEndIconVisible = false
                    manager.isEnabled = false
                }
            })
        manager.setOnItemClickListener { parent, view, position, id ->
            viewModel.validate(
                getCurrentFormData().apply {
                    managerId =
                        (viewModel as StoreDetailViewModel).userList.value?.get(position)?.id
                }
            )
            (viewModel as StoreDetailViewModel).currentStoreManager.value =
                (viewModel as StoreDetailViewModel).userList.value?.get(position)
        }
    }

}