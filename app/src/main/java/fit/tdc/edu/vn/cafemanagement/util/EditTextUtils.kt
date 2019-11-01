package fit.tdc.edu.vn.cafemanagement.util

import android.app.DatePickerDialog
import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.EditorInfo
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.EditText
import androidx.annotation.LayoutRes
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.Timestamp
import java.text.SimpleDateFormat
import java.util.*

fun TextInputLayout.asEditText(dataChanged: (String) -> Unit) {
    editText?.let {
        it.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                this.clearFocus()
            } else {
                dataChanged.invoke(it.text.toString())
            }
        }
    }
    editText?.let {
        it.setOnEditorActionListener { _, actionId, _ ->
            when (actionId) {
                EditorInfo.IME_ACTION_DONE -> {
                    this.clearFocus()
                    it.clearFocus()
                    dataChanged.invoke(it.text.toString())
                }
            }
            false
        }
    }
}

fun TextInputLayout.asDatePicker(context: Context, dataChanged: (Timestamp) -> Unit) {
    setEndIconOnClickListener {
        val now: Calendar = Calendar.getInstance()
        DatePickerDialog(
            context,
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                now.set(year, monthOfYear, dayOfMonth)
                val fm = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
                editText?.setText(fm.format(now.time))
                dataChanged.invoke(Timestamp(now.time))
                clearFocus()
            },
            now.get(Calendar.YEAR),  // Initial year selection
            now.get(Calendar.MONTH),  // Initial month selection
            now.get(Calendar.DAY_OF_MONTH) // Inital day selection
        ).show()
    }
    editText?.setOnClickListener {
        val now: Calendar = Calendar.getInstance()
        DatePickerDialog(
            context,
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                now.set(year, monthOfYear, dayOfMonth)
                val fm = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
                editText?.setText(fm.format(now.time))
                dataChanged.invoke(Timestamp(now.time))
                clearFocus()
            },
            now.get(Calendar.YEAR),  // Initial year selection
            now.get(Calendar.MONTH),  // Initial month selection
            now.get(Calendar.DAY_OF_MONTH) // Inital day selection
        ).show()
    }
}

fun AutoCompleteTextView.init(
    context: Context, @LayoutRes resId: Int,
    dataset: List<String>,
    dataChanged: (position: Int) -> Unit
) {
    setAdapter(
        ArrayAdapter(
            context,
            resId,
            dataset
        )
    )
    setOnItemClickListener { _, _, position, _ ->
        dataChanged.invoke(position)
    }
}

fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(
            s: CharSequence,
            start: Int,
            count: Int,
            after: Int
        ) {
        }

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}


fun TextInputLayout.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.editText?.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(
            s: CharSequence,
            start: Int,
            count: Int,
            after: Int
        ) {
        }

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}