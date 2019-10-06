package fit.tdc.edu.vn.cafemanagement.data.repository

import fit.tdc.edu.vn.cafemanagement.data.data_source.FireBaseDataSource
import fit.tdc.edu.vn.cafemanagement.data.extension.DocumentType
import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.Material

class MaterialRepository ( val dataSource: FireBaseDataSource) {

    fun getAllMarerials() = dataSource.getMaterialList("EfzspceETNgWk56YDOOt",DocumentType.ALL)

    fun getMaterial(id: String) = dataSource.getMaterial("EfzspceETNgWk56YDOOt",id,DocumentType.SINGLE)

    fun insert(material: Material) =
        dataSource.createMaterial("EfzspceETNgWk56YDOOt", material)

    fun update(material: Material) {
        //TODO: get update function
    }

    fun delete(material: Material) =
        dataSource.deleteMaterial("EfzspceETNgWk56YDOOt", material.id!!)

    fun deleteAllMaterialss() {
        //TODO: get delete all function
    }
}