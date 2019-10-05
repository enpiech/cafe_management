package fit.tdc.edu.vn.cafemanagement.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import fit.tdc.edu.vn.cafemanagement.data.data_source.FireBaseDataSource
import fit.tdc.edu.vn.cafemanagement.data.model.Material

class MaterialRepository ( val dataSource: FireBaseDataSource) {

    fun getAllMarerials() : LiveData<ArrayList<Material>> {
        return dataSource.getMaterialMap().map { map: HashMap<String, Material> ->
            ArrayList<Material>(map.values)
        }
    }

    fun getMaterial(id: String) : LiveData<Material?> {
        return dataSource.getMaterialMap().map { map: HashMap<String, Material> ->
            map[id] = Material.builder().build()
            map[id]
        }
    }

    fun insert(material: Material) {
        //TODO: get insert function
    }

    fun update(material: Material) {
        //TODO: get update function
    }

    fun delete(material: Material) {
        //TODO: get delete function
    }

    fun deleteAllMaterialss() {
        //TODO: get delete all function
    }
}