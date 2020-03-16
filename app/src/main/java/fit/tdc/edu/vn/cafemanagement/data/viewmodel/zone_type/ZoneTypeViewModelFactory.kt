package fit.tdc.edu.vn.cafemanagement.data.viewmodel.zone_type

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.zonetype.ZoneTypeRemoteDataSourceImpl
import fit.tdc.edu.vn.cafemanagement.data.repository.zone_type.ZoneTypeRepositoryImpl

class ZoneTypeViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ZoneTypeViewModel::class.java)) {
            return ZoneTypeViewModel(
                zoneTypeRepository = ZoneTypeRepositoryImpl(
                    // LOOSE
                    dataSource = ZoneTypeRemoteDataSourceImpl()
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}
