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
        return dataSource.getUnitList("EfzspceETNgWk56YDOOt")
    }

    fun getUnitById(id: String) : LiveData<Unit?> {
        return dataSource.getUnit("EfzspceETNgWk56YDOOt", id)
    }


    fun insert(unit: Unit) {
        dataSource.createUnit("EfzspceETNgWk56YDOOt", unit)
    }

    fun update(unit: Unit) {
        //TODO: get update function
    }

    fun delete(unit: Unit) {
        dataSource.deleteUnit("EfzspceETNgWk56YDOOt", unit.id)
    }

    fun deleteAllUnits() {
        //TODO: get delete all function
    }
}