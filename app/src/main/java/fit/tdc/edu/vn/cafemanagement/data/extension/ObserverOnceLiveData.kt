package fit.tdc.edu.vn.cafemanagement.data.extension

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> LiveData<T>.observeOnce(lifecycleOwner: LifecycleOwner, observer: Observer<T>) {
    observe(lifecycleOwner, object : Observer<T> {
        override fun onChanged(t: T?) {
            observer.onChanged(t)
            removeObserver(this)
        }
    })
}

fun <T> LiveData<T>.observeUntil(
    lifecycleOwner: LifecycleOwner,
    observer: Observer<T>,
    stopCondition: (T?) -> Boolean
) {
    observe(lifecycleOwner, object : Observer<T> {
        override fun onChanged(t: T?) {
            observer.onChanged(t)
            if (stopCondition.invoke(t)) {
                removeObserver(this)
            }
        }
    })
}

fun <T> LiveData<T>.observeUntil(
    observer: Observer<T>,
    stopCondition: (T?) -> Boolean
) {
    observeForever(object : Observer<T> {
        override fun onChanged(t: T?) {
            observer.onChanged(t)
            if (stopCondition.invoke(t)) {
                removeObserver(this)
            }
        }
    })
}