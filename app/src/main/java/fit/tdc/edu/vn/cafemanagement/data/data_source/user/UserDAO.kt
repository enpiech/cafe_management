package fit.tdc.edu.vn.cafemanagement.data.data_source.user

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
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