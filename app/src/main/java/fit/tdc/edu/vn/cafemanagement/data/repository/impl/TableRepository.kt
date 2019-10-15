package fit.tdc.edu.vn.cafemanagement.data.repository.impl

import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.FireBaseAPI
import fit.tdc.edu.vn.cafemanagement.data.extension.DocumentType
import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.Table
import fit.tdc.edu.vn.cafemanagement.data.repository.TableRepositoryAPI

class TableRepository(val dataSource: FireBaseAPI):
    TableRepositoryAPI {

    override fun getAllTables() = dataSource.getTableList("EfzspceETNgWk56YDOOt",DocumentType.ALL)

    override fun getTable(id: String) = dataSource.getTable("EfzspceETNgWk56YDOOt", id,DocumentType.SINGLE)

    override fun insert(table: Table) =
        dataSource.createTable("EfzspceETNgWk56YDOOt", table)

    override fun update(table: Table) {
        dataSource.modifyTable("EfzspceETNgWk56YDOOt", table)
    }

    override fun delete(table: Table) =
        dataSource.deleteTable("EfzspceETNgWk56YDOOt", table.id)
}