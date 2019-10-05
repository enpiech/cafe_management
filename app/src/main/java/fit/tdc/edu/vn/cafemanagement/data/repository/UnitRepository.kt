package fit.tdc.edu.vn.cafemanagement.data.repository

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import fit.tdc.edu.vn.cafemanagement.data.data_source.FireBaseDataSource
import fit.tdc.edu.vn.cafemanagement.data.model.Category
import fit.tdc.edu.vn.cafemanagement.data.model.Unit
import fit.tdc.edu.vn.cafemanagement.data.model.unit.UnitDAO
import fit.tdc.edu.vn.cafemanagement.data.model.unit.UnitDatabase


class UnitRepository ( val dataSource: FireBaseDataSource) {

    fun getAllUnits() : LiveData<ArrayList<Unit>> {
        return dataSource.getUnitMap().map { map: HashMap<String, Unit> ->
            ArrayList<Unit>(map.values)
        }
    }

    fun getUnitById(id: String) : LiveData<Unit?> {
        return dataSource.getUnitMap().map { map: HashMap<String, Unit> ->
            map[id] = Unit.builder().build()
            map[id]
        }
    }


    fun insert(unit: Unit) {
        //TODO: get insert function
    }

    fun update(unit: Unit) {
        //TODO: get update function
    }

    fun delete(unit: Unit) {
        //TODO: get delete function
    }

    fun deleteAllUnits() {
        //TODO: get delete all function
    }

//    private var unitDAO: UnitDAO
//
//    private var allUnits: LiveData<List<fit.tdc.edu.vn.cafemanagement.data.model.unit.Unit>>
//
//    init {
//        val database: UnitDatabase = UnitDatabase.getInstance(
//            application.applicationContext
//        )!!
//        unitDAO = database.unitDAO()
//        allUnits = unitDAO.getAllUnits()
//    }
//
//    fun insert(unit: fit.tdc.edu.vn.cafemanagement.data.model.unit.Unit) {
//        val insertUnitAsyncTask = InsertUnitAsyncTask(unitDAO).execute(unit)
//    }
//
//    fun update(unit: fit.tdc.edu.vn.cafemanagement.data.model.unit.Unit) {
//        val updateUnitAsyncTask = UpdateUnitAsyncTask(unitDAO).execute(unit)
//    }
//
//
//    fun delete(unit: fit.tdc.edu.vn.cafemanagement.data.model.unit.Unit) {
//        val deleteUnitAsyncTask = DeleteUnitAsyncTask(unitDAO).execute(unit)
//    }
//
//    fun deleteAllUnits() {
//        val deleteAllUnitsAsyncTask = DeleteAllUnitsAsyncTask(
//            unitDAO
//        ).execute()
//    }


//    companion object {
//        private class InsertUnitAsyncTask(unitDAO: UnitDAO) : AsyncTask<fit.tdc.edu.vn.cafemanagement.data.model.unit.Unit, Unit, Unit>() {
//            val unitDAO = unitDAO
//
//            override fun doInBackground(vararg p0: fit.tdc.edu.vn.cafemanagement.data.model.unit.Unit?) {
//                unitDAO.insert(p0[0]!!)
//            }
//        }
//
//        private class UpdateUnitAsyncTask(unitDAO: UnitDAO) : AsyncTask<fit.tdc.edu.vn.cafemanagement.data.model.unit.Unit, Unit, Unit>() {
//            val unitDAO = unitDAO
//            override fun doInBackground(vararg p0: fit.tdc.edu.vn.cafemanagement.data.model.unit.Unit?) {
//                unitDAO.update(p0[0]!!)
//            }
//        }
//
//        private class DeleteUnitAsyncTask(unitDAO: UnitDAO) : AsyncTask<fit.tdc.edu.vn.cafemanagement.data.model.unit.Unit, Unit, Unit>() {
//            val unitDAO = unitDAO
//
//            override fun doInBackground(vararg p0: fit.tdc.edu.vn.cafemanagement.data.model.unit.Unit?) {
//                unitDAO.delete(p0[0]!!)
//            }
//        }
//
//        private class DeleteAllUnitsAsyncTask(unitDAO: UnitDAO) : AsyncTask<fit.tdc.edu.vn.cafemanagement.data.model.unit.Unit, Unit, Unit>() {
//            val unitDAO = unitDAO
//
//            override fun doInBackground(vararg p0: fit.tdc.edu.vn.cafemanagement.data.model.unit.Unit?) {
//                unitDAO.deleteAllUnits()
//            }
//        }
//    }
}