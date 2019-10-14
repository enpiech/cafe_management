package fit.tdc.edu.vn.cafemanagement.ui.category_view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.Category
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.category_viewmodel.CategoryViewModelFactory
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.category_viewmodel.CategoryViewViewModel
import kotlinx.android.synthetic.main.activity_category_view.*

class CategoryViewActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ID = "fit.tdc.edu.vn.cafemanagement.EXTRA_ID"
        const val EXTRA_NAME = "fit.tdc.edu.vn.cafemanagement.EXTRA_NAME"
        const val CREATE = R.string.btnTao
        const val EDIT = R.string.btnChinhSua
        const val UPDATE = R.string.btnUpdate
    }

    private lateinit var viewModel: CategoryViewViewModel

    enum class ButtonState {
        ADD,
        MODIFY,
        UPDATE
    }

    private var buttonState = ButtonState.ADD

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_view)

        viewModel = ViewModelProviders.of(this, CategoryViewModelFactory())
            .get(CategoryViewViewModel::class.java)

        if (intent.hasExtra(EXTRA_ID)) {
            title = "Chỉnh sửa danh mục"
            viewModel.getCategory(intent.getStringExtra(EXTRA_ID))
            viewModel.category.observe(this, Observer {
                if (it == null) return@Observer
                edit_category.setText(it.name)
                edit_category.isEnabled = false
                btn_modifyCategory.setText(EDIT)
                buttonState = ButtonState.MODIFY
            })
        } else {
            title = "Tạo danh mục"
            edit_category.isEnabled = true
            btn_modifyCategory.setText(CREATE)
            buttonState = ButtonState.ADD
        }

        btn_modifyCategory.setOnClickListener {
            when (buttonState) {
                ButtonState.MODIFY -> {
                    buttonState = ButtonState.UPDATE
                    btn_modifyCategory.setText(UPDATE)
                    modifyCategory()
                }
                ButtonState.ADD -> saveCategory()
                ButtonState.UPDATE -> updateCategory()
            }
        }
    }

    private fun updateCategory() {
        viewModel.update(Category(name = edit_category.text.toString()).apply {
            id = viewModel.category.value!!.id
            finish()
        })
    }

    private fun modifyCategory() {
        edit_category.isEnabled = true
    }

    private fun saveCategory() {
        if (edit_category.text.toString().trim().isBlank()) {
            Toast.makeText(this, "Can not insert empty category!", Toast.LENGTH_SHORT).show()
            return
        }
        viewModel.insert(Category(name = edit_category.text.toString()))
        finish()
    }
}
