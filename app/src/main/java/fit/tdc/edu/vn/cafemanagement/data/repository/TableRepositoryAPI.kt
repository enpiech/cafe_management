package fit.tdc.edu.vn.cafemanagement.data.repository

import com.google.firebase.firestore.DocumentReference
import fit.tdc.edu.vn.cafemanagement.data.extension.CollectionLiveData
import fit.tdc.edu.vn.cafemanagement.data.extension.DocumentLiveData
import fit.tdc.edu.vn.cafemanagement.data.extension.TaskLiveData
import fit.tdc.edu.vn.cafemanagement.data.model.table.Table

interface TableRepositoryAPI {
    fun getAllTables(): CollectionLiveData<Table>
    fun getTable(id: String): DocumentLiveData<Table>
    fun insert(table: Table): TaskLiveData<DocumentReference>
    fun update(table: Table): TaskLiveData<Void>
    fun delete(table: Table): TaskLiveData<Void>
}