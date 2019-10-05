package fit.tdc.edu.vn.cafemanagement.data.extension

import androidx.lifecycle.LiveData
import android.util.Log
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.QuerySnapshot
import java.lang.Exception

/**
 * An observable [LiveData] representing the current state of the data at a [CollectionReference].
 *
 * @param T The [FirestoreModel] to convert the resulting Firestore documents into
 * @param modelClass The class representing [T]
 * @param collectionReference The [CollectionReference] to provide the [CollectionLiveData] for.
 *
 * @see [CollectionReference.asLiveData]
 * @see [FirestoreResource]
 * @see [FirestoreModel]
 */
class CollectionLiveData<T : FirestoreModel>(
    private val modelClass: Class<T>,
    private val collectionReference: CollectionReference,
    private val documentType: DocumentType
) : LiveData<FirestoreResource<List<T>>>() {

    /**
     * When the instance becomes active, initially post a [Status.LOADING] value.
     * Then, call [CollectionReference.addSnapshotListener] to listen for data changes,
     * and post new values to the [CollectionLiveData] instance appropriately.
     */
    override fun onActive() {
        super.onActive()
        postValue(FirestoreResource.loading())

        if (documentType == DocumentType.SINGLE) {
            collectionReference
                .get()
                .addOnSuccessListener {querySnapshot ->
                    listenAllDocument(querySnapshot)
                }
                .addOnFailureListener { exception ->
                    handleException(exception)
                }
        } else {
            collectionReference
                .addSnapshotListener { querySnapshot, firebaseFirestoreException ->
                    if (firebaseFirestoreException != null) {
                        handleException(firebaseFirestoreException)
                    } else {
                        when (documentType) {
                            DocumentType.ALL -> listenAllDocument(querySnapshot)
                            else -> listenChangedDocument(querySnapshot)
                        }

                    }
                }
        }
    }

    private fun listenAllDocument(querySnapshot: QuerySnapshot?) {
        val documents = querySnapshot?.documents.orEmpty()
        val models = documents.mapNotNull { doc ->
            doc.toObject(modelClass)?.apply {
                id = doc.id
            }
        }
        postValue(FirestoreResource.success(models))
    }

    private fun listenChangedDocument(querySnapshot: QuerySnapshot?) {
        val documents = querySnapshot?.documentChanges.orEmpty()
        val models = when (documentType) {
            DocumentType.MODIFIED -> documents
                .filter { documentChange ->
                    documentChange.type == DocumentChange.Type.MODIFIED
                }
            DocumentType.ADDED -> documents
                .filter { documentChange ->
                    documentChange.type == DocumentChange.Type.ADDED
                }
            DocumentType.REMOVED -> documents
                .filter { documentChange ->
                    documentChange.type == DocumentChange.Type.REMOVED
                }
            else -> documents
        }.mapNotNull {documentsChanged ->
            val doc = documentsChanged.document
            doc.toObject(modelClass).apply {
                id = doc.id
            }

        }
        postValue(FirestoreResource.success(models))
    }

    private fun handleException(exception: Exception) {
        postValue(FirestoreResource.error(exception))
        Log.w("CollectionLiveData", exception.localizedMessage)
        exception.printStackTrace()
    }

    /**
     * Adds a new item to the collection referred to by [collectionReference].
     *
     * This is returned as a [TaskLiveData], so the state of the operation can be observed if desired. However, it is not required to be observed in order to execute the operation.
     *
     * @param item The item to add to the collection
     * @return A [TaskLiveData] representing the state of the operation, as a [TaskResult].
     */
    fun add(item: T): TaskLiveData<DocumentReference> = collectionReference.add(item).asLiveData()

}