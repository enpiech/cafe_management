package fit.tdc.edu.vn.cafemanagement.data.model.user

class UserInfor private constructor() {
    var storeId: String? = null
    var userType: Int? = null

    init {
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