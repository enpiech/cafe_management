package fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.warehouse

import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import fit.tdc.edu.vn.cafemanagement.data.extension.*
import fit.tdc.edu.vn.cafemanagement.data.model.ware_house.WareHouse
import fit.tdc.edu.vn.cafemanagement.util.Constants

class WareHouseRemoteDataSourceImpl : WareHouseRemoteDataSource {
    private val db: FirebaseFirestore by lazy {
        Firebase.firestore
    }

    override fun readWareHouses(
        storeId: String,
        documentType: DocumentType
    ): CollectionLiveData<WareHouse> =
        db.collection(Constants.STORES_KEY).document(storeId)
            .collection(Constants.WAREHOUSES_KEY)
            .asLiveData()

    override fun readWareHouse(
        storeId: String,
        wareHouseId: String,
        documentType: DocumentType
    ): DocumentLiveData<WareHouse> =
        db.collection(Constants.STORES_KEY).document(storeId)
            .collection(Constants.WAREHOUSES_KEY).document(wareHouseId)
            .asLiveData()

    override fun createWareHouse(
        storeId: String,
        wareHouse: WareHouse
    ): TaskLiveData<DocumentReference> =
        db.collection(Constants.STORES_KEY).document(storeId)
            .collection(Constants.WAREHOUSES_KEY)
            .add(wareHouse)
            .asLiveData()

    override fun updateWareHouse(storeId: String, wareHouse: WareHouse): TaskLiveData<Void> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteWareHouse(storeId: String, wareHouseId: String): TaskLiveData<Void> =
        db.collection(Constants.STORES_KEY).document(storeId)
            .collection(Constants.WAREHOUSES_KEY).document(wareHouseId)
            .delete()
            .asLiveData()
}