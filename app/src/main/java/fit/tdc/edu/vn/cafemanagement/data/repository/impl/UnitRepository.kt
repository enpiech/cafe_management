package fit.tdc.edu.vn.cafemanagement.data.repository.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.FireBaseAPI
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.FireBaseDataSource
import fit.tdc.edu.vn.cafemanagement.data.extension.CollectionLiveData
import fit.tdc.edu.vn.cafemanagement.data.extension.DocumentLiveData
import fit.tdc.edu.vn.cafemanagement.data.extension.DocumentType
import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.Material
import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.Unit
import fit.tdc.edu.vn.cafemanagement.data.repository.UnitRepositoryAPI


class UnitRepository :
    UnitRepositoryAPI {
    val dataSource: FireBaseAPI =
        FireBaseDataSource()

    val filteredMaterialList = MediatorLiveData<List<Material>?>()
    private var listMaterial = dataSource.getMaterialList("EfzspceETNgWk56YDOOt",DocumentType.ALL)
    private var id:String = ""
    init {
        filteredMaterialList.addSource(listMaterial) { filteredList ->
            filteredList?.let {
                filteredMaterialList.value = filteredList.data?.filter { material ->
                    if (id.isNullOrEmpty()) {
                        true
                    } else {
                        material.id.equals(id)
                    }
                }
            }
        }
    }

    fun tablesInZone(id: String) : LiveData<List<Material>?> {
        this.id = id
        listMaterial = dataSource.getMaterialList("EfzspceETNgWk56YDOOt",DocumentType.ALL)
        return filteredMaterialList
    }


    override fun getAllUnits() : CollectionLiveData<Unit> =
        dataSource.getUnitList("EfzspceETNgWk56YDOOt",DocumentType.ALL)

    override fun getUnit(id: String) : DocumentLiveData<Unit> =
        dataSource.getUnit("EfzspceETNgWk56YDOOt", id,DocumentType.SINGLE)


    override fun insert(unit: Unit) =
        dataSource.createUnit("EfzspceETNgWk56YDOOt", unit)

    override fun update(unit: Unit) {
        dataSource.createUnit("EfzspceETNgWk56YDOOt", unit)
    }

    override fun delete(unit: Unit) =
        dataSource.deleteUnit("EfzspceETNgWk56YDOOt", unit.id)

    override fun deleteAllUnits() {
        //TODO: get delete all function
    }
}