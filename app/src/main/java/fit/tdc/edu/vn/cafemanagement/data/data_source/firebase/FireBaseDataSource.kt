package fit.tdc.edu.vn.cafemanagement.data.data_source.firebase

import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Source
import com.google.firebase.firestore.WriteBatch
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import fit.tdc.edu.vn.cafemanagement.data.extension.*
import fit.tdc.edu.vn.cafemanagement.data.model.category.Category
import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.Payment
import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.Revenue
import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.ZoneType
import fit.tdc.edu.vn.cafemanagement.data.model.material.Material
import fit.tdc.edu.vn.cafemanagement.data.model.order.Order
import fit.tdc.edu.vn.cafemanagement.data.model.store.Store
import fit.tdc.edu.vn.cafemanagement.data.model.table.Table
import fit.tdc.edu.vn.cafemanagement.data.model.unit.Unit
import fit.tdc.edu.vn.cafemanagement.data.model.user.User
import fit.tdc.edu.vn.cafemanagement.data.model.ware_house.WareHouse
import fit.tdc.edu.vn.cafemanagement.data.model.zone.Zone
import fit.tdc.edu.vn.cafemanagement.util.Constants.Companion.CATEGORIES_KEY
import fit.tdc.edu.vn.cafemanagement.util.Constants.Companion.CATEGORY_ID_KEY
import fit.tdc.edu.vn.cafemanagement.util.Constants.Companion.CATEGORY_NAME_KEY
import fit.tdc.edu.vn.cafemanagement.util.Constants.Companion.MANAGER_ID_KEY
import fit.tdc.edu.vn.cafemanagement.util.Constants.Companion.MANAGER_NAME_KEY
import fit.tdc.edu.vn.cafemanagement.util.Constants.Companion.MATERIALS_KEY
import fit.tdc.edu.vn.cafemanagement.util.Constants.Companion.ORDERS_KEY
import fit.tdc.edu.vn.cafemanagement.util.Constants.Companion.ORDER_STATE_KEY
import fit.tdc.edu.vn.cafemanagement.util.Constants.Companion.PAYMENTS_KEY
import fit.tdc.edu.vn.cafemanagement.util.Constants.Companion.PAYMENT_ID_KEY
import fit.tdc.edu.vn.cafemanagement.util.Constants.Companion.PAYMENT_STATE_KEY
import fit.tdc.edu.vn.cafemanagement.util.Constants.Companion.REVENUES_KEY
import fit.tdc.edu.vn.cafemanagement.util.Constants.Companion.STORES_KEY
import fit.tdc.edu.vn.cafemanagement.util.Constants.Companion.STORE_ID_KEY
import fit.tdc.edu.vn.cafemanagement.util.Constants.Companion.STORE_NAME_KEY
import fit.tdc.edu.vn.cafemanagement.util.Constants.Companion.TABLES_KEY
import fit.tdc.edu.vn.cafemanagement.util.Constants.Companion.TABLE_STATE_KEY
import fit.tdc.edu.vn.cafemanagement.util.Constants.Companion.UNITS_KEY
import fit.tdc.edu.vn.cafemanagement.util.Constants.Companion.UNIT_ID_KEY
import fit.tdc.edu.vn.cafemanagement.util.Constants.Companion.UNIT_NAME_KEY
import fit.tdc.edu.vn.cafemanagement.util.Constants.Companion.USERS_KEY
import fit.tdc.edu.vn.cafemanagement.util.Constants.Companion.WAREHOUSES_KEY
import fit.tdc.edu.vn.cafemanagement.util.Constants.Companion.ZONES_KEY
import fit.tdc.edu.vn.cafemanagement.util.Constants.Companion.ZONE_ID_KEY
import fit.tdc.edu.vn.cafemanagement.util.Constants.Companion.ZONE_NAME_KEY
import fit.tdc.edu.vn.cafemanagement.util.Constants.Companion.ZONE_TYPES_KEY
import javax.inject.Singleton

