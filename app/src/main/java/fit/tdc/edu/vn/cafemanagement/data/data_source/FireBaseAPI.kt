package fit.tdc.edu.vn.cafemanagement.data.data_source

import androidx.lifecycle.LiveData
import fit.tdc.edu.vn.cafemanagement.data.model.*
import fit.tdc.edu.vn.cafemanagement.data.model.Unit

interface FireBaseAPI {
    fun getCategoryList(storeId: String): LiveData<ArrayList<Category>>
    fun getMaterialList(storeId: String): LiveData<ArrayList<Material>>
    fun getTableList(storeId: String): LiveData<ArrayList<Table>>
    fun getRevenueList(storeId: String): LiveData<ArrayList<Revenue>>
    fun getUnitList(storeId: String): LiveData<ArrayList<Unit>>
    fun getZoneTypeList(storeId: String): LiveData<ArrayList<ZoneType>>
    fun getZoneList(storeId: String): LiveData<ArrayList<Zone>>
    fun getEmployeeList(): LiveData<ArrayList<Employee>>
    fun getStoreList(): LiveData<ArrayList<Store>>

    fun getUnit(storeId: String, id: String): LiveData<Unit?>
    fun getZone(storeId: String, id: String): LiveData<Zone?>
    fun getCategory(storeId: String, id: String): LiveData<Category?>

    fun createUnit(storeId: String, unit: Unit)
    fun createZone(storeId: String, zone: Zone)
    fun createCategory(storeId: String, category: Category)

    fun deleteUnit(storeId: String, unitId: String)
    fun deleteZone(storeId: String, zoneId: String)
    fun deleteCategory(storeId: String, categoryId: String)
}