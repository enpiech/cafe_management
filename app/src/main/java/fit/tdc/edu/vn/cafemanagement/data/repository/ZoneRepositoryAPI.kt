package fit.tdc.edu.vn.cafemanagement.data.repository

import com.google.firebase.firestore.DocumentReference
import fit.tdc.edu.vn.cafemanagement.data.extension.CollectionLiveData
import fit.tdc.edu.vn.cafemanagement.data.extension.DocumentLiveData
import fit.tdc.edu.vn.cafemanagement.data.extension.TaskLiveData
import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.Zone
import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.ZoneType

interface ZoneRepositoryAPI {
    fun getAllZones(): CollectionLiveData<Zone>
    fun getZone(id: String): DocumentLiveData<Zone>
    fun insert(zone: Zone): TaskLiveData<DocumentReference>
    fun update(zone: Zone)
    fun delete(zone: Zone): TaskLiveData<Void>
    fun deleteAllZones()
}