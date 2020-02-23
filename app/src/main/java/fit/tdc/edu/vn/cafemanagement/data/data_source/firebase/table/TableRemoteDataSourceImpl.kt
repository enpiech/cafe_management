package fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.table

import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import fit.tdc.edu.vn.cafemanagement.data.extension.*
import fit.tdc.edu.vn.cafemanagement.data.model.table.Table
import fit.tdc.edu.vn.cafemanagement.util.Constants

class TableRemoteDataSourceImpl : TableRemoteDataSource {
    private val db: FirebaseFirestore by lazy {
        Firebase.firestore
    }

    override fun readTables(
        storeId: String,
        documentType: DocumentType
    ): CollectionLiveData<Table> =
        db.collection(Constants.STORES_KEY).document(storeId)
            .collection(Constants.TABLES_KEY)
            .asLiveData(documentType)


    override fun readTable(
        storeId: String,
        tableId: String,
        documentType: DocumentType
    ): DocumentLiveData<Table> =
        db.collection(Constants.STORES_KEY).document(storeId)
            .collection(Constants.TABLES_KEY).document(tableId)
            .asLiveData(documentType)

    override fun createTable(
        storeId: String,
        table: Table
    ): TaskLiveData<DocumentReference> =
        db.collection(Constants.STORES_KEY).document(storeId)
            .collection(Constants.TABLES_KEY)
            .add(table)
            .asLiveData()

    override fun updateTable(storeId: String, table: Table): TaskLiveData<Void> {
        return db.collection(Constants.STORES_KEY).document(storeId)
            .collection(Constants.TABLES_KEY).document(table.id)
            .set(table)
            .asLiveData()
    }

    override fun deleteTable(
        storeId: String,
        tableID: String
    ): TaskLiveData<Void> =
        db.collection(Constants.STORES_KEY).document(storeId)
            .collection(Constants.TABLES_KEY).document(tableID)
            .delete()
            .asLiveData()
}