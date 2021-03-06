package fit.tdc.edu.vn.cafemanagement.fragment.order

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.model.order.Order
import kotlinx.android.synthetic.main.item_table_order_waiter.view.*
import kotlinx.android.synthetic.main.item_table_order_waiter.view.txt_order_amount
import kotlinx.android.synthetic.main.item_table_order_waiter.view.txt_order_name
import kotlinx.android.synthetic.main.item_table_order_waiter_checkout.view.*

class OrderAdapter(
    private val layoutType: LayoutType = LayoutType.SELECT,
    private val onAmountChange: ((item: Order) -> (Unit))? = null
) : ListAdapter<Order, RecyclerView.ViewHolder>(DIFF_CALLBACK) {
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentOrder = getItem(position)
        (holder as OrderHolder).bind(currentOrder, onAmountChange)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OrderHolder {
        val itemView: View =
            LayoutInflater.from(parent.context).inflate(layoutType.resId, parent, false)
        return OrderHolder(itemView, layoutType)
    }

    enum class LayoutType(
        @LayoutRes val resId: Int
    ) {
        SELECT(R.layout.item_table_order_waiter),
        CHECKOUT(R.layout.item_table_order_waiter_checkout)
    }
}

class OrderHolder(
    itemView: View,
    private val layoutType: OrderAdapter.LayoutType
) : RecyclerView.ViewHolder(itemView) {
    fun bind(
        item: Order,
        onAmountChange: ((item: Order) -> (Unit))?
    ) {
        if (layoutType == OrderAdapter.LayoutType.SELECT) {
            itemView.txt_order_amount.text = item.amount.toString()
            itemView.btn_decrease.isEnabled = (item.amount == 0)
            itemView.txt_order_name.text = item.name

            itemView.btn_increase.setOnClickListener {
                itemView.btn_decrease.isEnabled = true
                itemView.txt_order_amount.text = (++item.amount).toString()
                onAmountChange?.invoke(item)
            }

            itemView.btn_decrease.isEnabled = (item.amount > 0)
            itemView.btn_decrease.setOnClickListener {
                if (item.amount > 0) {
                    item.amount--
                }
                itemView.txt_order_amount.text = item.amount.toString()
                itemView.btn_decrease.isEnabled = (item.amount > 0)
                onAmountChange?.invoke(item)
            }
            itemView.setOnClickListener {
                Snackbar.make(
                    it, "Bạn đang bấm vào món " + item.name,
                    Snackbar.LENGTH_LONG
                )
                    .setAction("Action", null).show()
            }
        } else {
            itemView.txt_order_name.text = item.name
            itemView.txt_order_price.text = "${item.price}"
            itemView.txt_order_amount.text = item.amount.toString()
        }
    }
}

private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Order>() {
    override fun areItemsTheSame(oldItem: Order, newItem: Order): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Order, newItem: Order): Boolean {
        return oldItem.name == newItem.name
    }
}