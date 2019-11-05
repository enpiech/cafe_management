package fit.tdc.edu.vn.cafemanagement.data.viewmodel.ware_house

import androidx.lifecycle.SavedStateHandle
import com.google.firebase.firestore.DocumentReference
import fit.tdc.edu.vn.cafemanagement.data.extension.DocumentLiveData
import fit.tdc.edu.vn.cafemanagement.data.extension.TaskLiveData
import fit.tdc.edu.vn.cafemanagement.data.model.ware_house.WareHouse
import fit.tdc.edu.vn.cafemanagement.data.repository.MaterialRepositoryAPI
import fit.tdc.edu.vn.cafemanagement.data.repository.UnitRepositoryAPI
import fit.tdc.edu.vn.cafemanagement.data.repository.WareHouseRepositoryAPI
import fit.tdc.edu.vn.cafemanagement.fragment.BaseDetailViewModel

class WareHouseDetailViewModel (
    private val handle: SavedStateHandle,
    private val wareHouseRepository: WareHouseRepositoryAPI,
    unitRepository: UnitRepositoryAPI,
    materialRepository: MaterialRepositoryAPI
) : BaseDetailViewModel<WareHouse>() {
    override var saved: WareHouse
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
        set(value) {}

    override fun getItem(id: String): DocumentLiveData<WareHouse> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun insert(item: WareHouse): TaskLiveData<DocumentReference> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun update(item: WareHouse): TaskLiveData<Void> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun validate(item: WareHouse?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}