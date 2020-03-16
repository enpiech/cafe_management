package fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.zonetype

import com.google.firebase.firestore.DocumentReference
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.FirebaseRemoteDataSource
import fit.tdc.edu.vn.cafemanagement.data.extension.CollectionLiveData
import fit.tdc.edu.vn.cafemanagement.data.extension.DocumentLiveData
import fit.tdc.edu.vn.cafemanagement.data.extension.DocumentType
import fit.tdc.edu.vn.cafemanagement.data.extension.TaskLiveData
import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.ZoneType

interface ZoneTypeRemoteDataSource : FirebaseRemoteDataSource {
    fun readZoneTypes(
        storeId: String,
        documentType: DocumentType
    ): CollectionLiveData<ZoneType>

    fun readZoneType(
        storeId: String,
        zoneTypeId: String,
        documentType: DocumentType
    ): DocumentLiveData<ZoneType>

    fun createZoneType(
        storeId: String,
        zoneType: ZoneType
    ): TaskLiveData<DocumentReference>

    fun updateZoneType(
        storeId: String,
        zoneType: ZoneType
    ): TaskLiveData<Void>

    fun deleteZoneType(
        storeId: String,
        zoneTypeId: String
    ): TaskLiveData<Void>
}