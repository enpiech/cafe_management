package fit.tdc.edu.vn.cafemanagement.data.repository

import com.google.firebase.firestore.DocumentReference
import fit.tdc.edu.vn.cafemanagement.data.extension.CollectionLiveData
import fit.tdc.edu.vn.cafemanagement.data.extension.DocumentLiveData
import fit.tdc.edu.vn.cafemanagement.data.extension.TaskLiveData
import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.Category
import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.Employee

interface EmployeeRepsitoryAPI {
    fun getAllEmployees(): CollectionLiveData<Employee>
    fun getEmployee(id: String): DocumentLiveData<Employee>
    fun insert(employee: Employee): TaskLiveData<DocumentReference>
    fun update(employee: Employee)
    fun delete(employee: Employee): TaskLiveData<Void>
    fun deleteAllEmployees()
}