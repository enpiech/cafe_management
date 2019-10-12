package fit.tdc.edu.vn.cafemanagement.data.data_source.local.category

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.Category

@Database(entities = [Category::class], version = 1)
abstract class CategoryDatabase : RoomDatabase() {

    abstract fun categoryDAO() : CategoryDAO


    companion object {
        private var instance: CategoryDatabase? = null

        fun getInstance(context: Context): CategoryDatabase? {
            if (instance == null) {
                synchronized(CategoryDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        CategoryDatabase::class.java, "notes_database"
                    )
                        .fallbackToDestructiveMigration()
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
                CategoryDatabase.PopulateDbAsyncTask(
                    instance
                )
                    .execute()
            }
        }
    }

    class PopulateDbAsyncTask(db: CategoryDatabase?) : AsyncTask<Category, Category, Category>() {

        private val categoryDAO = db?.categoryDAO()

        override fun doInBackground(vararg p0: Category?) {
            categoryDAO?.insert(fit.tdc.edu.vn.cafemanagement.data.model.kotlin.Unit("KG"))
            categoryDAO?.insert(fit.tdc.edu.vn.cafemanagement.data.model.kotlin.Unit("Tạ"))
            categoryDAO?.insert(fit.tdc.edu.vn.cafemanagement.data.model.kotlin.Unit("Tấn"))
        }
    }

}