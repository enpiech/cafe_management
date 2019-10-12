package fit.tdc.edu.vn.cafemanagement.data.data_source.firebase

import android.util.Log
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import fit.tdc.edu.vn.cafemanagement.data.extension.*
import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.*
import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.Unit
import javax.inject.Singleton

@Singleton
class FireBaseDataSource: FireBaseAPI {
    private val db: FirebaseFirestore by lazy {
        FirebaseFirestore.getInstance()
    }
    companion object {
        private const val STORES_KEY        = "stores"
        private const val CATEGORIES_KEY    = "categories"
        private const val MATERIALS_KEY     = "materials"
        private const val TABLES_KEY        = "tables"
        private const val REVENUES_KEY      = "revenues"
        private const val UNITS_KEY         = "units"
        private const val ZONE_TYPES_KEY    = "zoneTypes"
        private const val ZONES_KEY         = "zones"
        private const val USERS_KEY         = "users"
        private const val PAYMENTS_KEY      = "payments"
    }

    /**
    *
    * ==========  CATEGORY  ============
    */

//    fun getUserOfStore(storeId: String) {
//        db.collection("userOfStore").document("7MmeTKnmhgOieVehY05S")
//            .get()
//            .addOnSuccessListener {
//                if (!it.data.isNullOrEmpty()) {
//                    val list = ArrayList<String>()
//                    val map: Map<String, Any>? = it.data
//                    if (!map.isNullOrEmpty()) {
//                        for (entry in map.entries) {
//                            list.add(entry.value.toString())
//                        }
//                    }
//                    list.forEach { tag ->
//                        Log.d("test", tag)
//                    }
//                }
//            }
//    }

    override fun fetchCategoryList(
        storeId: String
    ) =
        db.collection(STORES_KEY).document(storeId)
            .collection(CATEGORIES_KEY)
            .get()
            .asLiveData()

    override fun getCategoryList(
        storeId: String,
        documentType: DocumentType
    ): CollectionLiveData<Category> =
        db.collection(STORES_KEY).document(storeId)
            .collection(CATEGORIES_KEY)
            .asLiveData(documentType)

    override fun getCategory(
        storeId: String,
        categoryId: String,
        documentType: DocumentType
    ): DocumentLiveData<Category> =
        db.collection(STORES_KEY).document(storeId)
            .collection(CATEGORIES_KEY).document(categoryId)
            .asLiveData(documentType)

    override fun createCategory(
        storeId: String,
        category: Category
    ): TaskLiveData<DocumentReference> {
        return db.collection(STORES_KEY).document(storeId)
            .collection(CATEGORIES_KEY)
            .add(category)
            .asLiveData()
    }

    override fun modifyCategory(
        storeId: String,
        category: Category
    ): TaskLiveData<Void> {
        return db.collection(STORES_KEY).document(storeId)
            .collection(CATEGORIES_KEY).document(category.id)
            .set(category)
            .asLiveData()
    }

    override fun deleteCategory(
        storeId: String,
        categoryId: String
    ): TaskLiveData<Void> =
        db.collection(STORES_KEY).document(storeId)
            .collection(CATEGORIES_KEY).document(categoryId)
            .delete()
            .asLiveData()

    /**
     *
     * ==========  MATERIAL  ============
     */
    override fun getMaterialList(
        storeId: String,
        documentType: DocumentType
    ): CollectionLiveData<Material> =
        db.collection(STORES_KEY).document(storeId)
            .collection(MATERIALS_KEY)
            .asLiveData(documentType)

    override fun getMaterial(
        storeId: String,
        materialId: String,
        documentType: DocumentType
    ): DocumentLiveData<Material> =
        db.collection(STORES_KEY).document(storeId)
            .collection(CATEGORIES_KEY).document(materialId)
            .asLiveData(documentType)

    override fun createMaterial(
        storeId: String,
        material: Material
    ): TaskLiveData<DocumentReference> =
        db.collection(STORES_KEY).document(storeId)
            .collection(CATEGORIES_KEY)
            .add(material)
            .asLiveData()

    override fun modifyMaterial(
        storeId: String,
        material: Material
    ): TaskLiveData<Void> {
        return db.collection(STORES_KEY).document(storeId)
            .collection(CATEGORIES_KEY).document(material.id)
            .set(material)
            .asLiveData()
    }

    override fun deleteMaterial(
        storeId: String,
        materialID: String
    ): TaskLiveData<Void> =
        db.collection(STORES_KEY).document(storeId)
            .collection(CATEGORIES_KEY).document(materialID)
            .delete()
            .asLiveData()


    /**
     *
     * ==========  TABLE  ============
     */
    override fun getTableList(
        storeId: String,
        documentType: DocumentType
    ): CollectionLiveData<Table> =
        db.collection(STORES_KEY).document(storeId)
            .collection(TABLES_KEY)
            .asLiveData(documentType)


    override fun getTable(
        storeId: String,
        tableId: String,
        documentType: DocumentType
    ): DocumentLiveData<Table> =
        db.collection(STORES_KEY).document(storeId)
            .collection(CATEGORIES_KEY).document(tableId)
            .asLiveData(documentType)

