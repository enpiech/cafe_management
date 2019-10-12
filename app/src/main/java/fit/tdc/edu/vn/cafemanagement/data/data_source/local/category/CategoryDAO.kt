package fit.tdc.edu.vn.cafemanagement.data.data_source.local.category

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.Category

@Dao
interface CategoryDAO {

    @Insert
    fun insert(category: Category)

    
    fun getAllNotes(): LiveData<List<Category>>
}