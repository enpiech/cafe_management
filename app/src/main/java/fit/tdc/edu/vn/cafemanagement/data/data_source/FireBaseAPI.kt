package fit.tdc.edu.vn.cafemanagement.data.data_source

import fit.tdc.edu.vn.cafemanagement.data.extension.CollectionLiveData
import fit.tdc.edu.vn.cafemanagement.data.extension.DocumentType
import fit.tdc.edu.vn.cafemanagement.data.extension.DocumentLiveData
import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.*
import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.Unit

interface FireBaseAPI {
    // GET COLLECTION
    fun getCategoryList(storeId: String, documentType: DocumentType = DocumentType.ALL): CollectionLiveData<Category>
    fun getMaterialList(storeId: String, documentType: DocumentType = DocumentType.ALL): CollectionLiveData<Material>
    fun getTableList(storeId: String, documentType: DocumentType = DocumentType.ALL): CollectionLiveData<Table>
    fun getRevenueList(storeId: String, documentType: DocumentType = DocumentType.ALL): CollectionLiveData<Revenue>
    fun getUnitList(storeId: String, documentType: DocumentType = DocumentType.ALL): CollectionLiveData<Unit>
    fun getZoneTypeList(storeId: String, documentType: DocumentType = DocumentType.ALL): CollectionLiveData<ZoneType>
    fun getZoneList(storeId: String, documentType: DocumentType = DocumentType.ALL): CollectionLiveData<Zone>
    fun getEmployeeList(documentType: DocumentType = DocumentType.ALL): CollectionLiveData<Employee>
    fun getStoreList(documentType: DocumentType = DocumentType.ALL): CollectionLiveData<Store>

    // GET SPECIFIC DOCUMENT
    fun getUnit(storeId: String, id: String): DocumentLiveData<Unit>
    fun getZone(storeId: String, id: String): DocumentLiveData<Zone>
    fun getCategory(storeId: String, id: String): DocumentLiveData<Category>
    fun getMaterial(storeId: String, id: String): DocumentLiveData<Material>
    fun getRevenue(storeId: String, id: String): DocumentLiveData<Revenue>
    fun getTable(storeId: String, id: String): DocumentLiveData<Table>

    fun createUnit(storeId: String, unit: Unit)
    fun createZone(storeId: String, zone: Zone)
    fun createCategory(storeId: String, category: Category)
    fun createMaterial(storeId: String, material: Material)
    fun createRevenue(storeId: String, revenue: Revenue)
    fun createTable(storeId: String, table: Table)

    fun deleteUnit(storeId: String, unitId: String)
    fun deleteZone(storeId: String, zoneId: String)
    fun deleteCategory(storeId: String, categoryId: String)
    fun deleteMaterial(storeId: String, materialID: String)
    fun deleteRevenue(storeId: String, revenueID: String)
    fun deleteTable(storeId: String, tableID: String)
}