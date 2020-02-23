package fit.tdc.edu.vn.cafemanagement.data.repository.unit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.material.MaterialRemoteDataSource
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.unit.UnitRemoteDataSource
import fit.tdc.edu.vn.cafemanagement.data.extension.DocumentType
import fit.tdc.edu.vn.cafemanagement.data.model.material.Material
import fit.tdc.edu.vn.cafemanagement.data.model.unit.Unit
import fit.tdc.edu.vn.cafemanagement.data.model.user.UserInfor


class UnitRepositoryImpl(
    val unitDataSource: UnitRemoteDataSource,
    private val materialDataSource: MaterialRemoteDataSource
) : UnitRepository {

    private val filteredMaterialList = MediatorLiveData<List<Material>?>()
    private val filteredUnitList = MediatorLiveData<List<Unit>?>()

    private var listMaterial =
        materialDataSource.readMaterials(UserInfor.getInstance().storeId!!, DocumentType.ALL)
    private var id: String = ""

    init {
        filteredMaterialList.addSource(listMaterial) { filteredList ->
            filteredList?.let {
                filteredMaterialList.value = filteredList.data?.filter { material ->
                    if (id.isEmpty()) {
                        true
                    } else {
                        material.id == id
                    }
                }
            }
        }
        filteredUnitList.addSource(getAllUnits()) {}
    }

    override fun materialsWithUnit(id: String): LiveData<List<Material>?> {
        this.id = id
        listMaterial =
            materialDataSource.readMaterials(UserInfor.getInstance().storeId!!, DocumentType.ALL)
        return filteredMaterialList
    }

    override fun getUnitsASC(): MediatorLiveData<List<Unit>?> {
        filteredUnitList.addSource(getAllUnits()) {}
        filteredUnitList.value = filteredUnitList.value!!.sortedBy {
            it.name
        }
        return filteredUnitList
    }

    override fun getUnitsDESC(): MediatorLiveData<List<Unit>?> {
        filteredUnitList.addSource(getAllUnits()) {}
        filteredUnitList.value = filteredUnitList.value!!.sortedByDescending {
            it.name
        }
        return filteredUnitList
    }

    override fun getAllUnits() =
        unitDataSource.readUnits(UserInfor.getInstance().storeId!!, DocumentType.ALL)

    override fun getUnit(id: String) =
        unitDataSource.readUnit(UserInfor.getInstance().storeId!!, id, DocumentType.SINGLE)

    override fun insert(unit: Unit) =
        unitDataSource.createUnit(UserInfor.getInstance().storeId!!, unit)

    override fun update(unit: Unit) =
        unitDataSource.updateUnit(UserInfor.getInstance().storeId!!, unit)

    override fun delete(unit: Unit) =
        unitDataSource.deleteUnit(UserInfor.getInstance().storeId!!, unit.id)
}