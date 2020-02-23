package fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.zone

import com.google.firebase.firestore.DocumentReference
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.FirebaseRemoteDataSource
import fit.tdc.edu.vn.cafemanagement.data.extension.CollectionLiveData
import fit.tdc.edu.vn.cafemanagement.data.extension.DocumentLiveData
import fit.tdc.edu.vn.cafemanagement.data.extension.DocumentType
import fit.tdc.edu.vn.cafemanagement.data.extension.TaskLiveData
import fit.tdc.edu.vn.cafemanagement.data.model.zone.Zone

interface ZoneRemoteDataSource : FirebaseRemoteDataSource {
    fun readZones(
        storeId: String,
        documentType: DocumentType
    ): CollectionLiveData<Zone>

    fun readZone(
        storeId: String,
        zoneId: String,
        documentType: DocumentType
    ): DocumentLiveData<Zone>

    fun createZone(
        storeId: String,
        zone: Zone
    ): TaskLiveData<DocumentReference>

    fun updateZone(
        storeId: String,
        zone: Zone
    ): TaskLiveData<Void>

    fun deleteZone(
        storeId: String,
        zoneId: String
    ): TaskLiveData<Void>
}