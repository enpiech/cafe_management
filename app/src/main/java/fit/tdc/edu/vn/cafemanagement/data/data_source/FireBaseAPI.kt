package fit.tdc.edu.vn.cafemanagement.data.data_source

import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.QuerySnapshot
import fit.tdc.edu.vn.cafemanagement.data.extension.*
import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.*
import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.Unit

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

    fun getEmployeeList(
        documentType: DocumentType
    ): CollectionLiveData<Employee>

    fun getStoreList(
        documentType: DocumentType
    ): CollectionLiveData<Store>


    /**
     * ========== FETCH ============
     */
    fun fetchCategoryList(
        storeId: String
    ): TaskLiveData<QuerySnapshot>


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

    fun getEmployee(
        id: String,
        documentType: DocumentType
    ): DocumentLiveData<Employee>

    fun getStore(
        id: String,
        documentType: DocumentType
    ): DocumentLiveData<Store>


    /**
     * ========== CREATE DOCUMENT ===========
     */
    fun createCategory(
        storeId: String,
        category: Category
    ): TaskLiveData<DocumentReference>

    fun createMaterial(
        storeId: String,
        material: Material): TaskLiveData<DocumentReference>

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

    fun createEmployee(
        employee: Employee
    ): TaskLiveData<DocumentReference>

    fun createStore(
        store: Store
    ): TaskLiveData<DocumentReference>

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

    fun deleteEmployee(
        employeeId: String
    ): TaskLiveData<Void>

    fun deleteStore(
        storeId: String
    ): TaskLiveData<Void>
}