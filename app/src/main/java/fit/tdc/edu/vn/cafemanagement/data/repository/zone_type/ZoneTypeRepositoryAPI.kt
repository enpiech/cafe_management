package fit.tdc.edu.vn.cafemanagement.data.repository.zone_type

import com.google.firebase.firestore.DocumentReference
import fit.tdc.edu.vn.cafemanagement.data.extension.CollectionLiveData
import fit.tdc.edu.vn.cafemanagement.data.extension.DocumentLiveData
import fit.tdc.edu.vn.cafemanagement.data.extension.TaskLiveData
import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.ZoneType

interface ZoneTypeRepositoryAPI {
    fun getAllZoneTypes(): CollectionLiveData<ZoneType>
    fun getZoneType(id: String): DocumentLiveData<ZoneType>
    fun insert(zoneType: ZoneType): TaskLiveData<DocumentReference>
    fun update(zoneType: ZoneType)
    fun delete(zoneType: ZoneType): TaskLiveData<Void>
}