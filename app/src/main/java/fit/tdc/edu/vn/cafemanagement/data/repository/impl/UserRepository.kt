package fit.tdc.edu.vn.cafemanagement.data.repository.impl

import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.FireBaseAPI
import fit.tdc.edu.vn.cafemanagement.data.extension.DocumentType
import fit.tdc.edu.vn.cafemanagement.data.model.user.User
import fit.tdc.edu.vn.cafemanagement.data.repository.UserRepsitoryAPI

class UserRepository(val dataSource: FireBaseAPI) : UserRepsitoryAPI{

    override fun getAllUsers() = dataSource.getUserList(DocumentType.ALL)

    override fun getUser(id: String) = dataSource.getUser(id,DocumentType.SINGLE)

    override fun insert(user: User) =
        dataSource.createUser(user)

    override fun update(user: User) {
        //TODO: get update function
    }

    override fun delete(user: User) =
        dataSource.deleteCategory("EfzspceETNgWk56YDOOt", user.id)
}