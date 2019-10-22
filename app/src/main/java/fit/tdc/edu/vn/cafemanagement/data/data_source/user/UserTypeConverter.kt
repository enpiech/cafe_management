package fit.tdc.edu.vn.cafemanagement.data.data_source.user

import androidx.room.TypeConverter
import com.google.firebase.Timestamp
import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.Gender
import fit.tdc.edu.vn.cafemanagement.data.model.user.User
import java.util.*

object UserTypeConverter {

//    @TypeConverter
//    @JvmStatic
//    fun fromTimestamp(value: Long?): Date? = Date(value!!)
//    @TypeConverter
//    @JvmStatic
//    fun toTimestamp(timestamp: Date?): Long? = Timestamp(timestamp!!).seconds

    @TypeConverter
    @JvmStatic
    fun fromTimestamp(value: Timestamp?): Date? = Date(value!!.seconds)
    @TypeConverter
    @JvmStatic
    fun toTimestamp(timestamp: Date?): Timestamp? = Timestamp(timestamp!!)


    @TypeConverter
    @JvmStatic
    fun genderToString(gender: Gender): String? {
        return gender.name
    }
    @TypeConverter
    @JvmStatic
    fun stringToGender(gender: String): Gender? {
        return Gender.valueOf(gender)
    }

    @TypeConverter
    @JvmStatic
    fun typeToString(type: User.Type): String? {
        return type.name
    }
    @TypeConverter
    @JvmStatic
    fun stringToRole(type: String): User.Type? {
        return User.Type.valueOf(type)
    }
}