package fit.tdc.edu.vn.cafemanagement.data.adapter

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
import fit.tdc.edu.vn.cafemanagement.fragment.table_waiter.TableWaiterListFragmentDirections
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
            LayoutInflater.from(parent.context).inflate(R.layout.item_table, parent, false)
        return TableWaiterHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentTable: Table = getItem(position)
        (holder as TableWaiterHolder).bind(currentTable)
    }

    inner class TableWaiterHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Table) {
            itemView.btn_item_table.text = item.name
            when {
                item.state == Table.State.FREE -> {
                    itemView.btn_item_table.setBackgroundColor(Color.GREEN)
                    itemView.btn_item_table.setTextColor(Color.BLACK)
                }
                item.state == Table.State.ORDERING -> {
                    itemView.btn_item_table.setBackgroundColor(Color.RED)
                    itemView.btn_item_table.setTextColor(Color.BLACK)
                }
                item.state == Table.State.BOOKED -> {
                    itemView.btn_item_table.setBackgroundColor(Color.YELLOW)
                    itemView.btn_item_table.setTextColor(Color.BLACK)
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
                        TableWaiterListFragmentDirections.viewCurrentOrderAction(
                            tableId = table.id,
                            paymentId = null,
                            title = "Đặt món" )
                }
                else -> {
                    direction =
                        TableWaiterListFragmentDirections.viewCurrentOrderAction(
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
