package fit.tdc.edu.vn.cafemanagement.data.repository

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import fit.tdc.edu.vn.cafemanagement.data.model.Unit.UnitDAO
import fit.tdc.edu.vn.cafemanagement.data.model.Unit.UnitDatabase


class UnitRepository (application: Application) {

    private var unitDAO: UnitDAO

    private var allUnits: LiveData<List<fit.tdc.edu.vn.cafemanagement.data.model.Unit.Unit>>

    init {
        val database: UnitDatabase = UnitDatabase.getInstance(
            application.applicationContext
        )!!
        unitDAO = database.unitDAO()
        allUnits = unitDAO.getAllUnits()
    }

    fun insert(unit: fit.tdc.edu.vn.cafemanagement.data.model.Unit.Unit) {
        val insertUnitAsyncTask = InsertUnitAsyncTask(unitDAO).execute(unit)
    }

    fun update(unit: fit.tdc.edu.vn.cafemanagement.data.model.Unit.Unit) {
        val updateUnitAsyncTask = UpdateUnitAsyncTask(unitDAO).execute(unit)
    }


    fun delete(unit: fit.tdc.edu.vn.cafemanagement.data.model.Unit.Unit) {
        val deleteUnitAsyncTask = DeleteUnitAsyncTask(unitDAO).execute(unit)
    }

    fun deleteAllUnits() {
        val deleteAllUnitsAsyncTask = DeleteAllUnitsAsyncTask(
            unitDAO
        ).execute()
    }

    fun getAllUnits(): LiveData<List<fit.tdc.edu.vn.cafemanagement.data.model.Unit.Unit>> {
        return allUnits
    }

    companion object {
        private class InsertUnitAsyncTask(unitDAO: UnitDAO) : AsyncTask<fit.tdc.edu.vn.cafemanagement.data.model.Unit.Unit, Unit, Unit>() {
            val unitDAO = unitDAO

            override fun doInBackground(vararg p0: fit.tdc.edu.vn.cafemanagement.data.model.Unit.Unit?) {
                unitDAO.insert(p0[0]!!)
            }
        }

        private class UpdateUnitAsyncTask(unitDAO: UnitDAO) : AsyncTask<fit.tdc.edu.vn.cafemanagement.data.model.Unit.Unit, Unit, Unit>() {
            val unitDAO = unitDAO
            override fun doInBackground(vararg p0: fit.tdc.edu.vn.cafemanagement.data.model.Unit.Unit?) {
                unitDAO.update(p0[0]!!)
            }
        }

        private class DeleteUnitAsyncTask(unitDAO: UnitDAO) : AsyncTask<fit.tdc.edu.vn.cafemanagement.data.model.Unit.Unit, Unit, Unit>() {
            val unitDAO = unitDAO

            override fun doInBackground(vararg p0: fit.tdc.edu.vn.cafemanagement.data.model.Unit.Unit?) {
                unitDAO.delete(p0[0]!!)
            }
        }

        private class DeleteAllUnitsAsyncTask(unitDAO: UnitDAO) : AsyncTask<fit.tdc.edu.vn.cafemanagement.data.model.Unit.Unit, Unit, Unit>() {
            val unitDAO = unitDAO

            override fun doInBackground(vararg p0: fit.tdc.edu.vn.cafemanagement.data.model.Unit.Unit?) {
                unitDAO.deleteAllUnits()
            }
        }
    }
}