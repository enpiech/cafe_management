package fit.tdc.edu.vn.cafemanagement.data.extension

import androidx.lifecycle.LiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import fit.tdc.edu.vn.cafemanagement.data.Result
import fit.tdc.edu.vn.cafemanagement.data.model.login.LoggedInUser
import fit.tdc.edu.vn.cafemanagement.data.model.user.UserType

class UserLoggedInLiveData(
    private val firebaseAuth: FirebaseAuth? = FirebaseAuth.getInstance(),
    private val reference: DocumentReference? = null,
    private val userType: UserType? = UserType.MANAGER,
    private val username: String?,
    private val password: String?
): LiveData<FirestoreResource<LoggedInUser>>() {

    /**
     * When the instance becomes active, initially post a [Status.LOADING] value.
     * Then, call [DocumentReference.addSnapshotListener] to listen for data changes, and post new values to the [DocumentLiveData] instance appropriately.
     */
    override fun onActive() {
        super.onActive()
        postValue(FirestoreResource.loading())
        when (userType) {
            null -> null
            UserType.MANAGER -> firebaseAuthLogin()
            UserType.BARTENDER -> null
            UserType.STORE_MANAGER -> null
            UserType.WAITER -> null
        }
//        reference.addSnapshotListener { documentSnapshot, firebaseFirestoreException ->
//            if (firebaseFirestoreException != null) {
//                postValue(FirestoreResource.error(firebaseFirestoreException))
//                Log.w("DocumentLiveData", firebaseFirestoreException.localizedMessage)
//                firebaseFirestoreException.printStackTrace()
//            } else {
//                postValue(FirestoreResource.success(documentSnapshot?.toObject(modelClass)?.apply {
//                    id = documentSnapshot.id
//                }))
//            }
//        }
    }

    private fun firebaseAuthLogin() {
        firebaseAuth!!.signInWithEmailAndPassword(username!!, password!!)
            .addOnSuccessListener {
                val user = LoggedInUser(
                    it.user!!.email as String,
                    it.user!!.email as String,
                    UserType.MANAGER
                )
                postValue(FirestoreResource.success(user))
            }
            .addOnFailureListener {
                postValue(FirestoreResource.error(it))
            }
    }
}