package fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.zone

import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import fit.tdc.edu.vn.cafemanagement.data.extension.*
import fit.tdc.edu.vn.cafemanagement.data.model.zone.Zone
import fit.tdc.edu.vn.cafemanagement.util.Constants

class ZoneRemoteDataSourceImpl : ZoneRemoteDataSource {
    private val db: FirebaseFirestore by lazy {
        Firebase.firestore
    }

    override fun readZones(
        storeId: String,
        documentType: DocumentType
    ): CollectionLiveData<Zone> =
        db.collection(Constants.STORES_KEY).document(storeId)
            .collection(Constants.ZONES_KEY)
            .asLiveData(documentType)

    override fun readZone(
        storeId: String,
        zoneId: String,
        documentType: DocumentType
    ): DocumentLiveData<Zone> =
        db.collection(Constants.STORES_KEY).document(storeId)
            .collection(Constants.ZONES_KEY).document(zoneId)
            .asLiveData()

    override fun createZone(
        storeId: String,
        zone: Zone
    ): TaskLiveData<DocumentReference> =
        db.collection(Constants.STORES_KEY).document(storeId)
            .collection(Constants.ZONES_KEY)
            .add(zone)
            .asLiveData()

    override fun updateZone(
        storeId: String,
        zone: Zone
    ): TaskLiveData<Void> {
        val zoneCollectionReference =
            db.collection(Constants.STORES_KEY).document(storeId).collection(Constants.ZONES_KEY)
        val tableCollectionReference =
            db.collection(Constants.STORES_KEY).document(storeId).collection(Constants.TABLES_KEY)
        tableCollectionReference.whereEqualTo(Constants.ZONE_ID_KEY, zone.id)
            .get()
            .addOnSuccessListener { querySnapshot ->
                db.runBatch { batch ->
                    querySnapshot.documents
                        .map { documentSnapshot -> documentSnapshot.reference }
                        .forEach { documentReference: DocumentReference ->
                            batch.update(
                                documentReference,
                                Constants.ZONE_NAME_KEY,
                                zone.name
                            )
                        }
                }
            }
        val zoneDocumentReference = zoneCollectionReference.document(zone.id)
        return zoneDocumentReference.set(zone).asLiveData()
    }

    override fun deleteZone(
        storeId: String,
        zoneId: String
    ): TaskLiveData<Void> {
        val zoneDocumentReference =
            db.collection(Constants.STORES_KEY).document(storeId).collection(Constants.ZONES_KEY)
                .document(zoneId)
        val tableCollectionReference =
            db.collection(Constants.STORES_KEY).document(storeId).collection(Constants.TABLES_KEY)
        tableCollectionReference.whereEqualTo(Constants.ZONE_ID_KEY, zoneId)
            .get()
            .addOnSuccessListener { querySnapshot ->
                db.runBatch { batch ->
                    querySnapshot.documents
                        .map { documentSnapshot -> documentSnapshot.reference }
                        .forEach { documentReference: DocumentReference ->
                            batch.update(
                                documentReference,
                                Constants.ZONE_NAME_KEY,
                                null
                            )
                        }
                }
            }

        return zoneDocumentReference.delete().asLiveData()
    }
}