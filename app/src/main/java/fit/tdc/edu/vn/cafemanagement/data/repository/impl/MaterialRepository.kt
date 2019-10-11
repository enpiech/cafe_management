package fit.tdc.edu.vn.cafemanagement.data.repository.impl

import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.FireBaseDataSource
import fit.tdc.edu.vn.cafemanagement.data.extension.DocumentType
import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.Material
import fit.tdc.edu.vn.cafemanagement.data.repository.MaterialRepositoryAPI

class MaterialRepository ( val dataSource: FireBaseDataSource) :
    MaterialRepositoryAPI {

    override fun getAllMaterials() = dataSource.getMaterialList("EfzspceETNgWk56YDOOt",DocumentType.ALL)

    override fun getMaterial(id: String) = dataSource.getMaterial("EfzspceETNgWk56YDOOt",id,DocumentType.SINGLE)

    override fun insert(material: Material) =
        dataSource.createMaterial("EfzspceETNgWk56YDOOt", material)

    override fun update(material: Material) {
        dataSource.modifyMaterial("EfzspceETNgWk56YDOOt", material)
    }

    override fun delete(material: Material) =
        dataSource.deleteMaterial("EfzspceETNgWk56YDOOt", material.id)
}