package fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.warehouse

import com.google.firebase.firestore.DocumentReference
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.FirebaseRemoteDataSource
import fit.tdc.edu.vn.cafemanagement.data.extension.CollectionLiveData
import fit.tdc.edu.vn.cafemanagement.data.extension.DocumentLiveData
import fit.tdc.edu.vn.cafemanagement.data.extension.DocumentType
import fit.tdc.edu.vn.cafemanagement.data.extension.TaskLiveData
import fit.tdc.edu.vn.cafemanagement.data.model.ware_house.WareHouse

interface WareHouseRemoteDataSource : FirebaseRemoteDataSource {
    fun readWareHouses(
        storeId: String,
        documentType: DocumentType
    ): CollectionLiveData<WareHouse>

    fun readWareHouse(
        storeId: String,
        wareHouseId: String,
        documentType: DocumentType
    ): DocumentLiveData<WareHouse>

    fun createWareHouse(
        storeId: String,
        wareHouse: WareHouse
    ): TaskLiveData<DocumentReference>

    fun updateWareHouse(
        storeId: String,
        wareHouse: WareHouse
    ): TaskLiveData<Void>

    fun deleteWareHouse(
        storeId: String,
        wareHouseId: String
    ): TaskLiveData<Void>
}