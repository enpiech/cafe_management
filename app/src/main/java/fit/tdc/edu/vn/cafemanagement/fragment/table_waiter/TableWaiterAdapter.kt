package fit.tdc.edu.vn.cafemanagement.fragment.table_waiter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.model.table.Table
import kotlinx.android.synthetic.main.item_table.view.*

class TableWaiterAdapter : ListAdapter<Table, RecyclerView.ViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Table>() {
            override fun areItemsTheSame(oldItem: Table, newItem: Table): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Table, newItem: Table): Boolean {
                return oldItem.name == newItem.name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TableWaiterHolder {
        val itemView: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_table_waiter, parent, false)
        return TableWaiterHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentTable: Table = getItem(position)
        (holder as TableWaiterHolder).bind(currentTable)
    }

    inner class TableWaiterHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Table) {
            itemView.txt_table_name.text = item.name
            when {
                item.state == Table.State.FREE -> {
                    itemView.txt_table_name.setBackgroundColor(Color.parseColor("#00C853"))
                    itemView.txt_table_name.setTextColor(Color.BLACK)
                }
                item.state == Table.State.ORDERING -> {
                    itemView.txt_table_name.setBackgroundColor(Color.parseColor("#FFEA00"))
                    itemView.txt_table_name.setTextColor(Color.BLACK)
                }
                item.state == Table.State.BOOKED -> {
                    itemView.txt_table_name.setBackgroundColor(Color.parseColor("#EF5350"))
                    itemView.txt_table_name.setTextColor(Color.BLACK)
                }
                item.state == Table.State.BUSY -> {
                    itemView.txt_table_name.setBackgroundColor(Color.parseColor("#78909C"))
                    itemView.txt_table_name.setTextColor(Color.WHITE)
                }
            }
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    navigateToView(item, it)
                }
            }
        }

        private fun navigateToView(
            table: Table,
            it: View
        ) {
            val direction: NavDirections
            when (table.state) {
                Table.State.FREE, null -> {
                    direction =
                        TableWaiterListFragmentDirections.actionViewPayment(
                            tableId = table.id,
                            paymentId = null,
                            title = "Đặt món" )
                }
                else -> {
                    direction =
                        TableWaiterListFragmentDirections.actionViewPayment(
                            tableId = table.id,
                            paymentId = table.paymentId,
                            title = "Thanh toán"
                        )
                }
            }
            it.findNavController().navigate(direction)
        }
    }
}
