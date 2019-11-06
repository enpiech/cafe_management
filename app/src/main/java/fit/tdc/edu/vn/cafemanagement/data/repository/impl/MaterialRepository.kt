package fit.tdc.edu.vn.cafemanagement.data.repository.impl

import androidx.lifecycle.MediatorLiveData
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.FireBaseAPI
import fit.tdc.edu.vn.cafemanagement.data.extension.DocumentType
import fit.tdc.edu.vn.cafemanagement.data.model.material.Material
import fit.tdc.edu.vn.cafemanagement.data.model.user.UserInfor
import fit.tdc.edu.vn.cafemanagement.data.repository.MaterialRepositoryAPI

class MaterialRepository ( val dataSource: FireBaseAPI) :
    MaterialRepositoryAPI {

    private var filteredMeterialsList = MediatorLiveData<List<Material>?>()

    init {
        filteredMeterialsList.addSource(getAllMaterials()){}
    }

    override fun getMaterialsListASCName() : MediatorLiveData<List<Material>?>{
        filteredMeterialsList.addSource(getAllMaterials()){}
        filteredMeterialsList.value = filteredMeterialsList.value!!.sortedBy { it.name }
        return filteredMeterialsList
    }

    override fun getMaterialsListASCPrice() : MediatorLiveData<List<Material>?>{
        filteredMeterialsList.addSource(getAllMaterials()){}
        filteredMeterialsList.value = filteredMeterialsList.value!!.sortedBy { it.price }
        return filteredMeterialsList
    }

    override fun getSellableMaterialsList() : MediatorLiveData<List<Material>?>{
        filteredMeterialsList.addSource(getAllMaterials()){}
        filteredMeterialsList.value = filteredMeterialsList.value!!.filter {
            it.sellable
        }
        return filteredMeterialsList
    }

    override fun getMaterialsListDESCName() : MediatorLiveData<List<Material>?>{
        filteredMeterialsList.addSource(getAllMaterials()){}
        filteredMeterialsList.value = filteredMeterialsList.value!!.sortedByDescending { it.name }
        return filteredMeterialsList
    }

    override fun getMaterialsListDESCPrice() : MediatorLiveData<List<Material>?>{
        filteredMeterialsList.addSource(getAllMaterials()){}
        filteredMeterialsList.value = filteredMeterialsList.value!!.sortedByDescending { it.price }
        return filteredMeterialsList
    }

    override fun getUnSellableMaterialsList() : MediatorLiveData<List<Material>?>{
        filteredMeterialsList.addSource(getAllMaterials()){}
        filteredMeterialsList.value = filteredMeterialsList.value!!.filter {
            !it.sellable
        }
        return filteredMeterialsList
    }



    override fun getAllMaterials() = dataSource.getMaterialList(UserInfor.getInstance().storeId!!,DocumentType.ALL)

    override fun getMaterial(id: String) = dataSource.getMaterial(UserInfor.getInstance().storeId!!,id,DocumentType.SINGLE)

    override fun insert(material: Material) =
        dataSource.createMaterial(UserInfor.getInstance().storeId!!, material)

    override fun update(material: Material) =
        dataSource.modifyMaterial(UserInfor.getInstance().storeId!!, material)

    override fun delete(material: Material) =
        dataSource.deleteMaterial(UserInfor.getInstance().storeId!!, material.id)
}