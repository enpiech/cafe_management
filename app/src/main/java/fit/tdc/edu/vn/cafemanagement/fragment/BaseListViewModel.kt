package fit.tdc.edu.vn.cafemanagement.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import fit.tdc.edu.vn.cafemanagement.data.extension.CollectionLiveData
import fit.tdc.edu.vn.cafemanagement.data.extension.Status
import fit.tdc.edu.vn.cafemanagement.data.extension.TaskLiveData
import fit.tdc.edu.vn.cafemanagement.data.model.FirestoreModel

abstract class BaseListViewModel<T: FirestoreModel> : ViewModel() {
    val itemList: LiveData<List<T>> by lazy {
        getAllItems().map {
            if (it.status == Status.SUCCESS) {
                it.data!!
            } else {
                listOf()
            }
        }
    }

    abstract fun getAllItems(): CollectionLiveData<T>
    abstract fun delete(item: T): TaskLiveData<Void>
}