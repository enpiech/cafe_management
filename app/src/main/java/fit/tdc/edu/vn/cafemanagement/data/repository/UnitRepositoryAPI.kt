package fit.tdc.edu.vn.cafemanagement.data.repository

import androidx.lifecycle.LiveData
import fit.tdc.edu.vn.cafemanagement.data.model.Unit

interface UnitRepositoryAPI {
    fun getUnitMap(): LiveData<HashMap<String, Unit>>
}