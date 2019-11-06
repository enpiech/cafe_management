package fit.tdc.edu.vn.cafemanagement.data.repository

import androidx.lifecycle.LiveData
import com.google.firebase.firestore.DocumentReference
import fit.tdc.edu.vn.cafemanagement.data.extension.CollectionLiveData
import fit.tdc.edu.vn.cafemanagement.data.extension.DocumentLiveData
import fit.tdc.edu.vn.cafemanagement.data.extension.TaskLiveData
import fit.tdc.edu.vn.cafemanagement.data.model.material.Material

interface MaterialRepositoryAPI {
    fun getAllMaterials(): CollectionLiveData<Material>
    fun getMaterial(id: String): DocumentLiveData<Material>
    fun insert(material: Material): TaskLiveData<DocumentReference>
    fun update(material: Material): TaskLiveData<Void>
    fun delete(material: Material): TaskLiveData<Void>
    fun getMaterialsListASCName() : LiveData<List<Material>>
    fun getMaterialsListASCPrice() : LiveData<List<Material>>
    fun getSellableMaterials() : LiveData<List<Material>>
    fun getUnsellableMaterialsList() : LiveData<List<Material>>
}