package fit.tdc.edu.vn.cafemanagement.data.viewmodel.material_viewmodel

import androidx.lifecycle.LiveData
import fit.tdc.edu.vn.cafemanagement.data.model.Material
import fit.tdc.edu.vn.cafemanagement.data.model.Zone
import fit.tdc.edu.vn.cafemanagement.data.repository.MaterialRepository

class MaterialViewModel (private val materialRepository: MaterialRepository) {

    private var allMaterials: LiveData<ArrayList<Material>> = materialRepository.getAllMarerials()

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

    fun getAllMaterials(): LiveData<ArrayList<Material>> {
        return allMaterials
    }

}