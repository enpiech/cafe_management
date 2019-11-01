package fit.tdc.edu.vn.cafemanagement.data.viewmodel.unit

import fit.tdc.edu.vn.cafemanagement.data.model.unit.Unit
import fit.tdc.edu.vn.cafemanagement.data.repository.UnitRepositoryAPI
import fit.tdc.edu.vn.cafemanagement.data.repository.impl.UnitRepository
import fit.tdc.edu.vn.cafemanagement.fragment.BaseListViewModel

class UnitListViewModel (
    private val unitRepository: UnitRepository
): BaseListViewModel<Unit>() {
    override fun getAllItems() = unitRepository.getAllUnits()

    override fun delete(item: Unit) = unitRepository.delete(item)
}