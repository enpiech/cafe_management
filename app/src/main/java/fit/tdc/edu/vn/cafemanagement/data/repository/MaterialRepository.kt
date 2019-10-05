package fit.tdc.edu.vn.cafemanagement.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import fit.tdc.edu.vn.cafemanagement.data.data_source.FireBaseDataSource
import fit.tdc.edu.vn.cafemanagement.data.model.Material

class MaterialRepository ( val dataSource: FireBaseDataSource) {

    fun getAllMarerials() : LiveData<ArrayList<Material>> {
        return dataSource.getMaterialList("EfzspceETNgWk56YDOOt")
    }

    fun getMaterial(id: String) : LiveData<Material?> {
        return dataSource.getMaterial("EfzspceETNgWk56YDOOt",id)
    }

    fun insert(material: Material) {
        dataSource.createMaterial("EfzspceETNgWk56YDOOt", material)
    }

    fun update(material: Material) {
        //TODO: get update function
    }

    fun delete(material: Material) {
        dataSource.deleteMaterial("EfzspceETNgWk56YDOOt", material.id)
    }

    fun deleteAllMaterialss() {
        //TODO: get delete all function
    }
}