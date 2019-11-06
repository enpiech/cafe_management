package fit.tdc.edu.vn.cafemanagement.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import fit.tdc.edu.vn.cafemanagement.data.extension.FirestoreResource
import fit.tdc.edu.vn.cafemanagement.data.extension.TaskLiveData
import fit.tdc.edu.vn.cafemanagement.data.model.FirestoreModel

abstract class BaseListViewModel<T: FirestoreModel> : ViewModel() {
    val itemList by lazy {
        getAllItems()
    }

    abstract fun getAllItems(): LiveData<FirestoreResource<List<T>>>
    abstract fun delete(item: T): TaskLiveData<Void>
}