package fit.tdc.edu.vn.cafemanagement.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import fit.tdc.edu.vn.cafemanagement.data.data_source.FireBaseDataSource
import fit.tdc.edu.vn.cafemanagement.data.model.Category
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class CategoryRepository(val dataSource: FireBaseDataSource) {

    public fun getAllCategory() : ArrayList<Category> {
        val categoryList:ArrayList<Category> = ArrayList()

        val categoryMap = dataSource.getCategoryMap()
        categoryMap.observeForever{ data ->
            categoryList.addAll(data.values)
        }

        return categoryList
    }

    public fun getAllCategoryByID(id : String) : Category {
        var category:Category = Category.builder().build()

        val categoryMap = dataSource.getCategoryMap()

        categoryMap.observeForever{ data ->
            category = Category.builder().id(data.get(id)?.id).name(data.get(id)?.name).build()

        }

        return category
    }

    fun getCategory(id: String) : LiveData<Category?> {
        return dataSource.getCategoryMap().map { map: HashMap<String, Category> ->
            map[id] = Category.builder().build()
            map[id]
        }
    }
}