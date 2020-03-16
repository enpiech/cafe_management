package fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.material

import com.google.firebase.firestore.DocumentReference
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.FirebaseRemoteDataSource
import fit.tdc.edu.vn.cafemanagement.data.extension.CollectionLiveData
import fit.tdc.edu.vn.cafemanagement.data.extension.DocumentLiveData
import fit.tdc.edu.vn.cafemanagement.data.extension.DocumentType
import fit.tdc.edu.vn.cafemanagement.data.extension.TaskLiveData
import fit.tdc.edu.vn.cafemanagement.data.model.material.Material

interface MaterialRemoteDataSource : FirebaseRemoteDataSource {
    fun readMaterials(
        storeId: String,
        documentType: DocumentType
    ): CollectionLiveData<Material>

    fun readMaterial(
        storeId: String,
        materialId: String,
        documentType: DocumentType
    ): DocumentLiveData<Material>

    fun createMaterial(
        storeId: String,
        material: Material
    ): TaskLiveData<DocumentReference>

    fun updateMaterial(
        storeId: String,
        material: Material
    ): TaskLiveData<Void>

    fun deleteMaterial(
        storeId: String,
        materialID: String
    ): TaskLiveData<Void>
}