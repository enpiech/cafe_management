package fit.tdc.edu.vn.cafemanagement.data.data_source.local.unit

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.Unit

@Database(entities = [Unit::class], version = 1)
abstract class UnitDatabase : RoomDatabase() {

    abstract fun unitDAO(): UnitDAO


    companion object {
        private var instance: UnitDatabase? = null

        fun getInstance(context: Context): UnitDatabase? {
            if (instance == null) {
                synchronized(UnitDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        UnitDatabase::class.java, "unit_database"
                    )
                        .fallbackToDestructiveMigration() // when version increments, it migrates (deletes db and creates new) - else it crashes
                        .addCallback(roomCallback)
                        .build()
                }
            }
            return instance
        }

        fun destroyInstance() {
            instance = null
        }

        private val roomCallback = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                PopulateDbAsyncTask(
                    instance
                )
                    .execute()
            }
        }
    }

    class PopulateDbAsyncTask(db: UnitDatabase?) : AsyncTask<kotlin.Unit, kotlin.Unit, kotlin.Unit>() {
        private val unitDAO = db?.unitDAO()

        override fun doInBackground(vararg p0: kotlin.Unit?) {
            unitDAO?.insert(Unit("KG"))
            unitDAO?.insert(Unit("Tạ"))
            unitDAO?.insert(Unit("Tấn"))
        }
    }

}