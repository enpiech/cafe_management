package fit.tdc.edu.vn.cafemanagement.data.repository.user

import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.FireBaseAPI
import fit.tdc.edu.vn.cafemanagement.data.extension.DocumentType
import fit.tdc.edu.vn.cafemanagement.data.extension.TaskLiveData
import fit.tdc.edu.vn.cafemanagement.data.model.user.User

class UserRepository(val dataSource: FireBaseAPI) :
    UserRepositoryAPI {

    override fun getAllUsers() = dataSource.getUserList(DocumentType.ALL)

    override fun getUser(id: String) = dataSource.getUser(id,DocumentType.SINGLE)

    override fun insert(user: User) =
        dataSource.createUser(user)

    override fun update(oldUser: User, newUser: User): TaskLiveData<Void> =
        dataSource.modifyUser(oldUser, newUser)

    override fun delete(user: User) = dataSource.deleteUser(user)
}