package fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.unit

import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import fit.tdc.edu.vn.cafemanagement.data.extension.*
import fit.tdc.edu.vn.cafemanagement.data.model.unit.Unit
import fit.tdc.edu.vn.cafemanagement.util.Constants

class UnitRemoteDataSourceImpl : UnitRemoteDataSource {
    private val db: FirebaseFirestore by lazy {
        Firebase.firestore
    }

    override fun readUnits(
        storeId: String,
        documentType: DocumentType
    ): CollectionLiveData<Unit> =
        db.collection(Constants.STORES_KEY).document(storeId)
            .collection(Constants.UNITS_KEY)
            .asLiveData(documentType)

    override fun readUnit(
        storeId: String,
        unitId: String,
        documentType: DocumentType
    ): DocumentLiveData<Unit> =
        db.collection(Constants.STORES_KEY).document(storeId)
            .collection(Constants.UNITS_KEY).document(unitId)
            .asLiveData(documentType)

    override fun createUnit(
        storeId: String,
        unit: Unit
    ): TaskLiveData<DocumentReference> =
        db.collection(Constants.STORES_KEY).document(storeId)
            .collection(Constants.UNITS_KEY)
            .add(unit)
            .asLiveData()

    override fun updateUnit(
        storeId: String,
        unit: Unit
    ): TaskLiveData<Void> {
        val unitCollectionReference =
            db.collection(Constants.STORES_KEY).document(storeId).collection(Constants.UNITS_KEY)
        val materialCollectionReference =
            db.collection(Constants.STORES_KEY).document(storeId)
                .collection(Constants.MATERIALS_KEY)
        materialCollectionReference.whereEqualTo(Constants.UNIT_ID_KEY, unit.id)
            .get()
            .addOnSuccessListener { querySnapshot ->
                db.runBatch { batch ->
                    querySnapshot.documents
                        .map { documentSnapshot -> documentSnapshot.reference }
                        .forEach { documentReference: DocumentReference ->
                            batch.update(
                                documentReference,
                                Constants.UNIT_NAME_KEY,
                                unit.name
                            )
                        }
                }

            }
        val unitDocumentReference = unitCollectionReference.document(unit.id)
        return unitDocumentReference.set(unit).asLiveData()
    }

    override fun deleteUnit(
        storeId: String,
        unitId: String
    ): TaskLiveData<Void> {
        val unitCollectionReference =
            db.collection(Constants.STORES_KEY).document(storeId).collection(Constants.UNITS_KEY)
        val materialCollectionReference =
            db.collection(Constants.STORES_KEY).document(storeId)
                .collection(Constants.MATERIALS_KEY)
        materialCollectionReference.whereEqualTo(Constants.UNIT_ID_KEY, unitId)
            .get()
            .addOnSuccessListener { querySnapshot ->
                db.runBatch { batch ->
                    querySnapshot.documents
                        .map { documentSnapshot -> documentSnapshot.reference }
                        .forEach { documentReference: DocumentReference ->
                            batch.update(documentReference, Constants.UNIT_NAME_KEY, null)
                            batch.update(documentReference, Constants.UNIT_ID_KEY, null)
                        }
                }
            }
        return unitCollectionReference.document(unitId).delete().asLiveData()
    }

}