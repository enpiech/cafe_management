package fit.tdc.edu.vn.cafemanagement.data.repository

import fit.tdc.edu.vn.cafemanagement.data.data_source.FireBaseDataSource
import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.Revenue

class RevenueRepository ( val dataSource: FireBaseDataSource){

    fun getAllRevenues() = dataSource.getRevenueList("EfzspceETNgWk56YDOOt")

    fun getRevenue(id: String) = dataSource.getRevenue("EfzspceETNgWk56YDOOt", id)

    fun insert(revenue: Revenue) {
        dataSource.createRevenue("EfzspceETNgWk56YDOOt", revenue)
    }

    fun update(revenue: Revenue) {
        //TODO: get update function
    }

    fun delete(revenue: Revenue) {
        dataSource.deleteRevenue("EfzspceETNgWk56YDOOt", revenue.id!!)
    }

    fun deleteAllRevenues() {
        //TODO: get delete all function
    }
}