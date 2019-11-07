package fit.tdc.edu.vn.cafemanagement.data.repository.impl

import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.FireBaseAPI
import fit.tdc.edu.vn.cafemanagement.data.extension.DocumentType
import fit.tdc.edu.vn.cafemanagement.data.model.table.Table
import fit.tdc.edu.vn.cafemanagement.data.model.user.UserInfor
import fit.tdc.edu.vn.cafemanagement.data.repository.TableRepositoryAPI

class TableRepository(val dataSource: FireBaseAPI): TableRepositoryAPI {

    override fun getAllTables() = dataSource.getTableList(UserInfor.getInstance().storeId!!,DocumentType.ALL)

    override fun getTable(id: String) = dataSource.getTable(UserInfor.getInstance().storeId!!, id, DocumentType.ALL)

    override fun insert(table: Table) =
        dataSource.createTable(UserInfor.getInstance().storeId!!, table)

    override fun update(table: Table) =
        dataSource.modifyTable(UserInfor.getInstance().storeId!!, table)

    override fun delete(table: Table) =
        dataSource.deleteTable(UserInfor.getInstance().storeId!!, table.id)
}