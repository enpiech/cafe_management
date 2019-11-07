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
import androidx.annotation.StringRes
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.Timestamp
import java.text.SimpleDateFormat
import java.util.*

inline fun TextInputLayout.asEditText(crossinline dataChanged: (String) -> Unit) {
    editText?.let {
        it.setOnFocusChangeListener { _, hasFocus ->
            this.endIconMode = if (hasFocus) TextInputLayout.END_ICON_CLEAR_TEXT else TextInputLayout.END_ICON_NONE
            this.isEndIconVisible = hasFocus
            if (!hasFocus) {
                this.clearFocus()
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
        it.afterTextChanged {
            this.clearFocus()
            dataChanged.invoke(it)
        }
    }
}

inline fun TextInputLayout.asDatePicker(context: Context, crossinline dataChanged: (Timestamp) -> Unit) {
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

inline fun AutoCompleteTextView.setupForEnum(
    context: Context, @LayoutRes resId: Int,
    dataset: List<String>,
    crossinline dataChanged: (position: Int) -> Unit
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

inline fun AutoCompleteTextView.setupForLiveList(
    context: Context,
    @LayoutRes resId: Int,
    layout: TextInputLayout,
    @StringRes emptySetString: Int,
    @StringRes missingString: Int,
    dataset: List<String?>,
    crossinline dataChanged: (position: Int) -> Unit
) {
    if (dataset.isNotEmpty()) {
        setAdapter(
            ArrayAdapter(context, resId, dataset)
        )
        if (text.isNullOrBlank() || text.toString() == context.getString(emptySetString)) {
            setText(context.getString(missingString), false)
            layout.isEndIconVisible = true
            isEnabled = true
        }
    } else {
        if (text.isNullOrBlank() || text.toString() == context.getString(missingString)) {
            setText(context.getString(emptySetString), false)
            layout.isEndIconVisible = false
            isEnabled = false
        }
    }
    setOnItemClickListener { _, _, position, _ ->
        dataChanged.invoke(position)
    }
}

inline fun EditText.afterTextChanged(crossinline afterTextChanged: (String) -> Unit) {
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


inline fun TextInputLayout.afterTextChanged(crossinline afterTextChanged: (String) -> Unit) {
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