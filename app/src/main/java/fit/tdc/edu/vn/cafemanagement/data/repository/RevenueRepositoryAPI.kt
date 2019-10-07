package fit.tdc.edu.vn.cafemanagement.data.repository

import com.google.firebase.firestore.DocumentReference
import fit.tdc.edu.vn.cafemanagement.data.extension.CollectionLiveData
import fit.tdc.edu.vn.cafemanagement.data.extension.DocumentLiveData
import fit.tdc.edu.vn.cafemanagement.data.extension.TaskLiveData
import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.Revenue

interface RevenueRepositoryAPI {
    fun getAllRevenues(): CollectionLiveData<Revenue>
    fun getRevenue(id: String): DocumentLiveData<Revenue>
    fun insert(revenue: Revenue): TaskLiveData<DocumentReference>
    fun update(revenue: Revenue)
    fun delete(revenue: Revenue): TaskLiveData<Void>
    fun deleteAllRevenues()
}