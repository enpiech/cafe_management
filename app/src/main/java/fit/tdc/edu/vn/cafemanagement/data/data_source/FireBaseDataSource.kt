package fit.tdc.edu.vn.cafemanagement.data.data_source

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import fit.tdc.edu.vn.cafemanagement.data.model.*
import fit.tdc.edu.vn.cafemanagement.data.model.Unit
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.collections.HashMap

@Singleton
class FireBaseDataSource @Inject constructor(
    private val storeID: String,
    private val db: FirebaseFirestore
): FireBaseAPI {
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
        private const val BIRTH_KEY    = "birth"
        private const val GENDER_ID_KEY     = "genderId"
        private const val IDENTITY_ID_KEY   = "identityId"
        private const val PHONE_NUMBER_KEY  = "phoneNumber"
        private const val ADDRESS_KEY       = "address"
        private const val ROLE_ID_KEY       = "roleId"
        private const val STORE_ID_KEY      = "storeId"
        private const val USERNAME_KEY      = "username"
        private const val MANAGER_ID_KEY    = "managerId"

        private const val TAG = "FireBase"
        private var categoryMap     = MutableLiveData<HashMap<String, Category>>()
        private var materialMap     = MutableLiveData<HashMap<String, Material>>()
        private var tableMap        = MutableLiveData<HashMap<String, Table>>()
        private var revenueMap      = MutableLiveData<HashMap<String, Revenue>>()
        private var unitMap         = MutableLiveData<HashMap<String, Unit>>()
        private var zoneTypeMap     = MutableLiveData<HashMap<String, ZoneType>>()
        private var zoneMap         = MutableLiveData<HashMap<String, Zone>>()
        private var employeeMap     = MutableLiveData<HashMap<String, Employee>>()
        private var storeMap        = MutableLiveData<HashMap<String, Store>>()
    }

    // CATEGORY
    override fun getCategoryMap(): LiveData<HashMap<String, Category>> {
        if (zoneTypeMap.value == null) {
            loadCategoryMap(storeID)
        }
        return categoryMap
    }

    private fun loadCategoryMap(storeID: String) {
        db.collection(STORES_KEY).document(storeID).collection(CATEGORIES_KEY)
            .addSnapshotListener { categoryCollection, exception ->
                if (exception != null) {
                    Log.w(TAG, "Listen failed", exception)
                    return@addSnapshotListener
                }

                if (categoryCollection != null) {
                    val map = HashMap<String, Category>()
                    for (categoryDoc in categoryCollection) {
                        val category = Category.builder()
                            .id(categoryDoc.id)
                            .name(categoryDoc[NAME_KEY] as String)
                            .build()
                        map[categoryDoc.id] = category
                    }
                    categoryMap.value = map
                }
            }
    }

    // MATERIAL
    override fun getMaterialMap(): LiveData<HashMap<String, Material>> {
        if (materialMap.value == null) {
            loadMaterialMap(storeID)
        }
        return materialMap
    }

    private fun loadMaterialMap(storeID: String) {
        db.collection(STORES_KEY).document(storeID).collection(MATERIALS_KEY)
            .addSnapshotListener { materialCollection, exception ->
                if (exception != null) {
                    Log.w(TAG, "Listen failed", exception)
                    return@addSnapshotListener
                }

                if (materialCollection != null) {
                    val map = HashMap<String, Material>()
                    for (materialDoc in materialCollection) {
                        val material = Material.builder()
                            .id(materialDoc.id)
                            .name(materialDoc[NAME_KEY] as String)
                            .price(materialDoc[PRICE_KEY] as Long)
                            .sellable(materialDoc[SELLABLE_KEY] as Boolean)
                            .unitID(materialDoc[UNIT_ID_KEY] as String)
                            .build()
                        map[materialDoc.id] = material
                    }
                    materialMap.value = map
                }
            }
    }

    // TABLE
    override fun getTableMap(): LiveData<HashMap<String, Table>> {
        if (tableMap.value == null) {
            loadTableMap(storeID)
        }
        return tableMap
    }

    private fun loadTableMap(storeID: String) {
        db.collection(STORES_KEY).document(storeID).collection(TABLES_KEY)
            .addSnapshotListener { tableCollection, exception ->
                if (exception != null) {
                    Log.w(TAG, "Listen failed", exception)
                    return@addSnapshotListener
                }

                if (tableCollection != null) {
                    val map = HashMap<String, Table>()
                    for (tableDoc in tableCollection) {
                        val table = Table.builder()
                            .id(tableDoc.id)
                            .name(tableDoc[NAME_KEY] as String)
                            .stateID(tableDoc[STATE_ID_KEY] as String)
                            .zoneID(tableDoc[ZONE_ID_KEY] as String)
                            .build()
                        map[tableDoc.id] = table
                    }
                    tableMap.value = map
                }
            }
    }

    // REVENUE
    override fun getRevenueMap(): LiveData<HashMap<String, Revenue>> {
        if (revenueMap.value == null) {
            loadRevenueMap(storeID)
        }
        return revenueMap
    }

    private fun loadRevenueMap(storeID: String) {
        db.collection(STORES_KEY).document(storeID).collection(REVENUES_KEY)
            .addSnapshotListener { revenueCollection, exception ->
                if (exception != null) {
                    Log.w(TAG, "Listen failed", exception)
                    return@addSnapshotListener
                }

                if (revenueCollection != null) {
                    val map = HashMap<String, Revenue>()
                    for (revenueDoc in revenueCollection) {
                        val revenue = Revenue.builder()
                            .id(revenueDoc.id)
                            .storeID(storeID)
                            .income(revenueDoc[INCOME_KEY] as Long)
                            .outcome(revenueDoc[OUTCOME_KEY] as Long)
                            .startDate(revenueDoc[DATE_KEY] as Date)
                            .endDate(revenueDoc[DATE_KEY] as Date)
                            .build()
                        map[revenueDoc.id] = revenue
                    }
                    revenueMap.value = map
                }
            }
    }

    // UNIT
    override fun getUnitMap(): LiveData<HashMap<String, Unit>> {
        if (unitMap.value == null) {
            loadUnitMap(storeID)
        }
        return unitMap
    }

    private fun loadUnitMap(storeID: String) {
        db.collection(STORES_KEY).document(storeID).collection(UNITS_KEY)
            .addSnapshotListener { unitCollection, exception ->
                if (exception != null) {
                    Log.w(TAG, "Listen failed", exception)
                    return@addSnapshotListener
                }

                if (unitCollection != null) {
                    val map = HashMap<String, Unit>()
                    for (unitDoc in unitCollection) {
                        val unit = Unit.builder()
                            .id(unitDoc.id)
                            .name(unitDoc[NAME_KEY] as String)
                            .build()
                        map[unitDoc.id] = unit
                    }
                    unitMap.value = map
                }
            }
    }

    // ZONE TYPE
    override fun getZoneTypeMap(): LiveData<HashMap<String, ZoneType>> {
        if (zoneTypeMap.value == null) {
            loadZoneTypeMap(storeID)
        }
        return zoneTypeMap
    }

    private fun loadZoneTypeMap(storeID: String) {
        db.collection(STORES_KEY).document(storeID).collection(ZONE_TYPES_KEY)
            .addSnapshotListener { zoneTypeCollection, exception ->
                if (exception != null) {
                    Log.w(TAG, "Listen failed", exception)
                    return@addSnapshotListener
                }

                if (zoneTypeCollection != null) {
                    val map = HashMap<String, ZoneType>()
                    for (zoneTypeDoc in zoneTypeCollection) {
                        val zoneType = ZoneType.builder()
                            .id(zoneTypeDoc.id)
                            .name(zoneTypeDoc[NAME_KEY] as String)
                            .build()
                        map[zoneTypeDoc.id] = zoneType
                    }
                    zoneTypeMap.value = map
                }
            }
    }

    // ZONE
    override fun getZoneMap(): LiveData<HashMap<String, Zone>> {
        if (zoneMap.value == null) {
            loadZoneMap(storeID)
        }
        return zoneMap
    }

    private fun loadZoneMap(storeID: String) {
        db.collection(STORES_KEY).document(storeID).collection(ZONES_KEY)
            .addSnapshotListener { zoneCollection, exception ->
                if (exception != null) {
                    Log.w(TAG, "Listen failed", exception)
                    return@addSnapshotListener
                }

                if (zoneCollection != null) {
                    val map = HashMap<String, ZoneType>()
                    for (zoneTypeDoc in zoneCollection) {
                        val zoneType = ZoneType.builder()
                            .id(zoneTypeDoc.id)
                            .name(zoneTypeDoc[NAME_KEY] as String)
                            .build()
                        map[zoneTypeDoc.id] = zoneType
                    }
                    zoneTypeMap.value = map
                }
            }
    }

    // EMPLOYEE
    override fun getEmployeeMap(): LiveData<HashMap<String, Employee>> {
        if (employeeMap.value == null) {
            loadEmployeeMap()
        }
        return employeeMap
    }

    private fun loadEmployeeMap() {
        db.collection(EMPLOYEES_KEY)
            .addSnapshotListener { employeeCollection, exception ->
                if (exception != null) {
                    Log.w(TAG, "Listen failed", exception)
                    return@addSnapshotListener
                }

                if (employeeCollection != null) {
                    val map = HashMap<String, Employee>()
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
                        map[employeeDoc.id] = employee
                    }
                    employeeMap.value = map
                }
            }
    }

    // STORE
    override fun getStoreMap(): LiveData<HashMap<String, Store>> {
        if (storeMap.value == null) {
            loadStoreMap()
        }
        return storeMap
    }

    private fun loadStoreMap() {
        db.collection(STORES_KEY)
            .addSnapshotListener { storeCollection, exception ->
                if (exception != null) {
                    Log.w(TAG, "Listen failed", exception)
                    return@addSnapshotListener
                }

                if (storeCollection != null) {
                    Log.d(TAG, "change")
                    val map = HashMap<String, Store>()
                    for (storeDoc in storeCollection) {
                        val store = Store.builder()
                            .id(storeDoc.id)
                            .name(storeDoc[NAME_KEY] as String)
                            .address(storeDoc[ADDRESS_KEY] as String)
                            .managerID(storeDoc[MANAGER_ID_KEY] as String)
                            .build()

                        map[storeDoc.id] = store
                    }
                    storeMap.value = map
                }
            }
    }
}
