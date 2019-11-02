package fit.tdc.edu.vn.cafemanagement.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.google.firebase.firestore.DocumentReference
import fit.tdc.edu.vn.cafemanagement.data.extension.CollectionLiveData
import fit.tdc.edu.vn.cafemanagement.data.extension.DocumentLiveData
import fit.tdc.edu.vn.cafemanagement.data.extension.TaskLiveData
import fit.tdc.edu.vn.cafemanagement.data.model.material.Material
import fit.tdc.edu.vn.cafemanagement.data.model.unit.Unit

/**
 * UNIT REPOSITORY API
 *
 * @since 1.0
 * @author anh.lt
 */

interface UnitRepositoryAPI {
    fun getAllUnits(): CollectionLiveData<Unit>
    fun getUnit(id: String): DocumentLiveData<Unit>
    fun insert(unit: Unit): TaskLiveData<DocumentReference>
    fun update(unit: Unit): TaskLiveData<Void>
    fun delete(unit: Unit): TaskLiveData<Void>
    fun materialsWithUnit(id: String) : LiveData<List<Material>?>
    fun getUnitsASC(): MediatorLiveData<List<Unit>?>
    fun getUnitsDESC(): MediatorLiveData<List<Unit>?>
}