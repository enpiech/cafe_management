package fit.tdc.edu.vn.cafemanagement.ui.unit_create

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_unit_create.*
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.Unit
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.unit_viewmodel.UnitCreateViewModel
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.unit_viewmodel.UnitViewModelFactory

class UnitViewActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ID = "fit.tdc.edu.vn.cafemanagement.EXTRA_ID"
        const val EXTRA_NAME = "fit.tdc.edu.vn.cafemanagement.EXTRA_NAME"
        const val CHINH_SUA = R.string.btnChinhSua
        const val TAO = R.string.btnTao
        const val CAPNHAT = R.string.btnUpdate

    }

    private lateinit var unitCreateViewModel: UnitCreateViewModel
    enum class ButtonState {
        ADD,
        MODIFY,
        UPDATE
    }
    private var buttonState = ButtonState.ADD
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_unit_create)

        unitCreateViewModel = ViewModelProviders.of(this, UnitViewModelFactory()).get(
            UnitCreateViewModel::class.java)


        if (intent.hasExtra(EXTRA_ID)) {
            title = "Chỉnh sửa đơn vị"
            unitCreateViewModel.getUnit(intent.getStringExtra(EXTRA_ID))
            unitCreateViewModel.unit.observe(this, Observer {
                if (it == null) return@Observer
                Log.d("test", it.id + ": " + it.toString())
                edit_unit.setText(it.name)
                edit_unit.isEnabled = false
                btn_modifyUnit.setText(CHINH_SUA)
                buttonState = ButtonState.MODIFY
            })
        } else {
            title = "Tạo đơn vị"
            edit_unit.isEnabled = true
//          btn_modifyUnit.visibility = View.GONE
            btn_modifyUnit.setText(TAO)
            buttonState = ButtonState.ADD
        }

        btn_modifyUnit.setOnClickListener {
            Log.d("test", buttonState.toString())
            when (buttonState) {
                ButtonState.MODIFY -> {
                    buttonState = ButtonState.UPDATE
                    btn_modifyUnit.setText(CAPNHAT)
                    editUnit()
                }
                ButtonState.ADD -> saveUnit()
                ButtonState.UPDATE -> updateUnit()
                else -> finish()
            }
        }
    }

    private fun updateUnit() {
        unitCreateViewModel.update(Unit(name = edit_unit.text.toString()).apply { id = unitCreateViewModel.unit.value!!.id })
        finish()
    }

    private fun editUnit(){
        edit_unit.isEnabled = true

    }

    private fun saveUnit() {
        if (edit_unit.text.toString().trim().isBlank()) {
            Toast.makeText(this, "Can not insert, empty unit!", Toast.LENGTH_SHORT).show()
            return
        }
        unitCreateViewModel.insert(Unit(name = edit_unit.text.toString()))
        finish()
    }
}
