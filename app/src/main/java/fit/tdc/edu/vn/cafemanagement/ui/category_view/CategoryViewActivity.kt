package fit.tdc.edu.vn.cafemanagement.ui.category_view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import fit.tdc.edu.vn.cafemanagement.R
import kotlinx.android.synthetic.main.activity_category_view.*

class CategoryViewActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ID = "fit.tdc.edu.vn.cafemanagement.EXTRA_ID"
        const val EXTRA_NAME = "fit.tdc.edu.vn.cafemanagement.EXTRA_NAME"
        const val CREATE = "Tạo"
        const val EDIT = "Chỉnh sửa"
        const val UPDATE = "Cập nhật"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_view)

        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close)

        btn_modifyCategory.setOnClickListener {
            when {
                btn_modifyCategory.text == EDIT -> {
                    btn_modifyCategory.setText(UPDATE)
                    modifyCategory()
                }
                btn_modifyCategory.text == CREATE -> saveCategory()
                btn_modifyCategory.text == UPDATE -> saveCategory()
            }
        }

        if (intent.hasExtra(EXTRA_ID)) {
            title = "Chỉnh sửa danh mục"
            edit_category.setText(intent.getStringExtra(EXTRA_NAME))
            edit_category.isEnabled = false
            btn_modifyCategory.setText(EDIT)
        } else if (intent.hasExtra(EXTRA_NAME)) {
            title = "Tạo danh mục"
            edit_category.isEnabled = true
            btn_modifyCategory.setText(CREATE)
        }
    }

    private fun modifyCategory() {
        edit_category.isEnabled = true
    }

    private fun saveCategory() {
        if (edit_category.text.toString().trim().isBlank()) {
            // TODO create AleartDialog
            Toast.makeText(this, "Can not insert empty category!", Toast.LENGTH_SHORT).show()
            return
        }

        val data = Intent().apply {
            putExtra(EXTRA_NAME, edit_category.text.toString())
            if (!intent.getStringExtra(EXTRA_ID).isNullOrEmpty()) {
                putExtra(EXTRA_ID, intent.getStringExtra(EXTRA_ID))
            }
        }

        setResult(Activity.RESULT_OK, data)
        finish()
    }
}
