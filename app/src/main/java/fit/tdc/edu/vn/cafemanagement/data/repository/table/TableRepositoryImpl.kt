package fit.tdc.edu.vn.cafemanagement.data.repository.table

import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.table.TableRemoteDataSource
import fit.tdc.edu.vn.cafemanagement.data.extension.DocumentType
import fit.tdc.edu.vn.cafemanagement.data.model.table.Table
import fit.tdc.edu.vn.cafemanagement.data.model.user.UserInfor

class TableRepositoryImpl(
    val dataSource: TableRemoteDataSource
) : TableRepository {

    override fun getAllTables() =
        dataSource.readTables(UserInfor.getInstance().storeId!!, DocumentType.ALL)

    override fun getTable(id: String) =
        dataSource.readTable(UserInfor.getInstance().storeId!!, id, DocumentType.ALL)

    override fun insert(table: Table) =
        dataSource.createTable(UserInfor.getInstance().storeId!!, table)

    override fun update(table: Table) =
        dataSource.updateTable(UserInfor.getInstance().storeId!!, table)

    override fun delete(table: Table) =
        dataSource.deleteTable(UserInfor.getInstance().storeId!!, table.id)
}