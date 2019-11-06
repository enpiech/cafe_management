package fit.tdc.edu.vn.cafemanagement.data.model.user

import javax.inject.Singleton

public class UserInfor private constructor() {
    public var storeId: String? = null
    public var userType: Int? = null

    init {
        storeId = "EfzspceETNgWk56YDOOt"
        userType = -1
    }

    private object Holder { val INSTANCE = UserInfor() }

    companion object {
        @JvmStatic
        fun getInstance(): UserInfor{
            return Holder.INSTANCE
        }
    }
}