package fit.tdc.edu.vn.cafemanagement.data.repository.material

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.map
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.FireBaseAPI
import fit.tdc.edu.vn.cafemanagement.data.extension.DocumentType
import fit.tdc.edu.vn.cafemanagement.data.extension.Status
import fit.tdc.edu.vn.cafemanagement.data.model.material.Material
import fit.tdc.edu.vn.cafemanagement.data.model.user.UserInfor

class MaterialRepository(
    val dataSource: FireBaseAPI
) : MaterialRepositoryAPI {

    private var _materials = MediatorLiveData<List<Material>>().apply {
        addSource(getAllMaterials()){
            value = if (it.status == Status.SUCCESS) {
                it.data
            } else {
                listOf()
            }
        }
    }

    override fun getMaterialsListASCName() = _materials.map {list ->
        list.sortedBy {material ->
            material.name
        }
    }

    override fun getMaterialsListASCPrice() = _materials.map {list ->
        list.sortedBy {material ->
            material.price
        }
    }

    override fun getSellableMaterials()  = _materials.map {list ->
        list.filter {material ->
            material.type != Material.Type.INGREDIENT
        }
    }

    override fun getUnsellableMaterialsList() = _materials.map {list ->
        list.filter {material ->
            material.type == Material.Type.INGREDIENT
        }
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