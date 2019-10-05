package fit.tdc.edu.vn.cafemanagement.data.model.Unit

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UnitDAO {
    @Insert
    fun insert(unit: Unit)

    @Update
    fun update(unit: Unit)

    @Delete
    fun delete(unit: Unit)

    @Query("DELETE FROM unit_table")
    fun deleteAllUnits()

    @Query("SELECT * FROM unit_table")
    fun getAllUnits(): LiveData<List<Unit>>
}