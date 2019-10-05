package fit.tdc.edu.vn.cafemanagement.data.repository

import fit.tdc.edu.vn.cafemanagement.data.data_source.FireBaseAPI
import fit.tdc.edu.vn.cafemanagement.data.extension.CollectionLiveData
import fit.tdc.edu.vn.cafemanagement.data.extension.DocumentLiveData
import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.Unit


class UnitRepository ( val dataSource: FireBaseAPI) {

    fun getAllUnits() : CollectionLiveData<Unit> {
        return dataSource.getUnitList("EfzspceETNgWk56YDOOt")
    }

    fun getUnitById(id: String) : DocumentLiveData<Unit> {
        return dataSource.getUnit("EfzspceETNgWk56YDOOt", id)
    }


    fun insert(unit: Unit) {
        dataSource.createUnit("EfzspceETNgWk56YDOOt", unit)
    }

    fun update(unit: Unit) {
        //TODO: get update function
    }

    fun delete(unit: Unit) {
        dataSource.deleteUnit("EfzspceETNgWk56YDOOt", unit.id!!)
    }

    fun deleteAllUnits() {
        //TODO: get delete all function
    }
}