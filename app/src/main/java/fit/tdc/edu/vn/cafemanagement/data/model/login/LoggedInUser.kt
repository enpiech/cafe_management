package fit.tdc.edu.vn.cafemanagement.data.model.login

import fit.tdc.edu.vn.cafemanagement.data.extension.FirestoreModel
import fit.tdc.edu.vn.cafemanagement.data.model.user.UserType

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
data class LoggedInUser(
    val userId: String? = null,
    val displayName: String? = null,
    val type: UserType?,
    override var collectionName: String = "employees"
): FirestoreModel()
