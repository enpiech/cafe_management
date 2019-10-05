package fit.tdc.edu.vn.cafemanagement.data.viewmodel.table_viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.firestore.FirebaseFirestore
import fit.tdc.edu.vn.cafemanagement.data.data_source.FireBaseDataSource
import fit.tdc.edu.vn.cafemanagement.data.repository.TableRepository
import fit.tdc.edu.vn.cafemanagement.data.repository.UnitRepository
import fit.tdc.edu.vn.cafemanagement.data.repository.ZoneRepository
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.unit_viewmodel.UnitViewModel
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.zone_viewmodel.ZoneViewModel
import fit.tdc.edu.vn.cafemanagement.ui.login.LoginViewModel

class TableViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TableViewModel::class.java)) {
            return TableViewModel(
                 tableRepository = TableRepository(
                    dataSource = FireBaseDataSource()
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}