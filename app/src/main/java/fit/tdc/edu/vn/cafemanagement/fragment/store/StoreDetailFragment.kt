package fit.tdc.edu.vn.cafemanagement.fragment.store

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.FireBaseDataSource
import fit.tdc.edu.vn.cafemanagement.data.model.FormState
import fit.tdc.edu.vn.cafemanagement.data.model.store.Store
import fit.tdc.edu.vn.cafemanagement.data.model.store.StoreViewFormState
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.store.StoreDetailViewModel
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.store.StoreViewModelFactory
import fit.tdc.edu.vn.cafemanagement.fragment.BaseDetailViewModel
import fit.tdc.edu.vn.cafemanagement.fragment.BaseViewFragmentTest
import fit.tdc.edu.vn.cafemanagement.util.asEditText
import fit.tdc.edu.vn.cafemanagement.util.setupForLiveList
import kotlinx.android.synthetic.main.fragment_store_detail.*

class StoreDetailFragment : BaseViewFragmentTest<Store>(R.layout.fragment_store_detail) {
    override val viewModel: BaseDetailViewModel<Store>
        get() = ViewModelProvider(
            this,
            StoreViewModelFactory(FireBaseDataSource(), this)
        ).get<StoreDetailViewModel>()
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
            FormState.Type.MODIFY -> {
                enableForm(true)
                setupForm()
            }
            FormState.Type.ADD -> {
                enableForm(true)
                setupForm()
            }
            FormState.Type.VIEW -> {
                enableForm(false)
            }
        }
    }

    private fun enableForm(isEnabled: Boolean) {
        edtName.editText?.isEnabled = isEnabled
        edtAddress.editText?.isEnabled = isEnabled
        tilManager.isEndIconVisible = isEnabled
        manager.isEnabled = isEnabled
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
                manager.setupForLiveList(
                    context = requireContext(),
                    dataset = it.map { user -> user.name },
                    resId = R.layout.dropdown_menu_popup_item,
                    layout = tilManager,
                    emptySetString = R.string.warning_store_no_free_manager,
                    missingString = R.string.warning_store_missing_manager
                ) {position ->
                    viewModel.validate(
                        getCurrentFormData().apply {
                            managerId = it[position].id
                            managerName = it[position].name
                        }
                    )
                }
            })
    }

    override fun getCurrentFormData() = Store(
        name = edtName.editText?.text.toString(),
        address = edtAddress.editText?.text.toString(),
        managerName = manager.text.toString(),
        managerId = viewModel.saved.managerId
    )

    override fun fillFormWith(item: Store) {
        edtName.editText?.setText(item.name)
        edtAddress.editText?.setText(item.address)
        when {
            item.managerId != null -> manager.setText(item.managerName, false)
            else -> manager.setText(getString(R.string.warning_store_missing_manager), false)
        }
    }
}