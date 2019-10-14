package fit.tdc.edu.vn.cafemanagement.data.viewmodel.material_viewmodel

import androidx.lifecycle.ViewModel
import fit.tdc.edu.vn.cafemanagement.data.extension.CollectionLiveData
import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.Material
import fit.tdc.edu.vn.cafemanagement.data.repository.MaterialRepositoryAPI

class MaterialViewModel (private val materialRepository: MaterialRepositoryAPI): ViewModel() {

    private var allMaterials: CollectionLiveData<Material> = materialRepository.getAllMaterials()

    fun insert(material: Material) {
        materialRepository.insert(material)
    }

    fun update(material: Material) {
        materialRepository.update(material)
    }

    fun delete(material: Material) {
        materialRepository.delete(material)
    }

    fun getAllMaterials(): CollectionLiveData<Material> {
        return allMaterials
    }
}