package fit.tdc.edu.vn.cafemanagement.data.repository

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
    fun getMaterialsListASCName() : List<Material>
    fun getMaterialsListASCPrice() : List<Material>
    fun getSellableMaterialsList() : List<Material>
    fun getMaterialsListDESCName() : List<Material>
    fun getMaterialsListDESCPrice() : List<Material>
    fun getUnSellableMaterialsList() : List<Material>
}