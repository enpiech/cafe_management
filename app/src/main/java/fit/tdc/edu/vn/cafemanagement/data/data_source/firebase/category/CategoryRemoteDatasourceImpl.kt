package fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.category

import com.google.firebase.firestore.DocumentReference
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.FirebaseDataSourceImpl
import fit.tdc.edu.vn.cafemanagement.data.extension.*
import fit.tdc.edu.vn.cafemanagement.data.model.category.Category
import fit.tdc.edu.vn.cafemanagement.util.Constants

class CategoryRemoteDatasourceImpl : FirebaseDataSourceImpl(), CategoryRemoteDataSource {

    override fun readCategories(
        storeId: String,
        documentType: DocumentType
    ): CollectionLiveData<Category> =
        db.collection(Constants.STORES_KEY).document(storeId)
            .collection(Constants.CATEGORIES_KEY)
            .asLiveData(documentType)

    override fun readCategory(
        storeId: String,
        categoryId: String,
        documentType: DocumentType
    ): DocumentLiveData<Category> =
        db.collection(Constants.STORES_KEY).document(storeId)
            .collection(Constants.CATEGORIES_KEY).document(categoryId)
            .asLiveData(documentType)

    override fun createCategory(
        storeId: String,
        category: Category
    ): TaskLiveData<DocumentReference> =
        db.collection(Constants.STORES_KEY).document(storeId)
            .collection(Constants.CATEGORIES_KEY)
            .add(category)
            .asLiveData()

    override fun updateCategory(storeId: String, category: Category): TaskLiveData<Void> {
        val categoryCollectionReference =
            db.collection(Constants.STORES_KEY).document(storeId)
                .collection(Constants.CATEGORIES_KEY)
        val materialCollectionReference =
            db.collection(Constants.STORES_KEY).document(storeId)
                .collection(Constants.MATERIALS_KEY)
        materialCollectionReference.whereEqualTo(Constants.CATEGORY_ID_KEY, category.id)
            .get()
            .addOnSuccessListener { querySnapshot ->
                db.runBatch { batch ->
                    querySnapshot.documents
                        .map { documentSnapshot -> documentSnapshot.reference }
                        .forEach { documentReference: DocumentReference ->
                            batch.update(
                                documentReference,
                                Constants.CATEGORY_NAME_KEY,
                                category.name
                            )
                        }
                }
            }
        val categoryRef = categoryCollectionReference.document(category.id)
        return categoryRef.set(category).asLiveData()
    }

    override fun deleteCategory(storeId: String, categoryId: String): TaskLiveData<Void> {
        val categoryCollectionReference =
            db.collection(Constants.STORES_KEY).document(storeId)
                .collection(Constants.CATEGORIES_KEY)
        val materialCollectionReference =
            db.collection(Constants.STORES_KEY).document(storeId)
                .collection(Constants.MATERIALS_KEY)
        materialCollectionReference.whereEqualTo(Constants.CATEGORY_ID_KEY, categoryId)
            .get()
            .addOnSuccessListener { querySnapshot ->
                db.runBatch { batch ->
                    querySnapshot.documents
                        .map { documentSnapshot -> documentSnapshot.reference }
                        .forEach { documentReference: DocumentReference ->
                            batch.update(
                                documentReference,
                                Constants.CATEGORY_NAME_KEY,
                                null
                            )
                        }
                }
            }
        return categoryCollectionReference.document(categoryId).delete().asLiveData()
    }
}