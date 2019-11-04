package fit.tdc.edu.vn.cafemanagement.data.viewmodel.chef

import fit.tdc.edu.vn.cafemanagement.data.model.chef.Chef
import fit.tdc.edu.vn.cafemanagement.data.repository.impl.ChefRepository
import fit.tdc.edu.vn.cafemanagement.fragment.BaseListViewModel

class ChefListViewModel(
    private val chefRepository: ChefRepository
) : BaseListViewModel<Chef>() {
    override fun getAllItems() = chefRepository.getAllChefs()

    override fun delete(item: Chef) = chefRepository.delete(item)
}

