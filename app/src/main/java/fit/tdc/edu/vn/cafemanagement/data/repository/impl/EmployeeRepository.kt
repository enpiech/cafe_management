package fit.tdc.edu.vn.cafemanagement.data.repository.impl

import fit.tdc.edu.vn.cafemanagement.data.data_source.FireBaseAPI
import fit.tdc.edu.vn.cafemanagement.data.extension.CollectionLiveData
import fit.tdc.edu.vn.cafemanagement.data.extension.DocumentLiveData
import fit.tdc.edu.vn.cafemanagement.data.extension.DocumentType
import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.Category
import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.Employee
import fit.tdc.edu.vn.cafemanagement.data.repository.CategoryRepsitoryAPI
import fit.tdc.edu.vn.cafemanagement.data.repository.EmployeeRepsitoryAPI

class EmployeeRepository(val dataSource: FireBaseAPI) : EmployeeRepsitoryAPI{

    override fun getAllEmployees() = dataSource.getEmployeeList(DocumentType.ALL)

    override fun getEmployee(id: String) = dataSource.getEmployee(id,DocumentType.SINGLE)

    override fun insert(employee: Employee) =
        dataSource.createEmployee(employee)

    override fun update(employee: Employee) {
        //TODO: get update function
    }

    override fun delete(employee: Employee) =
        dataSource.deleteCategory("EfzspceETNgWk56YDOOt", employee.id!!)

    override fun deleteAllEmployees () {
        //TODO: get delete all function
    }
}