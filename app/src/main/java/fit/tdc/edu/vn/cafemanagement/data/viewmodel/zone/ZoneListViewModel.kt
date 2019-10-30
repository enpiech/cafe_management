package fit.tdc.edu.vn.cafemanagement.data.viewmodel.zone

import fit.tdc.edu.vn.cafemanagement.data.model.zone.Zone
import fit.tdc.edu.vn.cafemanagement.data.repository.ZoneRepositoryAPI
import fit.tdc.edu.vn.cafemanagement.fragment.BaseListViewModel

class ZoneListViewModel(
    private val zoneRepository: ZoneRepositoryAPI
) : BaseListViewModel<Zone>() {

    override fun getAllItems() = zoneRepository.getAllZones()

    override fun delete(item: Zone) = zoneRepository.delete(item)
}