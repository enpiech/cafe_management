package fit.tdc.edu.vn.cafemanagement.data.data_source

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.google.firebase.firestore.FirebaseFirestore
import fit.tdc.edu.vn.cafemanagement.data.model.*
import fit.tdc.edu.vn.cafemanagement.data.model.Unit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FireBaseDataSource @Inject constructor(
    private val storeID: String,
    private val db: FirebaseFirestore
): FireBaseAPI {
    companion object {
        private const val STORES_KEY = "stores"
        private const val CATEGORIES_KEY = "categories"
        private const val MATERIALS_KEY = "materials"
        private const val TABLES_KEY = "tables"
        private const val UNITS_KEY = "units"
        private const val ZONE_TYPES_KEY = "zoneTypes"
        private const val ZONES_KEY = "zones"

        private const val NAME_KEY = "name"
        private const val PRICE_KEY = "price"
        private const val SELLABLE_KEY = "sellable"
        private const val STATE_ID_KEY = "stateId"
        private const val ZONE_ID_KEY = "zoneId"
        private const val UNIT_ID_KEY = "unitId"
        private const val TYPE_ID_KEY = "typeId"

        private const val TAG = "FireBase"
        private var categoryMap = MutableLiveData<HashMap<String, Category>>()
        private var materialMap = MutableLiveData<HashMap<String, Material>>()
        private var tableMap = MutableLiveData<HashMap<String, Table>>()
        private var unitMap = MutableLiveData<HashMap<String, Unit>>()
        private var zoneTypeMap = MutableLiveData<HashMap<String, ZoneType>>()
        private var zoneMap = MutableLiveData<HashMap<String, Zone>>()
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
                        category
                        map[categoryDoc.id] = category
                        Log.d(TAG, category.toString())
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

    override fun getZoneMap(): LiveData<HashMap<String, Zone>> {
        if (zoneMap.value == null) {
            loadZoneMap(storeID)
        }
        return zoneMap
    }

    private fun loadZoneMap(storeID: String) {
        db.collection(STORES_KEY).document(storeID).collection(ZONES_KEY)
            .get()
            .addOnSuccessListener { zoneCollection ->
                val map = HashMap<String, Zone>()
                for (zoneDoc in zoneCollection) {
                    val zone = Zone.builder()
                        .id(zoneDoc.id)
                        .name(zoneDoc[NAME_KEY] as String)
                        .zoneTypeID(zoneDoc[TYPE_ID_KEY] as String)
                        .build()
                    map[zoneDoc.id] = zone
                }
                zoneMap.value = map
            }
    }

}
