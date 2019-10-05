package fit.tdc.edu.vn.cafemanagement.data.viewmodel.material_viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.firestore.FirebaseFirestore
import fit.tdc.edu.vn.cafemanagement.data.data_source.FireBaseDataSource
import fit.tdc.edu.vn.cafemanagement.data.repository.MaterialRepository
import fit.tdc.edu.vn.cafemanagement.data.repository.UnitRepository
import fit.tdc.edu.vn.cafemanagement.data.repository.ZoneRepository
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.unit_viewmodel.UnitViewModel
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.zone_viewmodel.ZoneViewModel
import fit.tdc.edu.vn.cafemanagement.ui.login.LoginViewModel

class MaterialViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MaterialViewModel::class.java)) {
            return MaterialViewModel(
                 materialRepository = MaterialRepository(
                    dataSource = FireBaseDataSource(
                        "EfzspceETNgWk56YDOOt",
                        FirebaseFirestore.getInstance()
                    )
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}