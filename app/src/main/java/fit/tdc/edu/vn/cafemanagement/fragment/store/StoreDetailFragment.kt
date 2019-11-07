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
import fit.tdc.edu.vn.cafemanagement.fragment.BaseDetailFragment
import fit.tdc.edu.vn.cafemanagement.util.asEditText
import fit.tdc.edu.vn.cafemanagement.util.setupForLiveList
import kotlinx.android.synthetic.main.fragment_detail_store.*

class StoreDetailFragment : BaseDetailFragment<Store>(R.layout.fragment_detail_store) {
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
            unit_name.error = getString(userFormState.nameError!!)
        } else {
            unit_name.error = null
            unit_name.isErrorEnabled = false
        }
        if (userFormState.addressError != null) {
            user_address.error = getString(userFormState.addressError!!)
        } else {
            user_address.error = null
            user_address.isErrorEnabled = false
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
        unit_name.editText?.isEnabled = isEnabled
        user_address.editText?.isEnabled = isEnabled
        store_manager_layout.isEndIconVisible = isEnabled
        store_manager.isEnabled = isEnabled
    }

    override fun setupForm() {
        unit_name.asEditText {
            viewModel.validate(
                getCurrentFormData()
            )
        }
        user_address.asEditText {
            viewModel.validate(
                getCurrentFormData()
            )
        }
        (viewModel as StoreDetailViewModel).userList.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer {
                store_manager.setupForLiveList(
                    context = requireContext(),
                    dataset = it.map { user -> user.name },
                    resId = R.layout.dropdown_menu_popup_item,
                    layout = store_manager_layout,
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
        name = unit_name.editText?.text.toString(),
        address = user_address.editText?.text.toString(),
        managerName = store_manager.text.toString(),
        managerId = viewModel.saved.managerId
    )

    override fun fillFormWith(item: Store) {
        unit_name.editText?.setText(item.name)
        user_address.editText?.setText(item.address)
        when {
            item.managerId != null -> store_manager.setText(item.managerName, false)
            else -> store_manager.setText(getString(R.string.warning_store_missing_manager), false)
        }
    }
}