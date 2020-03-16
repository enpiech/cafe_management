package fit.tdc.edu.vn.cafemanagement.fragment

import android.util.Log
import androidx.lifecycle.*
import com.google.firebase.firestore.DocumentReference
import fit.tdc.edu.vn.cafemanagement.data.extension.DocumentLiveData
import fit.tdc.edu.vn.cafemanagement.data.extension.Status
import fit.tdc.edu.vn.cafemanagement.data.extension.TaskLiveData
import fit.tdc.edu.vn.cafemanagement.data.model.FirestoreModel
import fit.tdc.edu.vn.cafemanagement.data.model.FormState

abstract class BaseDetailViewModel<T: FirestoreModel> : ViewModel() {
    private var _viewType = MutableLiveData<FormState.Type?>(null)
    val viewType: LiveData<FormState.Type?> = _viewType

    protected var _formState = MutableLiveData<FormState>(null)
    val formState: LiveData<FormState> = _formState

    private var _currentItemId = MutableLiveData<String>()
    val currentItem = _currentItemId.switchMap { id ->
        getItem(id).map {result ->
            if (result.status == Status.SUCCESS) {
                result.data
            } else {
                null
            }
        }
    }

    abstract var saved: T

    fun setViewType(type: FormState.Type) {
        _viewType.value = type
    }

    fun getCurrentItem(id: String) {
        _currentItemId.value = id
    }

    abstract fun getItem(id: String): DocumentLiveData<T>
    abstract fun insert(item: T): TaskLiveData<DocumentReference>
    abstract fun update(item: T): TaskLiveData<Void>
    abstract fun validate(item: T?)
}