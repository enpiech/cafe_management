package fit.tdc.edu.vn.cafemanagement.data.repository

import androidx.lifecycle.MediatorLiveData
import com.google.firebase.firestore.DocumentReference
import fit.tdc.edu.vn.cafemanagement.data.extension.CollectionLiveData
import fit.tdc.edu.vn.cafemanagement.data.extension.DocumentLiveData
import fit.tdc.edu.vn.cafemanagement.data.extension.TaskLiveData
import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.Material

interface MaterialRepositoryAPI {
    fun getAllMaterials(): CollectionLiveData<Material>
    fun getMaterial(id: String): DocumentLiveData<Material>
    fun insert(material: Material): TaskLiveData<DocumentReference>
    fun update(material: Material)
    fun delete(material: Material): TaskLiveData<Void>
    fun getMaterialsListASCName() : MediatorLiveData<List<Material>?>
    fun getMaterialsListASCPrice() : MediatorLiveData<List<Material>?>
    fun getSellableMaterialsList() : MediatorLiveData<List<Material>?>
    fun getMaterialsListDESCName() : MediatorLiveData<List<Material>?>
    fun getMaterialsListDESCPrice() : MediatorLiveData<List<Material>?>
    fun getUnSellableMaterialsList() : MediatorLiveData<List<Material>?>
}