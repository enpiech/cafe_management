package fit.tdc.edu.vn.cafemanagement.fragment.user

import android.util.Log
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.FireBaseDataSource
import fit.tdc.edu.vn.cafemanagement.data.extension.observeUntil
import fit.tdc.edu.vn.cafemanagement.data.model.FormState
import fit.tdc.edu.vn.cafemanagement.data.model.user.User
import fit.tdc.edu.vn.cafemanagement.data.model.user.UserViewFormState
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.user.UserDetailViewModel
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.user.UserViewModelFactory
import fit.tdc.edu.vn.cafemanagement.fragment.BaseDetailViewModel
import fit.tdc.edu.vn.cafemanagement.fragment.BaseViewFragmentTest
import fit.tdc.edu.vn.cafemanagement.util.asDatePicker
import fit.tdc.edu.vn.cafemanagement.util.asEditText
import fit.tdc.edu.vn.cafemanagement.util.init
import kotlinx.android.synthetic.main.fragment_user_detail.*
import java.text.SimpleDateFormat
import java.util.*


class UserViewFragment : BaseViewFragmentTest<User>(R.layout.fragment_user_detail) {
    override val args by navArgs<UserViewFragmentArgs>()
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
            edtUsername.error = getString(userFormState.usernameError!!)
        } else {
            edtUsername.error = null
            edtUsername.isErrorEnabled = false
        }
        if (userFormState.passwordError != null) {
            password.error = getString(userFormState.passwordError!!)
        } else {
            password.error = null
            password.isErrorEnabled = false
        }
        if (userFormState.nameError != null) {
            edtName.error = getString(userFormState.nameError!!)
        } else {
            edtName.error = null
            edtName.isErrorEnabled = false
        }
        if (userFormState.birthError != null) {
            edtBirth.error = getString(userFormState.birthError!!)
        } else {
            edtBirth.error = null
            edtBirth.isErrorEnabled = false
        }
        if (userFormState.identityIdError != null) {
            edtIdentityId.error = getString(userFormState.identityIdError!!)
        } else {
            edtIdentityId.error = null
            edtIdentityId.isErrorEnabled = false
        }
        if (userFormState.phoneError != null) {
            edtPhoneNumber.error = getString(userFormState.phoneError!!)
        } else {
            edtPhoneNumber.error = null
            edtPhoneNumber.isErrorEnabled = false
        }
        if (userFormState.addressError != null) {
            edtAddress.error = getString(userFormState.addressError!!)
        } else {
            edtAddress.error = null
            edtAddress.isErrorEnabled = false
        }
    }

    override fun getCurrentFormData() = User(
        username = edtUsername?.editText?.text.toString(),
        password = password?.editText?.text.toString(),
        name = edtName?.editText?.text.toString(),
        identityId = edtIdentityId?.editText?.text.toString(),
        phone = edtPhoneNumber?.editText?.text.toString(),
        address = edtAddress?.editText?.text.toString(),
        birth = viewModel.saved.birth,
        gender = viewModel.saved.gender,
        role = viewModel.saved.role,
        storeId = viewModel.saved.storeId
    )
