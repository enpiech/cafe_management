package fit.tdc.edu.vn.cafemanagement.data.repository

import com.google.firebase.firestore.DocumentReference
import fit.tdc.edu.vn.cafemanagement.data.extension.CollectionLiveData
import fit.tdc.edu.vn.cafemanagement.data.extension.DocumentLiveData
import fit.tdc.edu.vn.cafemanagement.data.extension.TaskLiveData
import fit.tdc.edu.vn.cafemanagement.data.model.ware_house.WareHouse

interface WareHouseRepositoryAPI {
    fun getAllWareHouses(): CollectionLiveData<WareHouse>
    fun getWareHouse(id: String): DocumentLiveData<WareHouse>
    fun insert(wareHouse: WareHouse): TaskLiveData<DocumentReference>
    fun update(wareHouse: WareHouse): TaskLiveData<Void>
    fun delete(wareHouse: WareHouse): TaskLiveData<Void>
}