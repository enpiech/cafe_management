package fit.tdc.edu.vn.cafemanagement.data.viewmodel.material

import fit.tdc.edu.vn.cafemanagement.data.model.material.Material
import fit.tdc.edu.vn.cafemanagement.data.repository.material.MaterialRepository
import fit.tdc.edu.vn.cafemanagement.fragment.BaseListViewModel

class MaterialListViewModel(
    private val materialRepository: MaterialRepository
): BaseListViewModel<Material>() {

    override fun getAllItems() = materialRepository.getAllMaterials()

    override fun delete(item: Material) = materialRepository.delete(item)
}