//        .also {
//        if (!edtBirth?.editText?.text.toString().isBlank()) {
//            val date = SimpleDateFormat(
//                "dd-MM-yyyy",
//                Locale.getDefault()
//            ).parse(edtBirth?.editText?.text.toString())
//            it.birth = if (date != null) {
//                Timestamp(date)
//            } else {
//                null
//            }
//        } else {
//            it.birth = null
//        }
//        if (!gender.text.isNullOrBlank()) {
//            it.gender =
//                User.Gender.values()
//                    .first { res -> getString(res.nameResId) == gender.text.toString() }
//        }
//        if (!role.text.isNullOrBlank()) {
//            it.role =
//                User.Role.values().first { res -> getString(res.nameResId) == role.text.toString() }
//        }
//        if (!store.text.isNullOrBlank() && store.text.toString() != getString(R.string.warning_user_no_store)) {
//            it.storeId = (viewModel as UserDetailViewModel).currentStore.value?.id
//        }
//    }

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
            edtUsername.editText?.isEnabled = false
        }

        setupForm()
    }

    private fun enableForm(isEnabled: Boolean) {
        edtUsername.editText?.isEnabled = isEnabled
        password.editText?.isEnabled = isEnabled
        edtName.editText?.isEnabled = isEnabled
        edtBirth.editText?.isEnabled = isEnabled
        edtBirth.isEndIconVisible = isEnabled
        edtBirth.isFocusable = isEnabled
        gender.isEnabled = isEnabled
        tilGender.isEndIconVisible = isEnabled
        role.isEnabled = isEnabled
        tilRole.isEndIconVisible = isEnabled
        store.isEnabled = isEnabled
        tilStore.isEndIconVisible = isEnabled
        edtIdentityId.editText?.isEnabled = isEnabled
        edtPhoneNumber.editText?.isEnabled = isEnabled
        edtAddress.editText?.isEnabled = isEnabled
    }

    override fun setupForm() {
        edtUsername.asEditText {
            viewModel.validate(
                getCurrentFormData()
            )
        }

        password.asEditText {
            viewModel.validate(
                getCurrentFormData()
            )
        }

        edtName.asEditText {
            viewModel.validate(
                getCurrentFormData()
            )
        }

        edtBirth.asDatePicker(requireContext()) {
            viewModel.validate(
                getCurrentFormData().apply {
                    birth = it
                }
            )
        }

        gender.init(
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

        role.init(
            context = requireContext(),
            resId = R.layout.dropdown_menu_popup_item,
            dataset = User.Role.values()
                .filterNot { role -> role == User.Role.MANAGER }
                .map { getString(it.nameResId) },
            dataChanged = {
                viewModel.validate(
                    getCurrentFormData().apply {
                        role = User.Role.values()
                            .filterNot { role -> role == User.Role.MANAGER }[it]
                    }
                )
            }
        )

        (viewModel as UserDetailViewModel).storeList.observeUntil(
            viewLifecycleOwner,
            androidx.lifecycle.Observer {
                if (it.isNotEmpty()) {
                    val list = it.map { store -> store.name }
                    store.setAdapter(
                        ArrayAdapter(
                            requireContext(),
                            R.layout.dropdown_menu_popup_item,
                            list
                        )
                    )
                } else {
                    store.setText(getString(R.string.warning_user_no_store), false)
                }
                store.setOnItemClickListener { _, _, position, _ ->
                    viewModel.validate(
                        getCurrentFormData().apply {
                            storeId = it[position].id
                        }
                    )
                    (viewModel as UserDetailViewModel).setCurrentStoreSelection(it[position].name)
                }
            }) {
            it != null
        }


        edtIdentityId.asEditText {
            viewModel.validate(
                getCurrentFormData()
            )
        }

        edtPhoneNumber.asEditText {
            viewModel.validate(
                getCurrentFormData()
            )
        }

        edtAddress.asEditText {
            viewModel.validate(
                getCurrentFormData()
            )
        }
    }

    override fun fillFormWith(item: User) {
        edtUsername.editText?.setText(item.username)
        password.editText?.setText(item.password)
        edtName.editText?.setText(item.name)
        edtIdentityId.editText?.setText(item.identityId)
        edtPhoneNumber.editText?.setText(item.phone)
        edtAddress.editText?.setText(item.address)
        val fm = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        item.birth?.toDate()?.let {
            edtBirth.editText?.setText(fm.format(it))
        }
        gender.setText(getString(item.gender.nameResId), false)
        role.setText(getString(item.role?.nameResId ?: User.Role.WAITER.nameResId), false)
        Log.d("test1", item.storeId ?: "null")
        (viewModel as UserDetailViewModel).currentStoreSelection.observeUntil(
            viewLifecycleOwner,
            androidx.lifecycle.Observer {
                store.setText(it, false)
            }) {
            it != null
        }
    }
}
