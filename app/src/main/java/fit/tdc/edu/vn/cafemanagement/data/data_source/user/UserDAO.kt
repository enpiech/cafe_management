package fit.tdc.edu.vn.cafemanagement.data.data_source.user

import androidx.lifecycle.LiveData
import androidx.room.*
import fit.tdc.edu.vn.cafemanagement.data.model.user.User

@Dao
interface UserDAO {
    @Insert
    fun insert(user: User)

    @Update
    fun update(user: User)

    @Query("DELETE FROM user_table")
    fun deleteAllUsers()

    @Query("SELECT * FROM user_table")
    fun getAllUsers(): LiveData<List<User>>
}