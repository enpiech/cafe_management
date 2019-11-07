package fit.tdc.edu.vn.cafemanagement.fragment.user

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.FireBaseDataSource
import fit.tdc.edu.vn.cafemanagement.data.model.FormState
import fit.tdc.edu.vn.cafemanagement.data.model.user.User
import fit.tdc.edu.vn.cafemanagement.data.model.user.UserViewFormState
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.user.UserDetailViewModel
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.user.UserViewModelFactory
import fit.tdc.edu.vn.cafemanagement.fragment.BaseDetailFragment
import fit.tdc.edu.vn.cafemanagement.fragment.BaseDetailViewModel
import fit.tdc.edu.vn.cafemanagement.util.asDatePicker
import fit.tdc.edu.vn.cafemanagement.util.asEditText
import fit.tdc.edu.vn.cafemanagement.util.setupForEnum
import fit.tdc.edu.vn.cafemanagement.util.setupForLiveList
import kotlinx.android.synthetic.main.fragment_detail_user.*
import java.text.SimpleDateFormat
import java.util.*


class UserDetailFragment : BaseDetailFragment<User>(R.layout.fragment_detail_user) {
    override val args by navArgs<UserDetailFragmentArgs>()
    override val viewModel: BaseDetailViewModel<User>
        get() = ViewModelProvider(
            this,
            UserViewModelFactory(FireBaseDataSource(), this)
        ).get<UserDetailViewModel>()
    override val navController: NavController
        get() = findNavController()
    override val itemId: String?
        get() = args.userId

    override fun showError(state: FormState) {
        val userFormState = state as UserViewFormState
        if (userFormState.usernameError != null) {
            username.error = getString(userFormState.usernameError!!)
        } else {
            username.error = null
            username.isErrorEnabled = false
        }
        if (userFormState.passwordError != null) {
            password.error = getString(userFormState.passwordError!!)
        } else {
            password.error = null
            password.isErrorEnabled = false
        }
        if (userFormState.nameError != null) {
            user_full_name.error = getString(userFormState.nameError!!)
        } else {
            user_full_name.error = null
            user_full_name.isErrorEnabled = false
        }
        if (userFormState.birthError != null) {
            user_birth.error = getString(userFormState.birthError!!)
        } else {
            user_birth.error = null
            user_birth.isErrorEnabled = false
        }
        if (userFormState.identityIdError != null) {
            user_uid.error = getString(userFormState.identityIdError!!)
        } else {
            user_uid.error = null
            user_uid.isErrorEnabled = false
        }
        if (userFormState.phoneError != null) {
            user_phone.error = getString(userFormState.phoneError!!)
        } else {
            user_phone.error = null
            user_phone.isErrorEnabled = false
        }
        if (userFormState.addressError != null) {
            user_address.error = getString(userFormState.addressError!!)
        } else {
            user_address.error = null
            user_address.isErrorEnabled = false
        }
    }

    override fun getCurrentFormData() = User(
        username = username?.editText?.text.toString(),
        password = password?.editText?.text.toString(),
        name = user_full_name?.editText?.text.toString(),
        identityId = user_uid?.editText?.text.toString(),
        phone = user_phone?.editText?.text.toString(),
        address = user_address?.editText?.text.toString(),
        birth = viewModel.saved.birth,
        gender = viewModel.saved.gender,
        role = viewModel.saved.role,
        storeId = viewModel.saved.storeId
    )

    override fun updateUI(type: FormState.Type) {
        when (type) {
            FormState.Type.MODIFY, FormState.Type.ADD -> {
                enableForm(true)
            }
            FormState.Type.VIEW -> {
                enableForm(false)
            }
        }

        if (type == FormState.Type.MODIFY) {
            username.editText?.isEnabled = false
        }

        setupForm()
    }

