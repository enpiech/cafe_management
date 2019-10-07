package fit.tdc.edu.vn.cafemanagement.data.viewmodel.zone_type_viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fit.tdc.edu.vn.cafemanagement.data.data_source.FireBaseDataSource
import fit.tdc.edu.vn.cafemanagement.data.repository.impl.UnitRepository
import fit.tdc.edu.vn.cafemanagement.data.repository.impl.ZoneRepository
import fit.tdc.edu.vn.cafemanagement.data.repository.impl.ZoneTypeRepository

class ZoneTypeViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ZoneTypeViewModel::class.java)) {
            return ZoneTypeViewModel(
                zoneTypeRepository = ZoneTypeRepository(
                    dataSource = FireBaseDataSource()
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}
