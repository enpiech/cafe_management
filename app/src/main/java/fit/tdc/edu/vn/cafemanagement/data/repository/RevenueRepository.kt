package fit.tdc.edu.vn.cafemanagement.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import fit.tdc.edu.vn.cafemanagement.data.data_source.FireBaseDataSource
import fit.tdc.edu.vn.cafemanagement.data.model.Revenue

class RevenueRepository ( val dataSource: FireBaseDataSource){

    fun getAllRevenues() : LiveData<ArrayList<Revenue>> {
        return dataSource.getRevenueMap().map { map: HashMap<String, Revenue> ->
            ArrayList<Revenue>(map.values)
        }
    }

    fun getRevenue(id: String) : LiveData<Revenue?> {
        return dataSource.getRevenueMap().map { map: HashMap<String, Revenue> ->
            map[id] = Revenue.builder().build()
            map[id]
        }
    }

    fun insert(revenue: Revenue) {
        //TODO: get insert function
    }

    fun update(revenue: Revenue) {
        //TODO: get update function
    }

    fun delete(revenue: Revenue) {
        //TODO: get delete function
    }

    fun deleteAllRevenues() {
        //TODO: get delete all function
    }
}