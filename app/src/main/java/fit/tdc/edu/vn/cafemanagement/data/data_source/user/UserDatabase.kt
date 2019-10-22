package fit.tdc.edu.vn.cafemanagement.data.data_source.user

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import fit.tdc.edu.vn.cafemanagement.data.model.user.User
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

@Database(entities = [User::class], version = 1)
@TypeConverters(UserTypeConverter::class)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDAO(): UserDAO

    companion object {
        private var instance: UserDatabase? = null

        fun getInstance(context: Context): UserDatabase? {
            if (instance == null) {
                synchronized(UserDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        UserDatabase::class.java, "user_database"
                    )
                        .fallbackToDestructiveMigration() // when version increments, it migrates (deletes db and creates new) - else it crashes
                        .build()
                }
            }
            return instance
        }

        fun destroyInstance() {
            instance = null
        }

    }
}