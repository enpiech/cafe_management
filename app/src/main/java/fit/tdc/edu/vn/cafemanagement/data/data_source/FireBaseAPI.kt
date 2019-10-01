package fit.tdc.edu.vn.cafemanagement.data.data_source

import androidx.lifecycle.LiveData
import fit.tdc.edu.vn.cafemanagement.data.model.*
import fit.tdc.edu.vn.cafemanagement.data.model.Unit

interface FireBaseAPI {
    fun getCategoryMap(): LiveData<HashMap<String, Category>>
    fun getMaterialMap(): LiveData<HashMap<String, Material>>
    fun getTableMap(): LiveData<HashMap<String, Table>>
    fun getUnitMap(): LiveData<HashMap<String, Unit>>
    fun getZoneTypeMap(): LiveData<HashMap<String, ZoneType>>
    fun getZoneMap(): LiveData<HashMap<String, Zone>>
}