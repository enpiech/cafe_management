package fit.tdc.edu.vn.cafemanagement.data.extension

import android.util.Log
import androidx.lifecycle.LiveData
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import fit.tdc.edu.vn.cafemanagement.data.model.FirestoreModel

/**
 * An observable [LiveData] representing the current state of the data from a [Query].
 *
 * @param T The [FirestoreModel] to convert the resulting Firestore documents into
 * @param modelClass The class representing [T]
 * @param query The [Query] to provide the [QueryLiveData] for.
 *
 * @see [Query.asLiveData]
 * @see [FirestoreResource]
 * @see [FirestoreModel]
 */
class QueryLiveData<T : FirestoreModel>(private val modelClass: Class<T>, private val query: Query) : LiveData<FirestoreResource<List<T>>>() {

    /**
     * When the instance becomes active, initially post a [Status.LOADING] value.
     * Then, call [Query.addSnapshotListener] to listen for data changes, and post new values to the [QueryLiveData] instance appropriately.
     */
    override fun onActive() {
        super.onActive()
        postValue(FirestoreResource.loading())
        query.addSnapshotListener { querySnapshot, firebaseFirestoreException ->
            if (firebaseFirestoreException != null) {
                postValue(FirestoreResource.error(firebaseFirestoreException))
                Log.w("QueryLiveData", firebaseFirestoreException.localizedMessage)
                firebaseFirestoreException.printStackTrace()
            } else {
                val documents = querySnapshot?.documents.orEmpty()
                val models = documents.mapNotNull { doc -> doc.toObject(modelClass)?.apply { id = doc.id } }
                postValue(FirestoreResource.success(models))
            }
        }
    }

    fun updateField(field: String, value: Any?): Task<QuerySnapshot> = query.get().addOnSuccessListener { querySnapshot ->
        querySnapshot.forEach {
            it.data[field] = value
        }
    }
}