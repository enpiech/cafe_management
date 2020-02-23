package fit.tdc.edu.vn.cafemanagement.fragment.category

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.category.CategoryRemoteDatasourceImpl
import fit.tdc.edu.vn.cafemanagement.data.model.FormState
import fit.tdc.edu.vn.cafemanagement.data.model.category.Category
import fit.tdc.edu.vn.cafemanagement.data.model.category.CategoryViewFormState
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.category.CategoryDetailViewModel
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.category.CategoryViewModelFactory
import fit.tdc.edu.vn.cafemanagement.fragment.BaseDetailFragment
import fit.tdc.edu.vn.cafemanagement.fragment.BaseDetailViewModel
import fit.tdc.edu.vn.cafemanagement.util.asEditText
import kotlinx.android.synthetic.main.fragment_detail_category.*

class CategoryDetailFragment : BaseDetailFragment<Category>(R.layout.fragment_detail_category) {
    override val args by navArgs<CategoryDetailFragmentArgs>()
    override val viewModel: BaseDetailViewModel<Category>
        get() = ViewModelProvider(
            this,
            CategoryViewModelFactory(
                CategoryRemoteDatasourceImpl(),
                this
            )
        ).get<CategoryDetailViewModel>()
    override val navController: NavController
        get() = findNavController()
    override val itemId: String?
        get() = args.categoryId

    override fun showError(state: FormState) {
        val categoryFormState = state as CategoryViewFormState
        if (categoryFormState.nameError != null) {
            category_name.error = getString(categoryFormState.nameError)
        } else {
            category_name.error = null
            category_name.isEndIconVisible = false
        }
    }

    override fun getCurrentFormData() = Category(
        name = category_name.editText?.text.toString()
    )

    override fun updateUI(type: FormState.Type) {
        when (type) {
            FormState.Type.MODIFY, FormState.Type.ADD -> {
                category_name.editText?.isEnabled = true
            }
            FormState.Type.VIEW -> {
                category_name.editText?.isEnabled = false
            }
        }
        setupForm()
    }

    override fun fillFormWith(item: Category) {
        category_name.editText?.setText(item.name)
    }

    override fun setupForm() {
        category_name.asEditText {
            viewModel.validate(
                Category(
                    name = it
                )
            )
        }
    }
}
