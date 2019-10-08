package fit.tdc.edu.vn.cafemanagement.ui.category_view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import fit.tdc.edu.vn.cafemanagement.R
import kotlinx.android.synthetic.main.activity_category_view.*

class CategoryViewActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ID = "fit.tdc.edu.vn.cafemanagement.EXTRA_ID"
        const val EXTRA_NAME = "fit.tdc.edu.vn.cafemanagement.EXTRA_NAME"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_view)

        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close)

        btn_modifyCategory.setOnClickListener {
            edit_category.isEnabled = true
            btn_modifyCategory.setText("Cập nhật")

        }

        if(intent.hasExtra(EXTRA_ID)) {
            title = "Chỉnh sửa danh mục"
            edit_category.setText(intent.getStringExtra(EXTRA_NAME))
            edit_category.isEnabled = false
        } else if (intent.hasExtra(EXTRA_NAME)) {
            title = "Tạo danh mục"
            edit_category.isEnabled = true
            btn_modifyCategory.visibility = View.VISIBLE
        }
    }
}
