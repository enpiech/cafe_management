package fit.tdc.edu.vn.cafemanagement.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fit.tdc.edu.vn.cafemanagement.data.extension.CollectionLiveData
import fit.tdc.edu.vn.cafemanagement.data.extension.TaskLiveData
import fit.tdc.edu.vn.cafemanagement.data.model.FirestoreModel

abstract class BaseListViewModel<T: FirestoreModel> : ViewModel() {
    private var _itemList = MutableLiveData<List<T>>()
    val itemList: LiveData<List<T>> = _itemList

    abstract fun getAllItems(): CollectionLiveData<T>
    abstract fun delete(item: T): TaskLiveData<Void>
}