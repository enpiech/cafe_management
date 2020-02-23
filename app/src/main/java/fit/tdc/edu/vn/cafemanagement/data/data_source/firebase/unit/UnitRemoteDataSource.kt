package fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.unit

import com.google.firebase.firestore.DocumentReference
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.FirebaseRemoteDataSource
import fit.tdc.edu.vn.cafemanagement.data.extension.CollectionLiveData
import fit.tdc.edu.vn.cafemanagement.data.extension.DocumentLiveData
import fit.tdc.edu.vn.cafemanagement.data.extension.DocumentType
import fit.tdc.edu.vn.cafemanagement.data.extension.TaskLiveData
import fit.tdc.edu.vn.cafemanagement.data.model.unit.Unit

interface UnitRemoteDataSource : FirebaseRemoteDataSource {
    fun readUnits(
        storeId: String,
        documentType: DocumentType
    ): CollectionLiveData<Unit>

    fun readUnit(
        storeId: String,
        unitId: String,
        documentType: DocumentType
    ): DocumentLiveData<Unit>

    fun createUnit(
        storeId: String,
        unit: Unit
    ): TaskLiveData<DocumentReference>

    fun updateUnit(
        storeId: String,
        unit: Unit
    ): TaskLiveData<Void>

    fun deleteUnit(
        storeId: String,
        unitId: String
    ): TaskLiveData<Void>
}