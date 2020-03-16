package fit.tdc.edu.vn.cafemanagement.data.repository.revenue

import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.revenue.RevenueRemoteDataSource
import fit.tdc.edu.vn.cafemanagement.data.extension.DocumentType
import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.Revenue
import fit.tdc.edu.vn.cafemanagement.data.model.user.UserInfor

class RevenueRepositoryImpl(
    val dataSource: RevenueRemoteDataSource
) :
    RevenueRepository {

    override fun getAllRevenues() =
        dataSource.readRevenues(UserInfor.getInstance().storeId!!, DocumentType.ALL)

    override fun getRevenue(id: String) =
        dataSource.readRevenue(UserInfor.getInstance().storeId!!, id, DocumentType.SINGLE)

    override fun insert(revenue: Revenue) =
        dataSource.createRevenue(UserInfor.getInstance().storeId!!, revenue)

    override fun update(revenue: Revenue) {
        dataSource.updateRevenue(UserInfor.getInstance().storeId!!, revenue)
    }

    override fun delete(revenue: Revenue) =
        dataSource.deleteRevenue(UserInfor.getInstance().storeId!!, revenue.id)

    override fun deleteAllRevenues() {
        //TODO: get complete all function
    }
}