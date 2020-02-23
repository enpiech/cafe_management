package fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.store

import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Source
import com.google.firebase.firestore.WriteBatch
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import fit.tdc.edu.vn.cafemanagement.data.extension.*
import fit.tdc.edu.vn.cafemanagement.data.model.store.Store
import fit.tdc.edu.vn.cafemanagement.util.Constants

class StoreRemoteDataSourceImpl : StoreRemoteDataSource {
    private val db: FirebaseFirestore by lazy {
        Firebase.firestore
    }

    override fun readStores(
        documentType: DocumentType
    ): CollectionLiveData<Store> =
        db.collection(Constants.STORES_KEY)
            .asLiveData(documentType)

    override fun readStore(
        storeId: String,
        documentType: DocumentType
    ): DocumentLiveData<Store> =
        db.collection(Constants.STORES_KEY).document(storeId)
            .asLiveData()

    override fun createStore(
        store: Store
    ): TaskLiveData<DocumentReference> {
        return db.collection(Constants.STORES_KEY)
            .add(store)
            .addOnSuccessListener { ref ->
                store.managerId?.let { managerId ->
                    db.runBatch { batch ->
                        val managerRef = db.collection(Constants.USERS_KEY).document(managerId)
                        val field = Constants.STORE_ID_KEY
                        val data = ref.id
                        batch.update(managerRef, field, data)
                    }
                }
            }
            .asLiveData()
    }


    override fun updateStore(
        oldStore: Store, newStore: Store
    ): TaskLiveData<Void> {
        var oldManagerRef: DocumentReference? = null
        oldStore.managerId?.let {
            oldManagerRef = db.collection(Constants.USERS_KEY).document(it)
        }
        var newManagerRef: DocumentReference? = null
        newStore.managerId?.let {
            newManagerRef = db.collection(Constants.USERS_KEY).document(it)
        }
        return db.runBatch { writeBatch: WriteBatch ->
            if (oldStore.managerId.isNullOrBlank() && !newStore.managerId.isNullOrBlank()) {
                newManagerRef?.let {
                    writeBatch.update(it, Constants.STORE_ID_KEY, newStore.id)
                    writeBatch.update(it, Constants.STORE_NAME_KEY, newStore.id)
                }
            } else if (!oldStore.managerId.isNullOrBlank() && newStore.managerId.isNullOrBlank()) {
                oldManagerRef?.let {
                    writeBatch.update(it, Constants.STORE_ID_KEY, null)
                    writeBatch.update(it, Constants.STORE_NAME_KEY, null)
                }
            } else if (!oldStore.managerId.isNullOrBlank() && !newStore.managerId.isNullOrBlank()) {
                writeBatch.update(oldManagerRef!!, Constants.STORE_ID_KEY, null)
                writeBatch.update(oldManagerRef!!, Constants.STORE_NAME_KEY, null)
                writeBatch.update(newManagerRef!!, Constants.STORE_ID_KEY, newStore.id)
                writeBatch.update(newManagerRef!!, Constants.STORE_NAME_KEY, newStore.name)
            }
            writeBatch.set(db.collection(Constants.STORES_KEY).document(newStore.id), newStore)
        }.asLiveData()
    }

    override fun deleteStore(
        storeId: String
    ): TaskLiveData<Void> {
        val refs: ArrayList<DocumentReference> = arrayListOf()
        db.collection(Constants.USERS_KEY)
            .whereEqualTo(Constants.STORE_ID_KEY, storeId)
            .get(Source.CACHE)
            .addOnSuccessListener { querySnap ->
                db.runBatch { batch ->
                    querySnap.documents.forEach { docSnap ->
                        val ref = docSnap.reference
                        refs.add(ref)
                    }
                    refs.forEach {
                        batch.update(it, Constants.STORE_ID_KEY, null)
                    }
                }
            }
        return db.collection(Constants.STORES_KEY).document(storeId).delete().asLiveData()
    }
}