    private fun enableForm(isEnabled: Boolean) {
        username.editText?.isEnabled = isEnabled
        password.editText?.isEnabled = isEnabled
        user_full_name.editText?.isEnabled = isEnabled
        user_birth.editText?.isEnabled = isEnabled
        user_birth.isEndIconVisible = isEnabled
        user_birth.isFocusable = isEnabled
        user_gender.isEnabled = isEnabled
        user_gender_layout.isEndIconVisible = isEnabled
        user_role.isEnabled = isEnabled
        user_role_layout.isEndIconVisible = isEnabled
        user_store.isEnabled = isEnabled
        user_store_layout.isEndIconVisible = isEnabled
        user_uid.editText?.isEnabled = isEnabled
        user_phone.editText?.isEnabled = isEnabled
        user_address.editText?.isEnabled = isEnabled
    }

    override fun setupForm() {
        username.asEditText {
            viewModel.validate(
                getCurrentFormData()
            )
        }

        password.asEditText {
            viewModel.validate(
                getCurrentFormData()
            )
        }

        user_full_name.asEditText {
            viewModel.validate(
                getCurrentFormData()
            )
        }

        user_birth.asDatePicker(requireContext()) {
            viewModel.validate(
                getCurrentFormData().apply {
                    birth = it
                }
            )
        }

        user_gender.setupForEnum(
            context = requireContext(),
            resId = R.layout.dropdown_menu_popup_item,
            dataset = User.Gender.values().map {
                getString(
                    it.nameResId
                )
            },
            dataChanged = {
                viewModel.validate(
                    getCurrentFormData().apply {
                        gender = User.Gender.values()[it]
                    }
                )
            }
        )

        user_role.setupForEnum(
            context = requireContext(),
            resId = R.layout.dropdown_menu_popup_item,
            dataset = User.Role.values()
                .filterNot { role -> role == User.Role.MANAGER }
                .map { getString(it.nameResId) },
            dataChanged = { position ->
                viewModel.validate(
                    getCurrentFormData().apply {
                        role = User.Role.values()
                            .filterNot { role -> role == User.Role.MANAGER }[position]
                    }
                )
            }
        )

        (viewModel as UserDetailViewModel).storeList.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer {
                user_store.setupForLiveList(
                    context = requireContext(),
                    resId = R.layout.dropdown_menu_popup_item,
                    layout = user_store_layout,
                    missingString = R.string.warning_user_missing_store,
                    emptySetString = R.string.warning_user_no_store,
                    dataset = it.map { store -> store.name }
                ) { position ->
                    viewModel.validate(
                        getCurrentFormData().apply {
                            storeId = it[position].id
                            storeName = it[position].name
                        }
                    )
                }
                if (viewModel.viewType.value == FormState.Type.VIEW) {
                    if (user_store.text.toString() == getString(R.string.warning_user_no_store)) {
                        user_store.setText(getString(R.string.warning_user_missing_store), false)
                    }
                    user_store.isEnabled = false
                    user_store_layout.isEndIconVisible = false
                }
            })


        user_uid.asEditText {
            viewModel.validate(
                getCurrentFormData()
            )
        }

        user_phone.asEditText {
            viewModel.validate(
                getCurrentFormData()
            )
        }

        user_address.asEditText {
            viewModel.validate(
                getCurrentFormData()
            )
        }
    }

    override fun fillFormWith(item: User) {
        username.editText?.setText(item.username)
        password.editText?.setText(item.password)
        user_full_name.editText?.setText(item.name)
        user_uid.editText?.setText(item.identityId)
        user_phone.editText?.setText(item.phone)
        user_address.editText?.setText(item.address)
        val fm = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        item.birth?.toDate()?.let {
            user_birth.editText?.setText(fm.format(it))
        }
        user_gender.setText(getString(item.gender.nameResId), false)
        user_role.setText(getString(item.role?.nameResId ?: User.Role.WAITER.nameResId), false)
        when {
            item.storeId != null -> user_store.setText(item.storeName, false)
            else -> user_store.setText(getString(R.string.warning_user_missing_store), false)
        }
    }
}
