package fit.tdc.edu.vn.cafemanagement.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import fit.tdc.edu.vn.cafemanagement.data.data_source.FireBaseDataSource
import fit.tdc.edu.vn.cafemanagement.data.model.Category
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class CategoryRepository(val dataSource: FireBaseDataSource) {

    fun getAllCategory() : LiveData<ArrayList<Category>> {
        return dataSource.getCategoryMap().map { map: HashMap<String, Category> ->
            ArrayList<Category>(map.values)
        }
    }

    fun getCategory(id: String) : LiveData<Category?> {
        return dataSource.getCategoryMap().map { map: HashMap<String, Category> ->
            map[id]
        }
    }

    fun getCategoryList() : LiveData<ArrayList<Category?>?> {
        return dataSource.getCategoryMap().map { map: HashMap<String, Category> ->
            ArrayList(map.values)
        }
    }
}