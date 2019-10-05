package fit.tdc.edu.vn.cafemanagement.data.data_source

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import fit.tdc.edu.vn.cafemanagement.data.model.*
import fit.tdc.edu.vn.cafemanagement.data.model.Unit
import java.util.*
import javax.inject.Singleton
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

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

        private const val NAME_KEY          = "name"
        private const val PRICE_KEY         = "price"
        private const val SELLABLE_KEY      = "sellable"
        private const val STATE_ID_KEY      = "stateId"
        private const val ZONE_ID_KEY       = "zoneId"
        private const val UNIT_ID_KEY       = "unitId"
        private const val TYPE_ID_KEY       = "typeId"
        private const val INCOME_KEY        = "income"
        private const val OUTCOME_KEY       = "outcome"
        private const val DATE_KEY          = "date"
        private const val BIRTH_KEY         = "birth"
        private const val GENDER_ID_KEY     = "genderId"
        private const val IDENTITY_ID_KEY   = "identityId"
        private const val PHONE_NUMBER_KEY  = "phoneNumber"
        private const val ADDRESS_KEY       = "address"
        private const val ROLE_ID_KEY       = "roleId"
        private const val STORE_ID_KEY      = "storeId"
        private const val USERNAME_KEY      = "username"
        private const val MANAGER_ID_KEY    = "managerId"

        private const val TAG = "FireBase"
        private var categoryList     = MutableLiveData<ArrayList<Category>>()
        private var materialList     = MutableLiveData<ArrayList<Material>>()
        private var tableList        = MutableLiveData<ArrayList<Table>>()
        private var revenueList      = MutableLiveData<ArrayList<Revenue>>()
        private var unitList         = MutableLiveData<ArrayList<Unit>>()
        private var zoneTypeList     = MutableLiveData<ArrayList<ZoneType>>()
        private var zoneList         = MutableLiveData<ArrayList<Zone>>()
        private var employeeList     = MutableLiveData<ArrayList<Employee>>()
        private var storeList        = MutableLiveData<ArrayList<Store>>()
    }

    // CATEGORY
    override fun getCategoryList(storeId: String): LiveData<ArrayList<Category>> {
        if (zoneTypeList.value == null) {
            loadCategoryList(storeId)
        }
        return categoryList
    }

    private fun loadCategoryList(storeId: String) {
        db.collection(STORES_KEY).document(storeId).collection(CATEGORIES_KEY)
            .addSnapshotListener { categoryCollection, exception ->
                if (exception != null) {
                    Log.w(TAG, "Listen failed", exception)
                    return@addSnapshotListener
                }

                if (categoryCollection != null) {
                    val list = ArrayList<Category>()
                    for (categoryDoc in categoryCollection) {
                        val category = Category.builder()
                            .id(categoryDoc.id)
                            .name(categoryDoc[NAME_KEY] as String)
                            .build()
                        list += category
                    }
                    categoryList.value = list
                }
            }
    }

    override fun getCategory(storeId: String, id: String): LiveData<Category?> {
        val category = MutableLiveData<Category>()
        db.collection(STORES_KEY).document(storeId).collection(CATEGORIES_KEY).document(id)
            .get()
            .addOnSuccessListener { categoryDoc ->
                if (categoryDoc.exists()) {
                    category.value = Category.builder()
                        .id(categoryDoc.id)
                        .name(categoryDoc[NAME_KEY] as String)
                        .build()
                }
            }
        return category
    }

    override fun createCategory(storeId: String, category: Category) {
        db.collection(STORES_KEY).document(storeId).collection(CATEGORIES_KEY)
            .add(category)
    }

    override fun deleteCategory(storeId: String, categoryId: String) {
        db.collection(STORES_KEY).document(storeId).collection(CATEGORIES_KEY).document(categoryId)
            .delete()
    }

    // MATERIAL
    override fun getMaterialList(storeId: String): LiveData<ArrayList<Material>> {
        if (materialList.value == null) {
            loadMaterialList(storeId)
        }
        return materialList
    }

    private fun loadMaterialList(storeId: String) {
        db.collection(STORES_KEY).document(storeId).collection(MATERIALS_KEY)
            .addSnapshotListener { materialCollection, exception ->
                if (exception != null) {
                    Log.w(TAG, "Listen failed", exception)
                    return@addSnapshotListener
                }

                if (materialCollection != null) {
                    val list = ArrayList<Material>()
                    for (materialDoc in materialCollection) {
                        val material = Material.builder()
                            .id(materialDoc.id)
                            .name(materialDoc[NAME_KEY] as String)
                            .price(materialDoc[PRICE_KEY] as Long)
                            .sellable(materialDoc[SELLABLE_KEY] as Boolean)
                            .unitID(materialDoc[UNIT_ID_KEY] as String)
                            .build()
                        list += material
                    }
                    materialList.value = list
                }
            }
    }

    override fun getMaterial(storeId: String, id: String): LiveData<Material?> {
        val material = MutableLiveData<Material>()
        db.collection(STORES_KEY).document(storeId).collection(CATEGORIES_KEY).document(id)
            .get()
            .addOnSuccessListener { materialDoc ->
                if (materialDoc.exists()) {
                    material.value = Material.builder()
                        .id(materialDoc.id)
                        .name(materialDoc[NAME_KEY] as String)
                        .build()
                }
            }
        return material
    }

    override fun createMaterial(storeId: String, material: Material) {
        db.collection(STORES_KEY).document(storeId).collection(CATEGORIES_KEY)
            .add(material)
    }

    override fun deleteMaterial(storeId: String, materialID: String) {
        db.collection(STORES_KEY).document(storeId).collection(CATEGORIES_KEY).document(materialID)
            .delete()
    }

    // TABLE
    override fun getTableList(storeId: String): LiveData<ArrayList<Table>> {
        if (tableList.value == null) {
            loadTableList(storeId)
        }
        return tableList
    }

    private fun loadTableList(storeId: String) {
        db.collection(STORES_KEY).document(storeId).collection(TABLES_KEY)
            .addSnapshotListener { tableCollection, exception ->
                if (exception != null) {
                    Log.w(TAG, "Listen failed", exception)
                    return@addSnapshotListener
                }

                if (tableCollection != null) {
                    val list = ArrayList<Table>()
                    for (tableDoc in tableCollection) {
                        val table = Table.builder()
                            .id(tableDoc.id)
                            .name(tableDoc[NAME_KEY] as String)
                            .stateID(tableDoc[STATE_ID_KEY] as String)
                            .zoneID(tableDoc[ZONE_ID_KEY] as String)
                            .build()
                        list += table
                    }
                    tableList.value = list
                }
            }
    }

    override fun getTable(storeId: String, id: String): LiveData<Table?> {
        val table = MutableLiveData<Table>()
        db.collection(STORES_KEY).document(storeId).collection(CATEGORIES_KEY).document(id)
            .get()
            .addOnSuccessListener { tableDoc ->
                if (tableDoc.exists()) {
                    table.value = Table.builder()
                        .id(tableDoc.id)
                        .zoneID(tableDoc[ZONES_KEY] as String)
                        .stateID(tableDoc[STATE_ID_KEY] as String)
                        .name(tableDoc[NAME_KEY] as String)
                        .build()
                }
            }
        return table
    }

    override fun createTable(storeId: String, table: Table) {
        db.collection(STORES_KEY).document(storeId).collection(CATEGORIES_KEY)
            .add(table)
    }

    override fun deleteTable(storeId: String, tableID: String) {
        db.collection(STORES_KEY).document(storeId).collection(CATEGORIES_KEY).document(tableID)
            .delete()
    }

    // REVENUE
    override fun getRevenueList(storeId: String): LiveData<ArrayList<Revenue>> {
        if (revenueList.value == null) {
            loadRevenueList(storeId)
        }
        return revenueList
    }

    private fun loadRevenueList(storeId: String) {
        db.collection(STORES_KEY).document(storeId).collection(REVENUES_KEY)
            .addSnapshotListener { revenueCollection, exception ->
                if (exception != null) {
                    Log.w(TAG, "Listen failed", exception)
                    return@addSnapshotListener
                }

                if (revenueCollection != null) {
                    val list = ArrayList<Revenue>()
                    for (revenueDoc in revenueCollection) {
                        val revenue = Revenue.builder()
                            .id(revenueDoc.id)
                            .storeID(storeId)
                            .income(revenueDoc[INCOME_KEY] as Long)
                            .outcome(revenueDoc[OUTCOME_KEY] as Long)
                            .startDate(revenueDoc[DATE_KEY] as Date)
                            .endDate(revenueDoc[DATE_KEY] as Date)
                            .build()
                        list += revenue
                    }
                    revenueList.value = list
                }
            }
    }

    override fun getRevenue(storeId: String, id: String): LiveData<Revenue?> {
        val revenue = MutableLiveData<Revenue>()
        db.collection(STORES_KEY).document(storeId).collection(CATEGORIES_KEY).document(id)
            .get()
            .addOnSuccessListener { revenueDoc ->
                if (revenueDoc.exists()) {
                    revenue.value = Revenue.builder()
                        .id(revenueDoc.id)
                        .storeID(revenueDoc[STORE_ID_KEY] as String)
                        .income(revenueDoc[INCOME_KEY] as Long)
                        .outcome(revenueDoc[OUTCOME_KEY] as Long)
                        .startDate(revenueDoc[DATE_KEY] as Date)
                        .endDate(revenueDoc[DATE_KEY] as Date)
                        .build()
                }
            }
        return revenue
    }

    override fun createRevenue(storeId: String, revenue: Revenue) {
        db.collection(STORES_KEY).document(storeId).collection(CATEGORIES_KEY)
            .add(revenue)
    }

    override fun deleteRevenue(storeId: String, revenueID: String) {
        db.collection(STORES_KEY).document(storeId).collection(CATEGORIES_KEY).document(revenueID)
            .delete()
    }

    // UNIT
    override fun getUnitList(storeId: String): LiveData<ArrayList<Unit>> {
        if (unitList.value == null) {
            loadUnitList(storeId)
        }
        return unitList
    }

    private fun loadUnitList(storeId: String) {
        db.collection(STORES_KEY).document(storeId).collection(UNITS_KEY)
            .addSnapshotListener { unitCollection, exception ->
                if (exception != null) {
                    Log.w(TAG, "Listen failed", exception)
                    return@addSnapshotListener
                }

                if (unitCollection != null) {
                    val list = ArrayList<Unit>()
                    for (unitDoc in unitCollection) {
                        val unit = Unit.builder()
                            .id(unitDoc.id)
                            .name(unitDoc[NAME_KEY] as String)
                            .build()
                        list += unit
                    }
                    unitList.value = list
                }
            }
    }

    override fun getUnit(storeId: String, id: String): LiveData<Unit?> {
        val unit = MutableLiveData<Unit>()
        db.collection(STORES_KEY).document(storeId).collection(UNITS_KEY).document(id)
            .get()
            .addOnSuccessListener { unitDoc ->
                if (unitDoc != null) {
                    unit.value = Unit.builder()
                        .id(unitDoc.id)
                        .name(unitDoc[NAME_KEY] as String)
                        .build()
                }
            }
        return unit
    }

    override fun createUnit(storeId: String, unit: Unit) {
        db.collection(STORES_KEY).document(storeId).collection(UNITS_KEY)
            .add(unit)
    }

    override fun deleteUnit(storeId: String, unitId: String) {
        db.collection(STORES_KEY).document(storeId).collection(UNITS_KEY).document(unitId).delete()
    }

    // ZONE TYPE
    override fun getZoneTypeList(storeId: String): LiveData<ArrayList<ZoneType>> {
        if (zoneTypeList.value == null) {
            loadZoneTypeList(storeId)
        }
        return zoneTypeList
    }

    private fun loadZoneTypeList(storeId: String) {
        db.collection(STORES_KEY).document(storeId).collection(ZONE_TYPES_KEY)
            .addSnapshotListener { zoneTypeCollection, exception ->
                if (exception != null) {
                    Log.w(TAG, "Listen failed", exception)
                    return@addSnapshotListener
                }

                if (zoneTypeCollection != null) {
                    val list = ArrayList<ZoneType>()
                    for (zoneTypeDoc in zoneTypeCollection) {
                        val zoneType = ZoneType.builder()
                            .id(zoneTypeDoc.id)
                            .name(zoneTypeDoc[NAME_KEY] as String)
                            .build()
                        list += zoneType
                    }
                    zoneTypeList.value = list
                }
            }
    }

    // ZONE
    override fun getZoneList(storeId: String): LiveData<ArrayList<Zone>> {
        if (zoneList.value == null) {
            loadZoneList(storeId)
        }
        return zoneList
    }

    private fun loadZoneList(storeId: String) {
        db.collection(STORES_KEY).document(storeId).collection(ZONES_KEY)
            .addSnapshotListener { zoneCollection, exception ->
                if (exception != null) {
                    Log.w(TAG, "Listen failed", exception)
                    return@addSnapshotListener
                }

                if (zoneCollection != null) {
                    val list = ArrayList<ZoneType>()
                    for (zoneTypeDoc in zoneCollection) {
                        val zoneType = ZoneType.builder()
                            .id(zoneTypeDoc.id)
                            .name(zoneTypeDoc[NAME_KEY] as String)
                            .build()
                        list += zoneType
                    }
                    zoneTypeList.value = list
                }
            }
    }

    override fun getZone( storeId: String, id: String): LiveData<Zone?> {
        val zone = MutableLiveData<Zone>()
        db.collection(STORES_KEY).document(storeId).collection(ZONES_KEY).document(id)
            .get()
            .addOnSuccessListener { zoneDoc ->
                if (zoneDoc != null) {
                    zone.value = Zone.builder()
                        .id(zoneDoc.id)
                        .name(zoneDoc[NAME_KEY] as String)
                        .build()
                }
            }
        return zone
    }

    override fun createZone(storeId: String, zone: Zone) {
        db.collection(STORES_KEY).document(storeId).collection(ZONES_KEY)
            .add(zone)
    }

    override fun deleteZone(storeId: String, zoneId: String) {
        db.collection(STORES_KEY).document(storeId).collection(ZONES_KEY).document(zoneId)
            .delete()
    }

    // EMPLOYEE
    override fun getEmployeeList(): LiveData<ArrayList<Employee>> {
        if (employeeList.value == null) {
            loadEmployeeList()
        }
        return employeeList
    }

    private fun loadEmployeeList() {
        db.collection(EMPLOYEES_KEY)
            .addSnapshotListener { employeeCollection, exception ->
                if (exception != null) {
                    Log.w(TAG, "Listen failed", exception)
                    return@addSnapshotListener
                }

                if (employeeCollection != null) {
                    val list = ArrayList<Employee>()
                    for (employeeDoc in employeeCollection) {
                        val employee = Employee.builder()
                            .id(employeeDoc.id)
                            .name(employeeDoc[NAME_KEY] as String)
                            .birthDay(employeeDoc[BIRTH_KEY] as Date)
                            .gender(Gender.valueOf(employeeDoc[GENDER_ID_KEY] as String))
                            .identityID(employeeDoc[IDENTITY_ID_KEY] as String)
                            .phoneNumber(employeeDoc[PHONE_NUMBER_KEY] as String)
                            .address(employeeDoc[ADDRESS_KEY] as String)
                            .roleID(employeeDoc[ROLE_ID_KEY] as String)
                            .storeID(employeeDoc[STORE_ID_KEY] as String)
                            .userName(employeeDoc[USERNAME_KEY] as String)
                            .build()
                        list += employee
                    }
                    employeeList.value = list
                }
            }
    }

    // STORE
    override fun getStoreList(): LiveData<ArrayList<Store>> {
        if (storeList.value == null) {
            loadStoreList()
        }
        return storeList
    }

    private fun loadStoreList() {
        db.collection(STORES_KEY)
            .addSnapshotListener { storeCollection, exception ->
                if (exception != null) {
                    Log.w(TAG, "Listen failed", exception)
                    return@addSnapshotListener
                }

                if (storeCollection != null) {
                    Log.d(TAG, "change")
                    val list = ArrayList<Store>()
                    for (storeDoc in storeCollection) {
                        val store = Store.builder()
                            .id(storeDoc.id)
                            .name(storeDoc[NAME_KEY] as String)
                            .address(storeDoc[ADDRESS_KEY] as String)
                            .managerID(storeDoc[MANAGER_ID_KEY] as String)
                            .build()

                        list += store
                    }
                    storeList.value = list
                }
            }
    }
}
