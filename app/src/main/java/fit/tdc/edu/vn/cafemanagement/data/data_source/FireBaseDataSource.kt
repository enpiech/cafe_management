package fit.tdc.edu.vn.cafemanagement.data.data_source

import com.google.firebase.firestore.FirebaseFirestore
import fit.tdc.edu.vn.cafemanagement.data.extension.CollectionLiveData
import fit.tdc.edu.vn.cafemanagement.data.extension.DocumentType
import fit.tdc.edu.vn.cafemanagement.data.extension.DocumentLiveData
import fit.tdc.edu.vn.cafemanagement.data.extension.asLiveData
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

    override fun getCategoryList(
        storeId: String,
        documentType: DocumentType
    ): CollectionLiveData<Category> =
        db.collection(STORES_KEY).document(storeId).collection(CATEGORIES_KEY).asLiveData(documentType)

    override fun getCategory(storeId: String, id: String): DocumentLiveData<Category> =
        db.collection(STORES_KEY).document(storeId).collection(CATEGORIES_KEY).document(id).asLiveData()

    override fun createCategory(storeId: String, category: Category) {
        db.collection(STORES_KEY).document(storeId).collection(CATEGORIES_KEY)
            .add(category)
    }

    override fun deleteCategory(storeId: String, categoryId: String) {
        db.collection(STORES_KEY).document(storeId).collection(CATEGORIES_KEY).document(categoryId)
            .delete()
    }

    // MATERIAL
    override fun getMaterialList(
        storeId: String,
        documentType: DocumentType
    ): CollectionLiveData<Material> =
        db.collection(STORES_KEY).document(storeId).collection(MATERIALS_KEY).asLiveData(documentType)

    override fun getMaterial(storeId: String, id: String): DocumentLiveData<Material> =
        db.collection(STORES_KEY).document(storeId).collection(CATEGORIES_KEY).document(id).asLiveData()

    override fun createMaterial(storeId: String, material: Material) {
        db.collection(STORES_KEY).document(storeId).collection(CATEGORIES_KEY)
            .add(material)
    }

    override fun deleteMaterial(storeId: String, materialID: String) {
        db.collection(STORES_KEY).document(storeId).collection(CATEGORIES_KEY).document(materialID)
            .delete()
    }

    // TABLE
    override fun getTableList(
        storeId: String,
        documentType: DocumentType
    ): CollectionLiveData<Table> =
        db.collection(STORES_KEY).document(storeId).collection(TABLES_KEY).asLiveData(documentType)


    override fun getTable(storeId: String, id: String): DocumentLiveData<Table> =
        db.collection(STORES_KEY).document(storeId).collection(CATEGORIES_KEY).document(id).asLiveData()

    override fun createTable(storeId: String, table: Table) {
        db.collection(STORES_KEY).document(storeId).collection(CATEGORIES_KEY)
            .add(table)
    }

    override fun deleteTable(storeId: String, tableID: String) {
        db.collection(STORES_KEY).document(storeId).collection(CATEGORIES_KEY).document(tableID)
            .delete()
    }

    // REVENUE
    override fun getRevenueList(
        storeId: String,
        documentType: DocumentType
    ): CollectionLiveData<Revenue> =
        db.collection(STORES_KEY).document(storeId).collection(REVENUES_KEY).asLiveData(documentType)

    override fun getRevenue(storeId: String, id: String): DocumentLiveData<Revenue> =
        db.collection(STORES_KEY).document(storeId).collection(CATEGORIES_KEY).document(id).asLiveData()

    override fun createRevenue(storeId: String, revenue: Revenue) {
        db.collection(STORES_KEY).document(storeId).collection(CATEGORIES_KEY)
            .add(revenue)
    }

    override fun deleteRevenue(storeId: String, revenueID: String) {
        db.collection(STORES_KEY).document(storeId).collection(CATEGORIES_KEY).document(revenueID)
            .delete()
    }

    // UNIT
    override fun getUnitList(
        storeId: String,
        documentType: DocumentType
    ): CollectionLiveData<Unit> = db.collection(STORES_KEY).document(storeId).collection(UNITS_KEY).asLiveData(documentType)

    override fun getUnit(storeId: String, id: String): DocumentLiveData<Unit> =
        db.collection(STORES_KEY).document(storeId).collection(UNITS_KEY).document(id).asLiveData()

    override fun createUnit(storeId: String, unit: Unit) {
        db.collection(STORES_KEY).document(storeId).collection(UNITS_KEY)
            .add(unit)
    }

    override fun deleteUnit(storeId: String, unitId: String) {
        db.collection(STORES_KEY).document(storeId).collection(UNITS_KEY).document(unitId).delete()
    }

    // ZONE TYPE
    override fun getZoneTypeList(
        storeId: String,
        documentType: DocumentType
    ): CollectionLiveData<ZoneType> =
        db.collection(STORES_KEY).document(storeId).collection(ZONE_TYPES_KEY).asLiveData(documentType)

    // ZONE
    override fun getZoneList(
        storeId: String,
        documentType: DocumentType
    ): CollectionLiveData<Zone> =
        db.collection(STORES_KEY).document(storeId).collection(ZONES_KEY).asLiveData(documentType)

    override fun getZone( storeId: String, id: String): DocumentLiveData<Zone> =
        db.collection(STORES_KEY).document(storeId).collection(ZONES_KEY).document(id).asLiveData()

    override fun createZone(storeId: String, zone: Zone) {
        db.collection(STORES_KEY).document(storeId).collection(ZONES_KEY)
            .add(zone)
    }

    override fun deleteZone(storeId: String, zoneId: String) {
        db.collection(STORES_KEY).document(storeId).collection(ZONES_KEY).document(zoneId)
            .delete()
    }

    // EMPLOYEE
    override fun getEmployeeList(documentType: DocumentType): CollectionLiveData<Employee> =
        db.collection(EMPLOYEES_KEY).asLiveData(documentType)

    // STORE
    override fun getStoreList(documentType: DocumentType): CollectionLiveData<Store> =
        db.collection(STORES_KEY).asLiveData(documentType)
}
