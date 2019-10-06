package fit.tdc.edu.vn.cafemanagement.data.repository

import com.google.firebase.firestore.DocumentReference
import fit.tdc.edu.vn.cafemanagement.data.extension.CollectionLiveData
import fit.tdc.edu.vn.cafemanagement.data.extension.DocumentLiveData
import fit.tdc.edu.vn.cafemanagement.data.extension.TaskLiveData
import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.*
import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.Unit

interface UnitRepositoryAPI {
    fun getAllCategory(): CollectionLiveData<Category>
    fun getAllMarerials(): CollectionLiveData<Category>
    fun getAllRevenue(): CollectionLiveData<Category>
    fun getAllTable(): CollectionLiveData<Category>
    fun getAllUnit(): CollectionLiveData<Category>
    fun getAllZone(): CollectionLiveData<Category>
    fun getAllZoneType(): CollectionLiveData<Category>

    fun getCategory(): DocumentLiveData<Category>
    fun getMarerials(): DocumentLiveData<Material>
    fun getRevenue(): DocumentLiveData<Revenue>
    fun getTable(): DocumentLiveData<Table>
    fun getUnit(): DocumentLiveData<Unit>
    fun getZone(): DocumentLiveData<Zone>
    fun getZoneType(): DocumentLiveData<ZoneType>

    fun insert(category: Category): TaskLiveData<DocumentReference>
    fun insert(material: Material): TaskLiveData<DocumentReference>
    fun insert(revenue: Revenue): TaskLiveData<DocumentReference>
    fun insert(table: Table): TaskLiveData<DocumentReference>
    fun insert(unit: Unit): TaskLiveData<DocumentReference>
    fun insert(zone: Zone): TaskLiveData<DocumentReference>
    fun insert(zoneType: ZoneType): TaskLiveData<DocumentReference>
}