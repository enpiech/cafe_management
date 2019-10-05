package fit.tdc.edu.vn.cafemanagement.data.repository

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import fit.tdc.edu.vn.cafemanagement.data.model.unit.Unit
import fit.tdc.edu.vn.cafemanagement.data.model.unit.UnitDAO
import fit.tdc.edu.vn.cafemanagement.data.model.unit.UnitDatabase


class UnitRepository (application: Application) {

    private var unitDAO: UnitDAO

    private var allUnits: LiveData<List<Unit>>

    init {
        val database: UnitDatabase = UnitDatabase.getInstance(
            application.applicationContext
        )!!
        unitDAO = database.unitDAO()
        allUnits = unitDAO.getAllUnits()
    }

    fun insert(unit: Unit) {
        val insertUnitAsyncTask = InsertUnitAsyncTask(unitDAO).execute(unit)
    }

    fun update(unit: Unit) {
        val updateUnitAsyncTask = UpdateUnitAsyncTask(unitDAO).execute(unit)
    }


    fun delete(unit: Unit) {
        val deleteUnitAsyncTask = DeleteUnitAsyncTask(unitDAO).execute(unit)
    }

    fun deleteAllUnits() {
        val deleteAllUnitsAsyncTask = DeleteAllUnitsAsyncTask(
            unitDAO
        ).execute()
    }

    fun getAllUnits(): LiveData<List<Unit>> {
        return allUnits
    }

    companion object {
        private class InsertUnitAsyncTask(val unitDAO: UnitDAO) : AsyncTask<Unit, Unit, kotlin.Unit>() {

            override fun doInBackground(vararg p0: Unit?) {
                unitDAO.insert(p0[0]!!)
            }
        }

        private class UpdateUnitAsyncTask(val unitDAO: UnitDAO) : AsyncTask<Unit, Unit, kotlin.Unit>() {
            override fun doInBackground(vararg p0: Unit?) {
                unitDAO.update(p0[0]!!)
            }
        }

        private class DeleteUnitAsyncTask(val unitDAO: UnitDAO) : AsyncTask<Unit, Unit, kotlin.Unit>() {

            override fun doInBackground(vararg p0: Unit?) {
                unitDAO.delete(p0[0]!!)
            }
        }

        private class DeleteAllUnitsAsyncTask(val unitDAO: UnitDAO) : AsyncTask<Unit, Unit, kotlin.Unit>() {

            override fun doInBackground(vararg p0: Unit?) {
                unitDAO.deleteAllUnits()
            }
        }
    }
}