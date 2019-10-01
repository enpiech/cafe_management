package fit.tdc.edu.vn.cafemanagement.data.data_source

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import fit.tdc.edu.vn.cafemanagement.data.model.Table
import fit.tdc.edu.vn.cafemanagement.data.model.Zone
import fit.tdc.edu.vn.cafemanagement.data.model.ZoneType
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FireBaseDataSource @Inject constructor(
    private val storeID: String,
    private val db: FirebaseFirestore
): FireBaseAPI {
    companion object {
        private const val ZONE_TYPES_KEY = "zoneTypes"
        private const val STORES_KEY = "stores"
        private const val ZONES_KEY = "zones"
        private const val NAME_KEY = "name"
        private const val TYPE_ID_KEY = "typeId"
        private const val TABLES_KEY = "tables"
        private const val ZONE_ID_KEY = "zoneId"
        private const val STATE_ID_KEY = "stateId"
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
        db.collection(STORES_KEY).document(storeID).collection(ZONE_TYPES_KEY)
            .get()
            .addOnSuccessListener { zoneTypeCollection ->
                for (zoneTypeDoc in zoneTypeCollection) {
                    val zoneType = ZoneType(
                        zoneTypeDoc.id,
                        zoneTypeDoc[NAME_KEY] as String
                    )
                    Log.d(TAG, zoneType.toString())
                }
            }
    }

    fun getZoneList(): LiveData<List<Zone>> {
        if (zoneList.value == null) {
            loadZoneList(storeID)
        }
        return zoneList
    }

    private fun loadZoneList(storeID: String) {
        db.collection(STORES_KEY).document(storeID).collection(ZONES_KEY)
            .get()
            .addOnSuccessListener { zoneCollection ->
                for (zoneDoc in zoneCollection) {
                    val zone = Zone(
                        zoneDoc.id,
                        zoneDoc[NAME_KEY] as String,
                        zoneDoc[TYPE_ID_KEY] as String
                    )
                    Log.d(TAG, zone.toString())
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
        db.collection(STORES_KEY).document(storeID).collection(TABLES_KEY)
            .get()
            .addOnSuccessListener { tableCollection ->
                for (tableDoc in tableCollection) {
                    val table = Table(
                        tableDoc.id,
                        tableDoc[NAME_KEY] as String,
                        tableDoc[STATE_ID_KEY] as String,
                        tableDoc[ZONE_ID_KEY] as String
                    )
                    Log.d(TAG, table.toString())
                }
            }
    }


}
