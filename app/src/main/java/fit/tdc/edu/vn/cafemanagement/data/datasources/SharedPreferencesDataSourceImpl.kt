package fit.tdc.edu.vn.cafemanagement.data.datasources

import android.content.SharedPreferences
import javax.inject.Inject

interface SharedPreferencesDataSource {

}

class SharedPreferencesDataSourceImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
): SharedPreferencesDataSource {
}
