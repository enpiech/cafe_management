package fit.tdc.edu.vn.cafemanagement.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import fit.tdc.edu.vn.cafemanagement.data.data_source.FireBaseDataSource
import fit.tdc.edu.vn.cafemanagement.data.model.Revenue

class RevenueRepository ( val dataSource: FireBaseDataSource){

    fun getAllRevenues() : LiveData<ArrayList<Revenue>> {
        return dataSource.getRevenueList("EfzspceETNgWk56YDOOt")
    }

    fun getRevenue(id: String) : LiveData<Revenue?> {
        return dataSource.getRevenue("EfzspceETNgWk56YDOOt", id)
    }

    fun insert(revenue: Revenue) {
        dataSource.createRevenue("EfzspceETNgWk56YDOOt", revenue)
    }

    fun update(revenue: Revenue) {
        //TODO: get update function
    }

    fun delete(revenue: Revenue) {
        dataSource.deleteRevenue("EfzspceETNgWk56YDOOt", revenue.id)
    }

    fun deleteAllRevenues() {
        //TODO: get delete all function
    }
}