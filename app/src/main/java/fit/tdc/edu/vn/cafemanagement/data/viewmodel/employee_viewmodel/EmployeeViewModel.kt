package fit.tdc.edu.vn.cafemanagement.data.viewmodel.employee_viewmodel

import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.Employee
import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.Unit
import fit.tdc.edu.vn.cafemanagement.data.repository.EmployeeRepsitoryAPI
import fit.tdc.edu.vn.cafemanagement.data.repository.impl.EmployeeRepository

class EmployeeViewModel (private val employeeRepository: EmployeeRepsitoryAPI) {

    private var allEmployees = employeeRepository.getAllEmployees()

    fun insert(employee: Employee) {
        employeeRepository.insert(employee)
    }

    fun update(employee: Employee) {
        employeeRepository.update(employee)
    }

    fun delete(employee: Employee) {
        employeeRepository.delete(employee)
    }

    fun deleteAllEmployee() {
        employeeRepository.deleteAllEmployees()
    }

    fun getAllEmployees() = allEmployees
}