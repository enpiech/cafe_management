package fit.tdc.edu.vn.cafemanagement.data.repository.impl

import com.google.firebase.firestore.DocumentReference
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.FireBaseAPI
import fit.tdc.edu.vn.cafemanagement.data.extension.CollectionLiveData
import fit.tdc.edu.vn.cafemanagement.data.extension.DocumentLiveData
import fit.tdc.edu.vn.cafemanagement.data.extension.DocumentType
import fit.tdc.edu.vn.cafemanagement.data.extension.TaskLiveData
import fit.tdc.edu.vn.cafemanagement.data.model.chef.Chef
import fit.tdc.edu.vn.cafemanagement.data.model.user.UserInfor
import fit.tdc.edu.vn.cafemanagement.data.repository.ChefRepositoryAPI

class ChefRepository (val dataSource: FireBaseAPI): ChefRepositoryAPI {
    override fun getAllChefs()= dataSource.getChefList(UserInfor.getInstance().storeId!!, DocumentType.ALL)

    override fun getChef(id: String)= dataSource.getChef(UserInfor.getInstance().storeId!!, id,DocumentType.SINGLE)

    override fun insert(chef: Chef)=
        dataSource.createChef(UserInfor.getInstance().storeId!!, chef)

    override fun update(chef: Chef): TaskLiveData<Void> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete(chef: Chef)=
        dataSource.deleteChef(UserInfor.getInstance().storeId!!, chef.id)
}