@Singleton
class FireBaseDataSource : FireBaseAPI {
    override fun modifyWareHouse(storeId: String, WwreHouse: WareHouse): TaskLiveData<Void> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        //TODO: Chỉnh sửa warehouse chưa được sử dụng
    }

    /**
     *
     * ==========  WareHouse  ============
     */
    override fun getWareHouseList(
        storeId: String,
        documentType: DocumentType
    ): CollectionLiveData<WareHouse> =
        db.collection(STORES_KEY).document(storeId)
            .collection(WAREHOUSES_KEY)
            .asLiveData()

    override fun getWareHouse(
        storeId: String,
        wareHouseId: String,
        documentType: DocumentType
    ): DocumentLiveData<WareHouse> =
        db.collection(STORES_KEY).document(storeId)
            .collection(WAREHOUSES_KEY).document(wareHouseId)
            .asLiveData()

    override fun createWareHouse(
        storeId: String,
        wareHouse: WareHouse
    ): TaskLiveData<DocumentReference> =
        db.collection(STORES_KEY).document(storeId)
            .collection(WAREHOUSES_KEY)
            .add(wareHouse)
            .asLiveData()

    override fun deleteWareHouse(storeId: String, wareHouseId: String): TaskLiveData<Void> =
        db.collection(STORES_KEY).document(storeId)
            .collection(WAREHOUSES_KEY).document(wareHouseId)
            .delete()
            .asLiveData()

    private val db: FirebaseFirestore by lazy {
        Firebase.firestore
    }

    /**
     *
     * ==========  CATEGORY  ============
     */

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
        val categoryCollectionReference =
            db.collection(STORES_KEY).document(storeId).collection(CATEGORIES_KEY)
        val materialCollectionReference =
            db.collection(STORES_KEY).document(storeId).collection(MATERIALS_KEY)
        materialCollectionReference.whereEqualTo(CATEGORY_ID_KEY, category.id)
            .get()
            .addOnSuccessListener { querySnapshot ->
                db.runBatch { batch ->
                    querySnapshot.documents
                        .map { documentSnapshot -> documentSnapshot.reference }
                        .forEach { documentReference: DocumentReference ->
                            batch.update(
                                documentReference,
                                CATEGORY_NAME_KEY,
                                category.name
                            )
                        }
                }
            }
        val categoryRef = categoryCollectionReference.document(category.id)
        return categoryRef.set(category).asLiveData()
    }

    override fun deleteCategory(
        storeId: String,
        categoryId: String
    ): TaskLiveData<Void> {
        val categoryCollectionReference =
            db.collection(STORES_KEY).document(storeId).collection(CATEGORIES_KEY)
        val materialCollectionReference =
            db.collection(STORES_KEY).document(storeId).collection(MATERIALS_KEY)
        materialCollectionReference.whereEqualTo(CATEGORY_ID_KEY, categoryId)
            .get()
            .addOnSuccessListener { querySnapshot ->
                db.runBatch { batch ->
                    querySnapshot.documents
                        .map { documentSnapshot -> documentSnapshot.reference }
                        .forEach { documentReference: DocumentReference ->
                            batch.update(
                                documentReference,
                                CATEGORY_NAME_KEY,
                                null
                            )
                        }
                }
            }
        return categoryCollectionReference.document(categoryId).delete().asLiveData()
    }

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

    override fun modifyMaterial(
        storeId: String,
        material: Material
    ): TaskLiveData<Void> {
        return db.collection(STORES_KEY).document(storeId)
            .collection(MATERIALS_KEY).document(material.id)
            .set(material)
            .asLiveData()
    }

    override fun deleteMaterial(
        storeId: String,
        materialID: String
    ): TaskLiveData<Void> =
        db.collection(STORES_KEY).document(storeId)
            .collection(MATERIALS_KEY).document(materialID)
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
            .collection(TABLES_KEY).document(tableId)
            .asLiveData(documentType)

    override fun createTable(
        storeId: String,
        table: Table
    ): TaskLiveData<DocumentReference> =
        db.collection(STORES_KEY).document(storeId)
            .collection(TABLES_KEY)
            .add(table)
            .asLiveData()

    override fun modifyTable(storeId: String, table: Table): TaskLiveData<Void> {
        return db.collection(STORES_KEY).document(storeId)
            .collection(TABLES_KEY).document(table.id)
            .set(table)
            .asLiveData()
    }

    override fun deleteTable(
        storeId: String,
        tableID: String
    ): TaskLiveData<Void> =
        db.collection(STORES_KEY).document(storeId)
            .collection(TABLES_KEY).document(tableID)
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
    ): DocumentLiveData<Revenue> =
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
        val unitCollectionReference =
            db.collection(STORES_KEY).document(storeId).collection(UNITS_KEY)
        val materialCollectionReference =
            db.collection(STORES_KEY).document(storeId).collection(MATERIALS_KEY)
        materialCollectionReference.whereEqualTo(UNIT_ID_KEY, unit.id)
            .get()
            .addOnSuccessListener { querySnapshot ->
                db.runBatch { batch ->
                    querySnapshot.documents
                        .map { documentSnapshot -> documentSnapshot.reference }
                        .forEach { documentReference: DocumentReference ->
                            batch.update(
                                documentReference,
                                UNIT_NAME_KEY,
                                unit.name
                            )
                        }
                }

            }
        val unitDocumentReference = unitCollectionReference.document(unit.id)
        return unitDocumentReference.set(unit).asLiveData()
    }

    override fun deleteUnit(
        storeId: String,
        unitId: String
    ): TaskLiveData<Void> {
        val unitCollectionReference =
            db.collection(STORES_KEY).document(storeId).collection(UNITS_KEY)
        val materialCollectionReference =
            db.collection(STORES_KEY).document(storeId).collection(MATERIALS_KEY)
        materialCollectionReference.whereEqualTo(UNIT_ID_KEY, unitId)
            .get()
            .addOnSuccessListener { querySnapshot ->
                db.runBatch { batch ->
                    querySnapshot.documents
                        .map { documentSnapshot -> documentSnapshot.reference }
                        .forEach { documentReference: DocumentReference ->
                            batch.update(documentReference, UNIT_NAME_KEY, null)
                            batch.update(documentReference, UNIT_ID_KEY, null)
                        }
                }
            }
        return unitCollectionReference.document(unitId).delete().asLiveData()
    }


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
        val zoneCollectionReference =
            db.collection(STORES_KEY).document(storeId).collection(ZONES_KEY)
        val tableCollectionReference =
            db.collection(STORES_KEY).document(storeId).collection(TABLES_KEY)
        tableCollectionReference.whereEqualTo(ZONE_ID_KEY, zone.id)
            .get()
            .addOnSuccessListener { querySnapshot ->
                db.runBatch { batch ->
                    querySnapshot.documents
                        .map { documentSnapshot -> documentSnapshot.reference }
                        .forEach { documentReference: DocumentReference ->
                            batch.update(
                                documentReference,
                                ZONE_NAME_KEY,
                                zone.name
                            )
                        }
                }
            }
        val zoneDocumentReference = zoneCollectionReference.document(zone.id)
        return zoneDocumentReference.set(zone).asLiveData()
    }

    override fun deleteZone(
        storeId: String,
        zoneId: String
    ): TaskLiveData<Void> {
        val zoneDocumentReference =
            db.collection(STORES_KEY).document(storeId).collection(ZONES_KEY).document(zoneId)
        val tableCollectionReference =
            db.collection(STORES_KEY).document(storeId).collection(TABLES_KEY)
        tableCollectionReference.whereEqualTo(ZONE_ID_KEY, zoneId)
            .get()
            .addOnSuccessListener { querySnapshot ->
                db.runBatch { batch ->
                    querySnapshot.documents
                        .map { documentSnapshot -> documentSnapshot.reference }
                        .forEach { documentReference: DocumentReference ->
                            batch.update(
                                documentReference,
                                ZONE_NAME_KEY,
                                null
                            )
                        }
                }
            }

        return zoneDocumentReference.delete().asLiveData()
    }

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
        oldUser: User,
        newUser: User
    ): TaskLiveData<Void> {
        var oldStoreRef: DocumentReference? = null
        oldUser.storeId?.let {
            oldStoreRef = db.collection(STORES_KEY).document(it)
        }
        var newStoreRef: DocumentReference? = null
        newUser.storeId?.let {
            newStoreRef = db.collection(STORES_KEY).document(it)
        }

        return db.runBatch { batch ->
            if (newUser.role == User.Role.STORE_MANAGER) {
                if (oldUser.storeId.isNullOrBlank() && !newUser.storeId.isNullOrBlank()) {
                    newStoreRef?.let {
                        batch.update(it, MANAGER_ID_KEY, newUser.id)
                        batch.update(newStoreRef!!, MANAGER_NAME_KEY, newUser.name)
                    }
                } else if (!oldUser.storeId.isNullOrBlank() && newUser.storeId.isNullOrBlank()) {
                    oldStoreRef?.let {
                        batch.update(it, MANAGER_ID_KEY, null)
                        batch.update(oldStoreRef!!, MANAGER_NAME_KEY, null)
                    }
                } else if (!oldUser.storeId.isNullOrBlank() && !newUser.storeId.isNullOrBlank()) {
                    batch.update(oldStoreRef!!, MANAGER_ID_KEY, null)
                    batch.update(oldStoreRef!!, MANAGER_NAME_KEY, null)
                    batch.update(newStoreRef!!, MANAGER_ID_KEY, newUser.id)
                    batch.update(newStoreRef!!, MANAGER_NAME_KEY, newUser.name)
                }
            }
            val userRef = db.collection(USERS_KEY).document(oldUser.id)
            batch.set(userRef, newUser)
        }.asLiveData()
    }

    override fun deleteUser(
        user: User
    ): TaskLiveData<Void> {
        return db.runBatch { batch ->
            if (user.role == User.Role.STORE_MANAGER && !user.storeId.isNullOrBlank()) {
                val storeReference = db.collection(STORES_KEY).document(user.storeId!!)
                batch.update(storeReference, MANAGER_ID_KEY, null)
                batch.update(storeReference, MANAGER_NAME_KEY, null)
            }
            batch.delete(db.collection(USERS_KEY).document(user.id))
        }.asLiveData()
    }

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
    ): TaskLiveData<DocumentReference> {
        return db.collection(STORES_KEY)
            .add(store)
            .addOnSuccessListener { ref ->
                store.managerId?.let { managerId ->
                    db.runBatch { batch ->
                        val managerRef = db.collection(USERS_KEY).document(managerId)
                        val field = STORE_ID_KEY
                        val data = ref.id
                        batch.update(managerRef, field, data)
                    }
                }
            }
            .asLiveData()
    }


    override fun modifyStore(
        oldStore: Store, newStore: Store
    ): TaskLiveData<Void> {
        var oldManagerRef: DocumentReference? = null
        oldStore.managerId?.let {
            oldManagerRef = db.collection(USERS_KEY).document(it)
        }
        var newManagerRef: DocumentReference? = null
        newStore.managerId?.let {
            newManagerRef = db.collection(USERS_KEY).document(it)
        }
        return db.runBatch { writeBatch: WriteBatch ->
            if (oldStore.managerId.isNullOrBlank() && !newStore.managerId.isNullOrBlank()) {
                newManagerRef?.let {
                    writeBatch.update(it, STORE_ID_KEY, newStore.id)
                    writeBatch.update(it, STORE_NAME_KEY, newStore.id)
                }
            } else if (!oldStore.managerId.isNullOrBlank() && newStore.managerId.isNullOrBlank()) {
                oldManagerRef?.let {
                    writeBatch.update(it, STORE_ID_KEY, null)
                    writeBatch.update(it, STORE_NAME_KEY, null)
                }
            } else if (!oldStore.managerId.isNullOrBlank() && !newStore.managerId.isNullOrBlank()) {
                writeBatch.update(oldManagerRef!!, STORE_ID_KEY, null)
                writeBatch.update(oldManagerRef!!, STORE_NAME_KEY, null)
                writeBatch.update(newManagerRef!!, STORE_ID_KEY, newStore.id)
                writeBatch.update(newManagerRef!!, STORE_NAME_KEY, newStore.name)
            }
            writeBatch.set(db.collection(STORES_KEY).document(newStore.id), newStore)
        }.asLiveData()
    }

    override fun deleteStore(
        storeId: String
    ): TaskLiveData<Void> {
        val refs: ArrayList<DocumentReference> = arrayListOf()
        db.collection(USERS_KEY)
            .whereEqualTo(STORE_ID_KEY, storeId)
            .get(Source.CACHE)
            .addOnSuccessListener { querySnap ->
                db.runBatch { batch ->
                    querySnap.documents.forEach { docSnap ->
                        val ref = docSnap.reference
                        refs.add(ref)
                    }
                    refs.forEach {
                        batch.update(it, STORE_ID_KEY, null)
                    }
                }
            }
        return db.collection(STORES_KEY).document(storeId).delete().asLiveData()
    }


    /**
     *
     * ==========  PAYMENT  ============
     */
    override fun getPaymentListByState(
        storeId: String,
        state: Payment.State,
        documentType: DocumentType
    ): QueryLiveData<Payment> =
        db.collection(STORES_KEY).document(storeId)
            .collection(PAYMENTS_KEY)
            .whereEqualTo("state", state)
            .asLiveData()

    override fun getAllPaymentList(
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

    override fun getWaiterOrders(
        storeId: String,
        paymentId: String,
        documentType: DocumentType
    ): QueryLiveData<Order> {
        return db.collection(STORES_KEY).document(storeId)
            .collection(ORDERS_KEY).whereEqualTo(PAYMENT_ID_KEY, paymentId)
            .asLiveData()
    }

    override fun getOrder(
        storeId: String,
        orderId: String,
        documentType: DocumentType
    ): DocumentLiveData<Order> {
        return db.collection(STORES_KEY).document(storeId)
            .collection(ORDERS_KEY).document(orderId)
            .asLiveData()
    }

    override fun getChefOrders(
        storeId: String,
        documentType: DocumentType
    ): QueryLiveData<Order> =
        db.collection(STORES_KEY).document(storeId)
            .collection(ORDERS_KEY).whereEqualTo(ORDER_STATE_KEY, Order.State.DOING)
            .asLiveData()

    override fun createOrder(
        storeId: String,
        order: Order
    ): TaskLiveData<DocumentReference> {
        val orderCollectionReference =
            db.collection(STORES_KEY).document(storeId).collection(ORDERS_KEY)
        return orderCollectionReference.add(order).asLiveData()
    }

    override fun addOrderToPayment(
        storeId: String,
        orders: List<Order>
    ) {
        val orderCollectionReference =
            db.collection(STORES_KEY).document(storeId).collection(ORDERS_KEY)
        orders.forEachIndexed { _, order ->
            orderCollectionReference.add(order)
        }
    }

    override fun completeOrder(storeId: String, orderId: String): TaskLiveData<Void> =
        db.collection(STORES_KEY).document(storeId)
            .collection(ORDERS_KEY).document(orderId)
            .update(ORDER_STATE_KEY, Order.State.DONE)
            .asLiveData()

    override fun checkout(storeId: String, payment: Payment): TaskLiveData<Void> {
        val paymentRef = db.collection(STORES_KEY).document(storeId).collection(PAYMENTS_KEY)
            .document(payment.id)
        val tableRef = db.collection(STORES_KEY).document(storeId).collection(TABLES_KEY)
            .document(payment.tableId!!)
        db.collection(STORES_KEY).document(storeId).collection(ORDERS_KEY)
            .whereEqualTo(PAYMENT_ID_KEY, payment.id)
            .whereGreaterThan(ORDER_STATE_KEY, Order.State.CLOSE)
            .get()
            .addOnSuccessListener { query ->
                db.runTransaction {
                    query.documents.forEach { doc ->
                        it.update(doc.reference, ORDER_STATE_KEY, Order.State.CLOSE)
                    }
                }
            }
        return db.runBatch {
            it.update(paymentRef, PAYMENT_STATE_KEY, Payment.State.PAID)
            it.update(tableRef, TABLE_STATE_KEY, Table.State.FREE)
            it.update(tableRef, PAYMENT_ID_KEY, null)
        }.asLiveData()
    }


    override fun createPayment(storeId: String, payment: Payment): TaskLiveData<DocumentReference> {
        return db.collection(STORES_KEY).document(storeId)
            .collection(PAYMENTS_KEY)
            .add(payment)
            .addOnSuccessListener { docRef ->
                payment.orderList.forEach { order ->
                    val orderRef = db.collection(STORES_KEY).document(storeId).collection(
                        ORDERS_KEY
                    )
                    orderRef.add(order.apply {
                        paymentId = docRef.id
                    })
                }
                val tableRef = db.collection(STORES_KEY).document(storeId)
                    .collection(TABLES_KEY).document(payment.tableId!!)
                db.runBatch {
                    it.update(tableRef, PAYMENT_ID_KEY, docRef.id)
                    it.update(tableRef, TABLE_STATE_KEY, Table.State.BOOKED)
                }
            }.asLiveData()
    }
}
