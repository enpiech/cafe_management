package fit.tdc.edu.vn.cafemanagement.fragment.user

import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.firebase.Timestamp
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.model.FormState
import fit.tdc.edu.vn.cafemanagement.data.model.user.User
import fit.tdc.edu.vn.cafemanagement.data.model.user.UserViewFormState
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.user_viewmodel.UserDetailViewModel
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.user_viewmodel.UserViewModelFactory
import fit.tdc.edu.vn.cafemanagement.fragment.BaseDetailViewModel
import fit.tdc.edu.vn.cafemanagement.fragment.BaseViewFragmentTest
import fit.tdc.edu.vn.cafemanagement.util.asDatePicker
import fit.tdc.edu.vn.cafemanagement.util.asEditText
import kotlinx.android.synthetic.main.fragment_user_view.*
import java.text.SimpleDateFormat
import java.util.*


class UserViewFragment : BaseViewFragmentTest<User>(R.layout.fragment_user_view) {
    override val args by navArgs<UserViewFragmentArgs>()
    override val viewModel: BaseDetailViewModel<User>
        get() = ViewModelProvider(this, UserViewModelFactory()).get<UserDetailViewModel>()
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
            edtPassword.error = getString(userFormState.passwordError!!)
        } else {
            edtPassword.error = null
            edtPassword.isErrorEnabled = false
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
        password = edtPassword?.editText?.text.toString(),
        name = edtName?.editText?.text.toString(),
        identityId = edtIdentityId?.editText?.text.toString(),
        phone = edtPhoneNumber?.editText?.text.toString(),
        address = edtAddress?.editText?.text.toString()
    ).also {
        val date = SimpleDateFormat(
            "dd-MM-yyyy",
            Locale.getDefault()
        ).parse(edtBirth?.editText?.text.toString())
        it.birth = if (date != null) {
            Timestamp(date)
        } else {
            null
        }
        it.gender =
            User.Gender.values().first { res -> getString(res.nameResId) == gender.text.toString() }
        it.role =
            User.Role.values().first { res -> getString(res.nameResId) == role.text.toString() }
        it.storeId = (viewModel as UserDetailViewModel).currentStore.value?.id
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

        if (type == FormState.Type.MODIFY) {
            edtUsername.editText?.isEnabled = false
        }

        setupForm()
    }

    private fun enableForm(isEnabled: Boolean) {
        edtUsername.editText?.isEnabled = isEnabled
        edtPassword.editText?.isEnabled = isEnabled
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

        edtPassword.asEditText {
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

        gender.setAdapter(
            ArrayAdapter(
                requireContext(),
                R.layout.dropdown_menu_popup_item,
                User.Gender.values().map { getString(it.nameResId) }
            )
        )
        gender.setOnItemClickListener { parent, view, position, id ->
            viewModel.validate(
                getCurrentFormData().apply {
                    gender = User.Gender.values()[position]
                }
            )
        }

        role.setAdapter(ArrayAdapter(
            requireContext(),
            R.layout.dropdown_menu_popup_item,
            User.Role.values().filterNot { role -> role == User.Role.MANAGER }.map {
                getString(
                    it.nameResId
                )
            }
        ))
        role.setOnItemClickListener { parent, view, position, id ->
            viewModel.validate(
                getCurrentFormData().apply {
                    role = User.Role.values()[position]
                }
            )
        }

        (viewModel as UserDetailViewModel).storeList.observe(
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
                }
            })
        store.setOnItemClickListener { parent, view, position, id ->
            viewModel.validate(
                getCurrentFormData().apply {
                    storeId = (viewModel as UserDetailViewModel).storeList.value?.get(position)?.id
                }
            )
            (viewModel as UserDetailViewModel).currentStore.value =
                (viewModel as UserDetailViewModel).storeList.value?.get(position)
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
        edtPassword.editText?.setText(item.password)
        edtName.editText?.setText(item.name)
        edtIdentityId.editText?.setText(item.identityId)
        edtPhoneNumber.editText?.setText(item.phone)
        edtAddress.editText?.setText(item.address)
        val fm = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        item.birth?.toDate()?.let {
            edtBirth.editText?.setText(fm.format(it))
        }
        gender.setText(item.gender.nameResId)
        role.setText(item.role?.nameResId ?: User.Role.WAITER.nameResId)
        (viewModel as UserDetailViewModel).currentStore.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer { cstore ->
                cstore?.name?.let { name ->
                    store.setText(name)
                    (viewModel as UserDetailViewModel).currentStore.removeObservers(
                        viewLifecycleOwner
                    )
                }
            })
    }
}
