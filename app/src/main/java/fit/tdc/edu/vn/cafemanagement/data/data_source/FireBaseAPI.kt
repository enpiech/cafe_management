package fit.tdc.edu.vn.cafemanagement.data.data_source

import androidx.lifecycle.LiveData
import fit.tdc.edu.vn.cafemanagement.data.model.*
import fit.tdc.edu.vn.cafemanagement.data.model.Unit

interface FireBaseAPI {
    fun getCategoryMap(): LiveData<HashMap<String, Category>>
    fun getMaterialMap(): LiveData<HashMap<String, Material>>
    fun getTableMap(): LiveData<HashMap<String, Table>>
    fun getRevenueMap(): LiveData<HashMap<String, Revenue>>
    fun getUnitMap(): LiveData<HashMap<String, Unit>>
    fun getZoneTypeMap(): LiveData<HashMap<String, ZoneType>>
    fun getZoneMap(): LiveData<HashMap<String, Zone>>
    fun getEmployeeMap(): LiveData<HashMap<String, Employee>>
    fun getStoreMap(): LiveData<HashMap<String, Store>>

    fun getUnit(id: String): LiveData<Unit>
    fun getZone(id: String): LiveData<Zone>
    fun getCategory(id: String): LiveData<Category>

}