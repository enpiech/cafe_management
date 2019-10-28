package fit.tdc.edu.vn.cafemanagement.fragment.user

import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
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
        if (userFormState.nameError != null) {
            edtName.error = getString(userFormState.nameError)
        }
    }

    override fun getCurrentFormData() = User(
        username = edtId?.editText?.text.toString(),
        password = edtPassword?.editText?.text.toString(),
        name = edtName?.editText?.text.toString(),
        /*TODO Birth edt to timestamp*/
        gender = User.Gender.valueOf(gender.text.toString().toUpperCase(Locale.getDefault())),
        role = User.Role.valueOf(role.text.toString().toUpperCase(Locale.getDefault())),
        identityId = edtIdentityId?.editText?.text.toString(),
        phone = edtPhoneNumber?.editText?.text.toString(),
        address = edtAddress?.editText?.text.toString()
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
            edtId.editText?.isEnabled = false
        }

        setupForm()
    }

    private fun enableForm(isEnabled: Boolean) {
        edtId.editText?.isEnabled = isEnabled
        edtPassword.editText?.isEnabled = isEnabled
        edtName.editText?.isEnabled = isEnabled
        edtBirth.editText?.isEnabled = isEnabled
        edtBirth.isEndIconVisible = isEnabled
        edtBirth.isFocusable = isEnabled
        gender.isEnabled = isEnabled
        tilGender.isEndIconVisible = isEnabled
        role.isEnabled = isEnabled
        tilGender.isEndIconVisible = isEnabled
        edtIdentityId.editText?.isEnabled = isEnabled
        edtPhoneNumber.editText?.isEnabled = isEnabled
        edtAddress.editText?.isEnabled = isEnabled
    }

    override fun setupForm() {
        edtId.asEditText {
            viewModel.validate(
                User(
                    username = it
                )
            )
        }

        edtPassword.asEditText {
            viewModel.validate(
                User(
                    password = it
                )
            )
        }

        edtName.asEditText {
            viewModel.validate(
                User(
                    name = it
                )
            )
        }

        edtBirth.asDatePicker(requireContext()) {
            viewModel.validate(
                User(
                    birth = it
                )
            )
        }

        gender.setAdapter(ArrayAdapter(
            requireContext(),
            R.layout.dropdown_menu_popup_item,
            User.Gender.values()
        ))

        role.setAdapter(ArrayAdapter(
            requireContext(),
            R.layout.dropdown_menu_popup_item,
            User.Role.values().filterNot { role -> role == User.Role.MANAGER }
        ))

        edtIdentityId.asEditText {
            viewModel.validate(
                User(
                    identityId = it
                )
            )
        }

        edtPhoneNumber.asEditText {
            viewModel.validate(
                User(
                    phone = it
                )
            )
        }

        edtAddress.asEditText {
            viewModel.validate(
                User(
                    address = it
                )
            )
        }
    }

    override fun fillFormWith(item: User) {
        edtId?.editText?.setText(item.username)
        edtPassword?.editText?.setText(item.password)
        edtName?.editText?.setText(item.name)
        edtIdentityId?.editText?.setText(item.identityId)
        edtPhoneNumber?.editText?.setText(item.phone)
        edtAddress?.editText?.setText(item.address)

        gender.setText(item.gender.nameResId)
        role.setText(item.role?.nameResId ?: User.Role.WAITER.nameResId)
    }
}
