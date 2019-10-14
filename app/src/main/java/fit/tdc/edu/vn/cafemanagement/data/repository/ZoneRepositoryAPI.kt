package fit.tdc.edu.vn.cafemanagement.data.repository

import androidx.lifecycle.LiveData
import com.google.firebase.firestore.DocumentReference
import fit.tdc.edu.vn.cafemanagement.data.extension.CollectionLiveData
import fit.tdc.edu.vn.cafemanagement.data.extension.DocumentLiveData
import fit.tdc.edu.vn.cafemanagement.data.extension.TaskLiveData
import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.Table
import fit.tdc.edu.vn.cafemanagement.data.model.zone.Zone

interface ZoneRepositoryAPI {
    fun tablesInZone(id: String) : LiveData<List<Table>?>
    fun getAllZones(): CollectionLiveData<Zone>
    fun getZone(id: String): DocumentLiveData<Zone>
    fun insert(zone: Zone): TaskLiveData<DocumentReference>
    fun update(zone: Zone): TaskLiveData<Void>
    fun delete(zone: Zone): TaskLiveData<Void>
}