    override fun createTable(
        storeId: String,
        table: Table
    ): TaskLiveData<DocumentReference> =
        db.collection(STORES_KEY).document(storeId)
            .collection(CATEGORIES_KEY)
            .add(table)
            .asLiveData()

    override fun modifyTable(storeId: String, table: Table): TaskLiveData<Void> {
        return db.collection(STORES_KEY).document(storeId)
            .collection(CATEGORIES_KEY).document(table.id)
            .set(table)
            .asLiveData()
    }

    override fun deleteTable(
        storeId: String,
        tableID: String
    ): TaskLiveData<Void> =
        db.collection(STORES_KEY).document(storeId)
            .collection(CATEGORIES_KEY).document(tableID)
            .delete()
            .asLiveData()


    /**
     *
     * ==========  REVENUE  ============
     */
    override fun getRevenueList(
        storeId: String,
        documentType: DocumentType
    ): CollectionLiveData<Revenue> =
        db.collection(STORES_KEY).document(storeId)
            .collection(REVENUES_KEY)
            .asLiveData(documentType)

    override fun getRevenue(
        storeId: String,
        revenueId: String,
        documentType: DocumentType
    ): DocumentLiveData<Revenue>  =
        db.collection(STORES_KEY).document(storeId)
            .collection(CATEGORIES_KEY).document(revenueId)
            .asLiveData(documentType)

    override fun createRevenue(
        storeId: String,
        revenue: Revenue
    ): TaskLiveData<DocumentReference> =
        db.collection(STORES_KEY).document(storeId)
            .collection(CATEGORIES_KEY)
            .add(revenue)
            .asLiveData()

    override fun modifyRevenue(
        storeId: String,
        revenue: Revenue
    ): TaskLiveData<Void> {
        return db.collection(STORES_KEY).document(storeId)
            .collection(CATEGORIES_KEY).document(revenue.id)
            .set(revenue)
            .asLiveData()
    }

    override fun deleteRevenue(
        storeId: String,
        revenueID: String
    ): TaskLiveData<Void> =
        db.collection(STORES_KEY).document(storeId)
            .collection(CATEGORIES_KEY).document(revenueID)
            .delete()
            .asLiveData()


    /**
     *
     * ==========  UNIT  ============
     */
    override fun getUnitList(
        storeId: String,
        documentType: DocumentType
    ): CollectionLiveData<Unit> =
        db.collection(STORES_KEY).document(storeId)
            .collection(UNITS_KEY)
            .asLiveData(documentType)

    override fun getUnit(
        storeId: String,
        unitId: String,
        documentType: DocumentType
    ): DocumentLiveData<Unit> =
        db.collection(STORES_KEY).document(storeId)
            .collection(UNITS_KEY).document(unitId)
            .asLiveData(documentType)

    override fun createUnit(
        storeId: String,
        unit: Unit
    ): TaskLiveData<DocumentReference> =
        db.collection(STORES_KEY).document(storeId)
            .collection(UNITS_KEY)
            .add(unit)
            .asLiveData()

    override fun modifyUnit(
        storeId: String,
        unit: Unit
    ): TaskLiveData<Void> {
        Log.d("test", unit.id)
        return db.collection(STORES_KEY).document(storeId)
            .collection(UNITS_KEY).document(unit.id)
            .set(unit)
            .asLiveData()
    }

    override fun deleteUnit(
        storeId: String,
        unitId: String
    ): TaskLiveData<Void> =
        db.collection(STORES_KEY).document(storeId)
            .collection(UNITS_KEY).document(unitId)
            .delete()
            .asLiveData()


    /**
     *
     * ==========  ZONE TYPE  ============
     */
    override fun getZoneTypeList(
        storeId: String,
        documentType: DocumentType
    ): CollectionLiveData<ZoneType> =
        db.collection(STORES_KEY).document(storeId)
            .collection(ZONE_TYPES_KEY)
            .asLiveData(documentType)

    override fun getZoneType(
        storeId: String,
        zoneTypeId: String,
        documentType: DocumentType
    ): DocumentLiveData<ZoneType> =
        db.collection(STORES_KEY).document(storeId)
            .collection(ZONE_TYPES_KEY).document(zoneTypeId)
            .asLiveData(documentType)

    override fun createZoneType(
        storeId: String,
        zoneType: ZoneType
    ): TaskLiveData<DocumentReference> =
        db.collection(STORES_KEY).document(storeId)
            .collection(ZONE_TYPES_KEY)
            .add(zoneType)
            .asLiveData()

    override fun modifyZoneType(
        storeId: String,
        zoneType: ZoneType
    ): TaskLiveData<Void> {
        return db.collection(STORES_KEY).document(storeId)
            .collection(ZONE_TYPES_KEY).document(zoneType.id)
            .set(zoneType)
            .asLiveData()
    }

    override fun deleteZoneType(
        storeId: String,
        zoneTypeId: String
    ): TaskLiveData<Void> =
        db.collection(STORES_KEY).document()
            .collection(ZONE_TYPES_KEY).document(zoneTypeId)
            .delete()
            .asLiveData()

