package fit.tdc.edu.vn.cafemanagement.data.model

abstract class FormState(
    var isDataValid: Boolean = false
) {
    enum class Type {
        ADD,
        VIEW,
        MODIFY
    }
}