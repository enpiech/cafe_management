package fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.zonetype

import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import fit.tdc.edu.vn.cafemanagement.data.extension.*
import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.ZoneType
import fit.tdc.edu.vn.cafemanagement.util.Constants

class ZoneTypeRemoteDataSourceImpl : ZoneTypeRemoteDataSource {
    private val db: FirebaseFirestore by lazy {
        Firebase.firestore
    }

    override fun readZoneTypes(
        storeId: String,
        documentType: DocumentType
    ): CollectionLiveData<ZoneType> =
        db.collection(Constants.STORES_KEY).document(storeId)
            .collection(Constants.ZONE_TYPES_KEY)
            .asLiveData(documentType)

    override fun readZoneType(
        storeId: String,
        zoneTypeId: String,
        documentType: DocumentType
    ): DocumentLiveData<ZoneType> =
        db.collection(Constants.STORES_KEY).document(storeId)
            .collection(Constants.ZONE_TYPES_KEY).document(zoneTypeId)
            .asLiveData(documentType)

    override fun createZoneType(
        storeId: String,
        zoneType: ZoneType
    ): TaskLiveData<DocumentReference> =
        db.collection(Constants.STORES_KEY).document(storeId)
            .collection(Constants.ZONE_TYPES_KEY)
            .add(zoneType)
            .asLiveData()

    override fun updateZoneType(
        storeId: String,
        zoneType: ZoneType
    ): TaskLiveData<Void> {
        return db.collection(Constants.STORES_KEY).document(storeId)
            .collection(Constants.ZONE_TYPES_KEY).document(zoneType.id)
            .set(zoneType)
            .asLiveData()
    }

    override fun deleteZoneType(
        storeId: String,
        zoneTypeId: String
    ): TaskLiveData<Void> =
        db.collection(Constants.STORES_KEY).document()
            .collection(Constants.ZONE_TYPES_KEY).document(zoneTypeId)
            .delete()
            .asLiveData()

}