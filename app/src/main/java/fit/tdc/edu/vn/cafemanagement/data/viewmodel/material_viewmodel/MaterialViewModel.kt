package fit.tdc.edu.vn.cafemanagement.data.viewmodel.material_viewmodel

import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.Material
import fit.tdc.edu.vn.cafemanagement.data.repository.MaterialRepository

class MaterialViewModel (private val materialRepository: MaterialRepository) {

    private var allMaterials = materialRepository.getAllMarerials()

    fun getMaterial(id: String) = materialRepository.getMaterial(id)

    fun insert(material: Material) {
        materialRepository.insert(material)
    }

    fun update(material: Material) {
        materialRepository.update(material)
    }

    fun delete(material: Material) {
        materialRepository.delete(material)
    }

    fun deleteAllMaterials() {
        materialRepository.deleteAllMaterialss()
    }

    fun getAllMaterials() = allMaterials

}