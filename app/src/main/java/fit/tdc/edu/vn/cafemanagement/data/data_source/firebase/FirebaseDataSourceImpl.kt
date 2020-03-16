package fit.tdc.edu.vn.cafemanagement.data.data_source.firebase

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import javax.inject.Singleton

@Singleton
abstract class FirebaseDataSourceImpl : FirebaseRemoteDataSource {
    protected val db: FirebaseFirestore by lazy {
        Firebase.firestore
    }
}
