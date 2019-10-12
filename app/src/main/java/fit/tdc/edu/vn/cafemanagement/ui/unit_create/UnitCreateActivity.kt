package fit.tdc.edu.vn.cafemanagement.ui.unit_create

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_unit_create.*
import fit.tdc.edu.vn.cafemanagement.R

class UnitCreateActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ID = "fit.tdc.edu.vn.cafemanagement.EXTRA_ID"
        const val EXTRA_NAME = "fit.tdc.edu.vn.cafemanagement.EXTRA_NAME"
        const val CHINH_SUA = "Chỉnh sửa"
        const val TAO = "Tạo"
        const val CAPNHAT = "Cập nhật"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_unit_create)

        if (intent.hasExtra(EXTRA_ID)) {
            title = "Chỉnh sửa đơn vị"
            edit_unit.setText(intent.getStringExtra(EXTRA_NAME))
            edit_unit.isEnabled = false
            btn_modifyUnit.setText(CHINH_SUA)
        } else {
            title = "Tạo đơn vị"
            edit_unit.isEnabled = true
//            btn_modifyUnit.visibility = View.GONE
            btn_modifyUnit.setText(TAO)
        }

        btn_modifyUnit.setOnClickListener {
            when {
                btn_modifyUnit.text == CHINH_SUA -> {
                    btn_modifyUnit.setText(CAPNHAT)
                    editUnit()
                }
                btn_modifyUnit.text == TAO -> saveUnit()
                btn_modifyUnit.text == CAPNHAT -> saveUnit()
            }
        }
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close)
    }

    private fun editUnit(){
        edit_unit.isEnabled = true

    }

    private fun saveUnit() {
        if (edit_unit.text.toString().trim().isBlank()) {
            // TODO create AleartDialog & remove Toast pls
            Toast.makeText(this, "Can not insert empty unit!", Toast.LENGTH_SHORT).show()
            return
        }

        val data = Intent().apply {
            putExtra(EXTRA_NAME, edit_unit.text.toString())
            if (!intent.getStringExtra(EXTRA_ID).isNullOrEmpty()) {
                putExtra(EXTRA_ID, intent.getStringExtra(EXTRA_ID))
            }
        }

        setResult(Activity.RESULT_OK, data)
        finish()
    }
}
