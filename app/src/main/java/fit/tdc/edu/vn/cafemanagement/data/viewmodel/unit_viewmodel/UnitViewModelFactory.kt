package fit.tdc.edu.vn.cafemanagement.data.viewmodel.unit_viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fit.tdc.edu.vn.cafemanagement.data.repository.impl.UnitRepository

class UnitViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UnitViewModel::class.java)) {
            return UnitViewModel(
                unitRepository = UnitRepository()
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}
