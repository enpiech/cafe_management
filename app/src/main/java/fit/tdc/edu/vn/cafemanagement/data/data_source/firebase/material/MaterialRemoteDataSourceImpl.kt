package fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.material

import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import fit.tdc.edu.vn.cafemanagement.data.extension.*
import fit.tdc.edu.vn.cafemanagement.data.model.material.Material
import fit.tdc.edu.vn.cafemanagement.util.Constants.Companion.MATERIALS_KEY
import fit.tdc.edu.vn.cafemanagement.util.Constants.Companion.STORES_KEY

class MaterialRemoteDataSourceImpl : MaterialRemoteDataSource {
    private val db: FirebaseFirestore by lazy {
        Firebase.firestore
    }

    override fun readMaterials(
        storeId: String,
        documentType: DocumentType
    ): CollectionLiveData<Material> =
        db.collection(STORES_KEY).document(storeId)
            .collection(MATERIALS_KEY)
            .asLiveData(documentType)

    override fun readMaterial(
        storeId: String,
        materialId: String,
        documentType: DocumentType
    ): DocumentLiveData<Material> =
        db.collection(STORES_KEY).document(storeId)
            .collection(MATERIALS_KEY).document(materialId)
            .asLiveData(documentType)

    override fun createMaterial(
        storeId: String,
        material: Material
    ): TaskLiveData<DocumentReference> =
        db.collection(STORES_KEY).document(storeId)
            .collection(MATERIALS_KEY)
            .add(material)
            .asLiveData()

    override fun updateMaterial(storeId: String, material: Material): TaskLiveData<Void> =
        db.collection(STORES_KEY).document(storeId)
            .collection(MATERIALS_KEY).document(material.id)
            .set(material)
            .asLiveData()

    override fun deleteMaterial(storeId: String, materialID: String): TaskLiveData<Void> =
        db.collection(STORES_KEY).document(storeId)
            .collection(MATERIALS_KEY).document(materialID)
            .delete()
            .asLiveData()
}