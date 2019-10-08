package fit.tdc.edu.vn.cafemanagement.data.viewmodel.material_viewmodel

import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.Material
import fit.tdc.edu.vn.cafemanagement.data.repository.MaterialRepositoryAPI

class MaterialViewModel (private val materialRepository: MaterialRepositoryAPI) {

    private var allMaterials = materialRepository.getAllMaterials()

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
        materialRepository.deleteAllMaterials()
    }

    fun getAllMaterials() = allMaterials

}