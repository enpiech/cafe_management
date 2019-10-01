package fit.tdc.edu.vn.cafemanagement.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations
import androidx.lifecycle.map
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import fit.tdc.edu.vn.cafemanagement.data.model.Zone
import fit.tdc.edu.vn.cafemanagement.data.model.Table
import fit.tdc.edu.vn.cafemanagement.data.model.ZoneType
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseDataSource @Inject constructor(
    private val storeID: String,
    private val db: FirebaseFirestore
) {
    companion object {
        private const val ZONE_TYPES_KEY = "zoneTypes"
        private const val ZONES_IN_STORES_KEY = "zonesInStores"
        private const val STORES_KEY = "stores"
        private const val STORE_KEY = "store"
        private const val ZONE_KEY = "zone"
        private const val NAME_KEY = "name"
        private const val TYPE_ID_KEY = "typeId"
        private const val TAG = "test"
        private var zoneTypeList = MutableLiveData<List<ZoneType>>()
        private var zoneList = MutableLiveData<List<Zone>>()
        private var tableList = MutableLiveData<List<Table>>()
    }

    fun getZoneTypeList(): LiveData<List<ZoneType>> {
        if (zoneTypeList.value == null) {
            loadZoneTypeList()
        }
        return zoneTypeList
    }

    private fun loadZoneTypeList() {
        val docRef = db.collection(ZONE_TYPES_KEY)
        docRef
            .addSnapshotListener { types, e ->
                if (e != null) {
                    Log.w(TAG, "Listen failed", e)
                    return@addSnapshotListener
                }

                val zoneTypes = ArrayList<ZoneType>()
                types?.forEach { type ->
                    zoneTypes.add(type.toObject(ZoneType::class.java))
                }
                zoneTypeList.value = zoneTypes
                // TODO Update from DB
            }
    }

    fun getZoneList(): LiveData<List<Zone>> {
        if (zoneList.value == null) {
            loadZoneList(storeID)
        }
        return zoneList
    }

    private fun loadZoneList(storeID: String) {
        db.collection(ZONES_IN_STORES_KEY).whereEqualTo(STORE_KEY, storeID)
            .addSnapshotListener { zonesInStore, e ->
                if (e != null) {
                    Log.w(TAG, "Listen failed", e)
                    return@addSnapshotListener
                }

                val zones = ArrayList<Zone>()
                zonesInStore?.forEach { zoneRef ->
                    val zoneCollection = zoneRef.get(ZONE_KEY) as DocumentReference
                    zoneCollection
//                        .get()
//                        .addOnSuccessListener { zone ->
//                            zones.add(
//                                Zone(
//                                    zone.id,
//                                    zone[NAME_KEY] as String,
//                                    zone[TYPE_ID_KEY] as String
//                                )
//                            )
//                            zoneList.value = zones
//                        }
                        .addSnapshotListener { zone, e ->
                            if (e != null) {
                                Log.w(TAG, "Listen failed", e)
                                return@addSnapshotListener
                            }
                            if (zone != null && zone.exists()) {
                                zones.add(
                                    Zone(
                                        zone.id,
                                        zone[NAME_KEY] as String,
                                        zone[TYPE_ID_KEY] as String
                                    )
                                )
                                zoneList.value = zones
                            }
                        }
                }
            }
    }

    fun getTableList(): LiveData<List<Table>> {
        if (tableList.value == null) {
            loadTableList(storeID)
        }
        return tableList
    }

    private fun loadTableList(storeID: String) {
        val docRef = db.collection(STORES_KEY).document(storeID).collection("tables").get()
            .addOnSuccessListener { tableCollection ->
                for (tableDoc in tableCollection) {
                    val table = tableDoc.toObject(Table::class.java)
                    Log.d(TAG, table.toString())
                }
            }
    }
}
