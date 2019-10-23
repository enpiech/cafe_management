package fit.tdc.edu.vn.cafemanagement.fragment

import androidx.lifecycle.*
import com.google.firebase.firestore.DocumentReference
import com.hadilq.liveevent.LiveEvent
import fit.tdc.edu.vn.cafemanagement.data.extension.CollectionLiveData
import fit.tdc.edu.vn.cafemanagement.data.extension.DocumentLiveData
import fit.tdc.edu.vn.cafemanagement.data.extension.Status
import fit.tdc.edu.vn.cafemanagement.data.extension.TaskLiveData
import fit.tdc.edu.vn.cafemanagement.data.model.FirestoreModel
import fit.tdc.edu.vn.cafemanagement.data.model.FormState

abstract class BaseViewViewModel<T: FirestoreModel> : ViewModel() {
    private var _viewType = MutableLiveData<FormState.Type?>(null)
    val viewType: LiveData<FormState.Type?> = _viewType

    protected var _formState = MutableLiveData<FormState>(null)
    val formState: LiveData<FormState> = _formState

    private var _currentItemId = LiveEvent<String>()
    val currentItem = MediatorLiveData<T>()

    private var _itemList = MutableLiveData<List<T>>()
    val itemList: LiveData<List<T>> = _itemList

    init {
        currentItem.addSource(
            _currentItemId.switchMap { id ->
                getItem(id)
            }
        ) { result ->
            if (result.status == Status.SUCCESS) {
                currentItem.value = result.data
            }
        }
    }

    fun setViewType(type: FormState.Type) {
        _viewType.value = type
    }

    fun getCurrentItem(id: String) {
        _currentItemId.value = id
    }


    abstract fun getAllItems(): CollectionLiveData<T>
    abstract fun getItem(id: String): DocumentLiveData<T>
    abstract fun insert(item: T): TaskLiveData<DocumentReference>
    abstract fun update(item: T): TaskLiveData<Void>
    abstract fun delete(item: T): TaskLiveData<Void>
    abstract fun dataChange(item: T)
}