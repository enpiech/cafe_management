package fit.tdc.edu.vn.cafemanagement.data.viewmodel.unit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fit.tdc.edu.vn.cafemanagement.data.repository.impl.UnitRepository

class UnitViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UnitListViewModel::class.java)) {
            return UnitListViewModel(
                unitRepository = UnitRepository()
            ) as T
        } else if (modelClass.isAssignableFrom(UnitDetailViewModel::class.java)) {
            return UnitDetailViewModel(
                itemRepository = UnitRepository()
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}
