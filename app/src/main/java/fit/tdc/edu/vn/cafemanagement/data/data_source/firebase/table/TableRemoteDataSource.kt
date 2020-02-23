package fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.table

import com.google.firebase.firestore.DocumentReference
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.FirebaseRemoteDataSource
import fit.tdc.edu.vn.cafemanagement.data.extension.CollectionLiveData
import fit.tdc.edu.vn.cafemanagement.data.extension.DocumentLiveData
import fit.tdc.edu.vn.cafemanagement.data.extension.DocumentType
import fit.tdc.edu.vn.cafemanagement.data.extension.TaskLiveData
import fit.tdc.edu.vn.cafemanagement.data.model.table.Table

interface TableRemoteDataSource : FirebaseRemoteDataSource {
    fun readTables(
        storeId: String,
        documentType: DocumentType
    ): CollectionLiveData<Table>

    fun readTable(
        storeId: String,
        tableId: String,
        documentType: DocumentType
    ): DocumentLiveData<Table>

    fun createTable(
        storeId: String,
        table: Table
    ): TaskLiveData<DocumentReference>

    fun updateTable(
        storeId: String,
        table: Table
    ): TaskLiveData<Void>

    fun deleteTable(
        storeId: String,
        tableID: String
    ): TaskLiveData<Void>
}