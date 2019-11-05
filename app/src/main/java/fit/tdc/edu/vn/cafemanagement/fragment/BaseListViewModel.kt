package fit.tdc.edu.vn.cafemanagement.fragment

import androidx.lifecycle.ViewModel
import fit.tdc.edu.vn.cafemanagement.data.extension.CollectionLiveData
import fit.tdc.edu.vn.cafemanagement.data.extension.TaskLiveData
import fit.tdc.edu.vn.cafemanagement.data.model.FirestoreModel

abstract class BaseListViewModel<T: FirestoreModel> : ViewModel() {
    val itemList by lazy {
        getAllItems()
    }

    abstract fun getAllItems(): CollectionLiveData<T>
    abstract fun delete(item: T): TaskLiveData<Void>
}