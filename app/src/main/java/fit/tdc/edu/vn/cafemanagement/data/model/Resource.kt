package fit.tdc.edu.vn.cafemanagement.data.model

sealed class Resource<out T> {
    data class Success<out T>(val data: T) : Resource<T>()
    data class Loading<out T>(val partialData: T? = null) : Resource<T>()
    data class Failure<out T>(val throwable: Throwable? = null) : Resource<T>()

    val extractData: T? get() = when (this) {
        is Success -> data
        is Loading -> partialData
        is Failure -> null
    }
}