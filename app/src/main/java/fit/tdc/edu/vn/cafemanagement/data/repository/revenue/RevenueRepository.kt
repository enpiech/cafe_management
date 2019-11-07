package fit.tdc.edu.vn.cafemanagement.data.repository.revenue

import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.FireBaseAPI
import fit.tdc.edu.vn.cafemanagement.data.extension.DocumentType
import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.Revenue
import fit.tdc.edu.vn.cafemanagement.data.model.user.UserInfor

class RevenueRepository ( val dataSource: FireBaseAPI):
    RevenueRepositoryAPI {

    override fun getAllRevenues() = dataSource.getRevenueList(UserInfor.getInstance().storeId!!,DocumentType.ALL)

    override fun getRevenue(id: String) = dataSource.getRevenue(UserInfor.getInstance().storeId!!, id,DocumentType.SINGLE)

    override fun insert(revenue: Revenue) =
        dataSource.createRevenue(UserInfor.getInstance().storeId!!, revenue)

    override fun update(revenue: Revenue) {
        //TODO: get update function
    }

    override fun delete(revenue: Revenue) =
        dataSource.deleteRevenue(UserInfor.getInstance().storeId!!, revenue.id)

    override fun deleteAllRevenues() {
        //TODO: get complete all function
    }
}