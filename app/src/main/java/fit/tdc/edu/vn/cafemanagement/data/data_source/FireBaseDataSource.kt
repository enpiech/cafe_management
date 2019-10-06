package fit.tdc.edu.vn.cafemanagement.data.data_source

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
        private const val EMPLOYEES_KEY     = "employees"
    }

    // CATEGORY

//    fun getEmployeeOfStore(storeId: String) {
//        db.collection("employeeOfStore").document("7MmeTKnmhgOieVehY05S")
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
    ): TaskLiveData<DocumentReference> =
        db.collection(STORES_KEY).document(storeId)
            .collection(CATEGORIES_KEY)
            .add(category)
            .asLiveData()

    override fun deleteCategory(
        storeId: String,
        categoryId: String
    ): TaskLiveData<Void> =
        db.collection(STORES_KEY).document(storeId)
            .collection(CATEGORIES_KEY).document(categoryId)
            .delete()
            .asLiveData()

    // MATERIAL
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

    override fun deleteMaterial(
        storeId: String,
        materialID: String
    ): TaskLiveData<Void> =
        db.collection(STORES_KEY).document(storeId)
            .collection(CATEGORIES_KEY).document(materialID)
            .delete()
            .asLiveData()

    // TABLE
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

    override fun deleteTable(
        storeId: String,
        tableID: String
    ): TaskLiveData<Void> =
        db.collection(STORES_KEY).document(storeId)
            .collection(CATEGORIES_KEY).document(tableID)
            .delete()
            .asLiveData()

    // REVENUE
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

    override fun deleteRevenue(
        storeId: String,
        revenueID: String
    ): TaskLiveData<Void> =
        db.collection(STORES_KEY).document(storeId)
            .collection(CATEGORIES_KEY).document(revenueID)
            .delete()
            .asLiveData()

    // UNIT
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

    override fun deleteUnit(
        storeId: String,
        unitId: String
    ): TaskLiveData<Void> =
        db.collection(STORES_KEY).document(storeId)
            .collection(UNITS_KEY).document(unitId)
            .delete()
            .asLiveData()

    // ZONE TYPE
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

    override fun deleteZoneType(
        storeId: String,
        zoneTypeId: String
    ): TaskLiveData<Void> =
        db.collection(STORES_KEY).document()
            .collection(ZONE_TYPES_KEY).document(zoneTypeId)
            .delete()
            .asLiveData()

    // ZONE
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

    override fun deleteZone(
        storeId: String,
        zoneId: String
    ): TaskLiveData<Void> =
        db.collection(STORES_KEY).document(storeId)
            .collection(ZONES_KEY).document(zoneId)
            .delete()
            .asLiveData()

    // EMPLOYEE
    override fun getEmployeeList(
        documentType: DocumentType
    ): CollectionLiveData<Employee> =
        db.collection(EMPLOYEES_KEY)
            .asLiveData(documentType)

    override fun getEmployee(
        employeeId: String,
        documentType: DocumentType
    ): DocumentLiveData<Employee> =
        db.collection(EMPLOYEES_KEY).document(employeeId)
            .asLiveData()

    override fun createEmployee(
        employee: Employee
    ): TaskLiveData<DocumentReference> =
        db.collection(EMPLOYEES_KEY)
            .add(employee)
            .asLiveData()

    override fun deleteEmployee(
        employeeId: String
    ): TaskLiveData<Void> =
        db.collection(EMPLOYEES_KEY).document(employeeId)
            .delete()
            .asLiveData()

    // STORE
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

    override fun deleteStore(
        storeId: String
    ): TaskLiveData<Void> =
        db.collection(STORES_KEY).document(storeId)
            .delete()
            .asLiveData()
}