    /**
     *
     * ==========  ZONE  ============
     */
    override fun getZoneList(
        storeId: String,
        documentType: DocumentType
    ): CollectionLiveData<Zone> =
        db.collection(STORES_KEY).document(storeId)
            .collection(ZONES_KEY)
            .asLiveData(documentType)

    override fun getZone(
        storeId: String,
        zoneId: String,
        documentType: DocumentType
    ): DocumentLiveData<Zone> =
        db.collection(STORES_KEY).document(storeId)
            .collection(ZONES_KEY).document(zoneId)
            .asLiveData()

    override fun createZone(
        storeId: String,
        zone: Zone
    ): TaskLiveData<DocumentReference> =
        db.collection(STORES_KEY).document(storeId)
            .collection(ZONES_KEY)
            .add(zone)
            .asLiveData()

    override fun modifyZone(
        storeId: String,
        zone: Zone
    ): TaskLiveData<Void> {
        return db.collection(STORES_KEY).document(storeId)
            .collection(ZONES_KEY).document(zone.id)
            .set(zone)
            .asLiveData()
    }

    override fun deleteZone(
        storeId: String,
        zoneId: String
    ): TaskLiveData<Void> =
        db.collection(STORES_KEY).document(storeId)
            .collection(ZONES_KEY).document(zoneId)
            .delete()
            .asLiveData()


    /**
     *
     * ==========  USER  ============
     */
    override fun getUserList(
        documentType: DocumentType
    ): CollectionLiveData<User> =
        db.collection(USERS_KEY)
            .asLiveData(documentType)

    override fun getUser(
        userId: String,
        documentType: DocumentType
    ): DocumentLiveData<User> =
        db.collection(USERS_KEY).document(userId)
            .asLiveData()

    override fun createUser(
        user: User
    ): TaskLiveData<DocumentReference> =
        db.collection(USERS_KEY)
            .add(user)
            .asLiveData()

    override fun modifyUser(
        user: User
    ): TaskLiveData<Void> {
        return db.collection(USERS_KEY).document(user.id)
            .set(user)
            .asLiveData()
    }

    override fun deleteUser(
        userId: String
    ): TaskLiveData<Void> =
        db.collection(USERS_KEY).document(userId)
            .delete()
            .asLiveData()


    /**
     *
     * ==========  STORE  ============
     */
    override fun getStoreList(
        documentType: DocumentType
    ): CollectionLiveData<Store> =
        db.collection(STORES_KEY)
            .asLiveData(documentType)

    override fun getStore(
        storeId: String,
        documentType: DocumentType
    ): DocumentLiveData<Store> =
        db.collection(STORES_KEY).document(storeId)
            .asLiveData()

    override fun createStore(
        store: Store
    ): TaskLiveData<DocumentReference> =
        db.collection(STORES_KEY)
            .add(store)
            .asLiveData()

    override fun modifyStore(
        store: Store
    ): TaskLiveData<Void> {
        return db.collection(STORES_KEY).document(store.id)
            .set(store)
            .asLiveData()
    }

    override fun deleteStore(
        storeId: String
    ): TaskLiveData<Void> =
        db.collection(STORES_KEY).document(storeId)
            .delete()
            .asLiveData()


    /**
     *
     * ==========  PAYMENT  ============
     */
    override fun getPaymentList(
        storeId: String,
        state: Payment.State,
        documentType: DocumentType
    ): QueryLiveData<Payment> =
        db.collection(STORES_KEY).document(storeId)
            .collection(PAYMENTS_KEY)
            .whereEqualTo("state", Payment.State.COOKED)
            .asLiveData()

//    fun getPaymentWithInfo(
//        storeId: String,
//        paymentId: String,
//        documentType: DocumentType
//    ): LiveData<List<Payment>> {
//        db.collection(STORES_KEY).document(storeId)
//            .collection(PAYMENTS_KEY)
//            .addSnapshotListener { querySnapshot, firebaseFirestoreException ->
//                if (querySnapshot != null && !querySnapshot.isEmpty) {
//                    querySnapshot.forEach { payment ->
//                        payment.reference.collection("order")
//                            .addSnapshotListener { orderSnapshot, firebaseFirestoreException ->
//                                if (orderSnapshot != null && !orderSnapshot.isEmpty) {
//                                    orderSnapshot.forEach { order ->
//
//                                    }
//                                }
//                            }
//                    }
//                }
//            }
//    }

    override fun getPaymentList(
        storeId: String,
        documentType: DocumentType
    ): CollectionLiveData<Payment> =
        db.collection(STORES_KEY).document(storeId)
            .collection(PAYMENTS_KEY)
            .asLiveData()

    override fun getPayment(
        storeId: String,
        paymentId: String,
        documentType: DocumentType
    ): DocumentLiveData<Payment> =
        db.collection(STORES_KEY).document(storeId)
            .collection(PAYMENTS_KEY).document(paymentId)
            .asLiveData()
}
