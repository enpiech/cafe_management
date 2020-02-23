package fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.revenue

import com.google.firebase.firestore.DocumentReference
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.FirebaseRemoteDataSource
import fit.tdc.edu.vn.cafemanagement.data.extension.CollectionLiveData
import fit.tdc.edu.vn.cafemanagement.data.extension.DocumentLiveData
import fit.tdc.edu.vn.cafemanagement.data.extension.DocumentType
import fit.tdc.edu.vn.cafemanagement.data.extension.TaskLiveData
import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.Revenue

interface RevenueRemoteDataSource : FirebaseRemoteDataSource {
    fun readRevenues(
        storeId: String,
        documentType: DocumentType
    ): CollectionLiveData<Revenue>

    fun readRevenue(
        storeId: String,
        revenueId: String,
        documentType: DocumentType
    ): DocumentLiveData<Revenue>

    fun createRevenue(
        storeId: String,
        revenue: Revenue
    ): TaskLiveData<DocumentReference>

    fun updateRevenue(
        storeId: String,
        revenue: Revenue
    ): TaskLiveData<Void>

    fun deleteRevenue(
        storeId: String,
        revenueID: String
    ): TaskLiveData<Void>
}