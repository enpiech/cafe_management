package fit.tdc.edu.vn.cafemanagement.data.viewmodel.zone_type_viewmodel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.firestore.FirebaseFirestore
import fit.tdc.edu.vn.cafemanagement.data.data_source.FireBaseDataSource
import fit.tdc.edu.vn.cafemanagement.data.repository.UnitRepository

import fit.tdc.edu.vn.cafemanagement.ui.login.LoginViewModel;
import kotlin.Suppress;

public class UnitViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UnitViewModel::class.java)) {
            return UnitViewModel(
                 UnitRepository(
                    dataSource = FireBaseDataSource()
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}
