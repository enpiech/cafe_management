package fit.tdc.edu.vn.cafemanagement.data.repository.user

import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.user.UserRemoteDataSource
import fit.tdc.edu.vn.cafemanagement.data.extension.DocumentType
import fit.tdc.edu.vn.cafemanagement.data.extension.TaskLiveData
import fit.tdc.edu.vn.cafemanagement.data.model.user.User

class UserRepositoryImpl(val dataSource: UserRemoteDataSource) :
    UserRepository {

    override fun getAllUsers() = dataSource.readUsers(DocumentType.ALL)

    override fun getUser(id: String) = dataSource.readUser(id, DocumentType.SINGLE)

    override fun insert(user: User) =
        dataSource.createUser(user)

    override fun update(oldUser: User, newUser: User): TaskLiveData<Void> =
        dataSource.updateUser(oldUser, newUser)

    override fun delete(user: User) = dataSource.deleteUser(user)
}