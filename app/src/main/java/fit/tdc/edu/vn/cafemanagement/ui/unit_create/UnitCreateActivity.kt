package fit.tdc.edu.vn.cafemanagement.ui.unit_create

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import fit.tdc.edu.vn.cafemanagement.R
import kotlinx.android.synthetic.main.activity_unit_create.*

class UnitCreateActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ID = "fit.tdc.edu.vn.cafemanagement.EXTRA_ID"
        const val EXTRA_NAME = "fit.tdc.edu.vn.cafemanagement.EXTRA_NAME"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_unit_create)

        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close)

        btn_modifyUnit.setOnClickListener {
            edit_unit.isEnabled = true
            btn_modifyUnit.setText("Cập nhật")

        }

        if (intent.hasExtra(EXTRA_ID)) {
            title = "Chỉnh sửa đơn vị"
            edit_unit.setText(intent.getStringExtra(EXTRA_NAME))
            edit_unit.isEnabled = false
        } else {
            title = "Tạo đơn vị"
            edit_unit.isEnabled = true
            btn_modifyUnit.visibility = View.GONE
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_unit, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.save_unit -> {
                saveUnit()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun saveUnit() {
        if (edit_unit.text.toString().trim().isBlank()) {
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
