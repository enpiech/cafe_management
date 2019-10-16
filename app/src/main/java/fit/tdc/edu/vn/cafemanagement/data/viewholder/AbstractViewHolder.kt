package fit.tdc.edu.vn.cafemanagement.data.viewholder

import android.view.View
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView

abstract class AbstractViewHolder(
    @NonNull val itemView: View
): RecyclerView.ViewHolder(itemView) {
    abstract fun setData(data: Any)
    abstract fun setActive(active: Boolean)
}