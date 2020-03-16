package fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.revenue

import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import fit.tdc.edu.vn.cafemanagement.data.extension.*
import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.Revenue
import fit.tdc.edu.vn.cafemanagement.util.Constants

class RevenueRemoteDataSourceImpl : RevenueRemoteDataSource {
    private val db: FirebaseFirestore by lazy {
        Firebase.firestore
    }

    override fun readRevenues(
        storeId: String,
        documentType: DocumentType
    ): CollectionLiveData<Revenue> =
        db.collection(Constants.STORES_KEY).document(storeId)
            .collection(Constants.REVENUES_KEY)
            .asLiveData(documentType)

    override fun readRevenue(
        storeId: String,
        revenueId: String,
        documentType: DocumentType
    ): DocumentLiveData<Revenue> =
        db.collection(Constants.STORES_KEY).document(storeId)
            .collection(Constants.CATEGORIES_KEY).document(revenueId)
            .asLiveData(documentType)

    override fun createRevenue(
        storeId: String,
        revenue: Revenue
    ): TaskLiveData<DocumentReference> =
        db.collection(Constants.STORES_KEY).document(storeId)
            .collection(Constants.CATEGORIES_KEY)
            .add(revenue)
            .asLiveData()

    override fun updateRevenue(
        storeId: String,
        revenue: Revenue
    ): TaskLiveData<Void> {
        return db.collection(Constants.STORES_KEY).document(storeId)
            .collection(Constants.CATEGORIES_KEY).document(revenue.id)
            .set(revenue)
            .asLiveData()
    }

    override fun deleteRevenue(
        storeId: String,
        revenueID: String
    ): TaskLiveData<Void> =
        db.collection(Constants.STORES_KEY).document(storeId)
            .collection(Constants.CATEGORIES_KEY).document(revenueID)
            .delete()
            .asLiveData()

}