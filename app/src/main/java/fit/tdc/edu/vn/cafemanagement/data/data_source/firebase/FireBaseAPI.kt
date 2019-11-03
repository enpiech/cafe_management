package fit.tdc.edu.vn.cafemanagement.data.data_source.firebase

import com.google.firebase.firestore.DocumentReference
import fit.tdc.edu.vn.cafemanagement.data.extension.*
import fit.tdc.edu.vn.cafemanagement.data.model.category.Category
import fit.tdc.edu.vn.cafemanagement.data.model.material.Material
import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.Payment
import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.Revenue
import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.ZoneType
import fit.tdc.edu.vn.cafemanagement.data.model.store.Store
import fit.tdc.edu.vn.cafemanagement.data.model.table.Table
import fit.tdc.edu.vn.cafemanagement.data.model.unit.Unit
import fit.tdc.edu.vn.cafemanagement.data.model.user.User
import fit.tdc.edu.vn.cafemanagement.data.model.zone.Zone

interface FireBaseAPI {
    /**
     * ======== GET LISTS ========
     */
    fun getCategoryList(
        storeId: String,
        documentType: DocumentType
    ): CollectionLiveData<Category>

    fun getMaterialList(
        storeId: String,
        documentType: DocumentType
    ): CollectionLiveData<Material>

    fun getRevenueList(
        storeId: String,
        documentType: DocumentType
    ): CollectionLiveData<Revenue>

    fun getTableList(
        storeId: String,
        documentType: DocumentType
    ): CollectionLiveData<Table>

    fun getUnitList(
        storeId: String,
        documentType: DocumentType
    ): CollectionLiveData<Unit>

    fun getZoneTypeList(
        storeId: String,
        documentType: DocumentType
    ): CollectionLiveData<ZoneType>

    fun getZoneList(
        storeId: String,
        documentType: DocumentType
    ): CollectionLiveData<Zone>

    fun getUserList(
        documentType: DocumentType
    ): CollectionLiveData<User>

    fun getStoreList(
        documentType: DocumentType
    ): CollectionLiveData<Store>

    fun getPaymentListByState(
        storeId: String,
        state: Payment.State,
        documentType: DocumentType
    ): QueryLiveData<Payment>

    fun getAllPaymentList(
        storeId: String,
        documentType: DocumentType
    ): CollectionLiveData<Payment>

    /**
     * =========== GET SPECIFIC DOCUMENT ============
     */
    fun getCategory(
        storeId: String,
        categoryId: String,
        documentType: DocumentType
    ): DocumentLiveData<Category>

    fun getMaterial(
        storeId: String,
        materialId: String,
        documentType: DocumentType
    ): DocumentLiveData<Material>

    fun getRevenue(
        storeId: String,
        revenueId: String,
        documentType: DocumentType
    ): DocumentLiveData<Revenue>

    fun getTable(
        storeId: String,
        tableId: String,
        documentType: DocumentType
    ): DocumentLiveData<Table>

    fun getUnit(
        storeId: String,
        unitId: String,
        documentType: DocumentType
    ): DocumentLiveData<Unit>

    fun getZoneType(
        storeId: String,
        zoneTypeId: String,
        documentType: DocumentType
    ): DocumentLiveData<ZoneType>

    fun getZone(
        storeId: String,
        zoneId: String,
        documentType: DocumentType
    ): DocumentLiveData<Zone>

    fun getUser(
        userId: String,
        documentType: DocumentType
    ): DocumentLiveData<User>

    fun getStore(
        storeId: String,
        documentType: DocumentType
    ): DocumentLiveData<Store>

    fun getPayment(
        storeId: String,
        paymentId: String,
        documentType: DocumentType
    ): DocumentLiveData<Payment>


    /**
     * ========== CREATE DOCUMENT ===========
     */
    fun createCategory(
        storeId: String,
        category: Category
    ): TaskLiveData<DocumentReference>

    fun createMaterial(
        storeId: String,
        material: Material
    ): TaskLiveData<DocumentReference>

    fun createRevenue(
        storeId: String,
        revenue: Revenue
    ): TaskLiveData<DocumentReference>

    fun createTable(
        storeId: String,
        table: Table
    ): TaskLiveData<DocumentReference>

    fun createUnit(
        storeId: String,
        unit: Unit
    ): TaskLiveData<DocumentReference>

    fun createZoneType(
        storeId: String,
        zoneType: ZoneType
    ): TaskLiveData<DocumentReference>

    fun createZone(
        storeId: String,
        zone: Zone
    ): TaskLiveData<DocumentReference>

    fun createUser(
        user: User
    ): TaskLiveData<DocumentReference>

    fun createStore(
        store: Store
    ): TaskLiveData<DocumentReference>


    /**
     * ========== CREATE DOCUMENT ===========
     */
    fun modifyCategory(
        storeId: String,
        category: Category
    ): TaskLiveData<Void>

    fun modifyMaterial(
        storeId: String,
        material: Material
    ): TaskLiveData<Void>

    fun modifyRevenue(
        storeId: String,
        revenue: Revenue
    ): TaskLiveData<Void>

    fun modifyTable(
        storeId: String,
        table: Table
    ): TaskLiveData<Void>

    fun modifyUnit(
        storeId: String,
        unit: Unit
    ): TaskLiveData<Void>

    fun modifyZoneType(
        storeId: String,
        zoneType: ZoneType
    ): TaskLiveData<Void>

    fun modifyZone(
        storeId: String,
        zone: Zone
    ): TaskLiveData<Void>

    fun modifyUser(
        oldUser: User,
        newUser: User
    ): TaskLiveData<Void>

    fun modifyStore(
        oldStore: Store, newStore: Store
    ): TaskLiveData<Void>
    
    
    /**
     * =========== DELETE DOCUMENT ==============
     */
    fun deleteCategory(
        storeId: String,
        categoryId: String
    ): TaskLiveData<Void>

    fun deleteMaterial(
        storeId: String,
        materialID: String
    ): TaskLiveData<Void>

    fun deleteRevenue(
        storeId: String,
        revenueID: String
    ): TaskLiveData<Void>

    fun deleteTable(
        storeId: String,
        tableID: String
    ): TaskLiveData<Void>

    fun deleteUnit(
        storeId: String,
        unitId: String
    ): TaskLiveData<Void>

    fun deleteZoneType(
        storeId: String,
        zoneTypeId: String
    ): TaskLiveData<Void>

    fun deleteZone(
        storeId: String,
        zoneId: String
    ): TaskLiveData<Void>

    fun deleteUser(
        user: User
    ): TaskLiveData<Void>

    fun deleteStore(
        storeId: String
    ): TaskLiveData<Void>
}