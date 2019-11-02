package fit.tdc.edu.vn.cafemanagement.data.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.convertTo
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.model.table.Table
import fit.tdc.edu.vn.cafemanagement.fragment.table_waiter.TableWaiterListFragmentDirections
import kotlinx.android.synthetic.main.dropdown_menu_popup_item.view.*
import kotlinx.android.synthetic.main.item_table.view.*

class TableWaiterAdapter : ListAdapter<Table, RecyclerView.ViewHolder>(DIFF_CALLBACK) {
    var mSelectedItem = 0
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentTable: Table = getItem(position)
        mSelectedItem = position
        (holder as TableWaiterHolder).bind(currentTable)
    }

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

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        val currentTable: Table = getItem(position)
        if(currentTable.state == Table.State.FREE){
            Log.d("test", "OKOKOKOKOKOKO")

            //holder.itemView.btn_item_table.setBackgroundResource(R.color.green)
        }
        super.onBindViewHolder(holder, position, payloads)
    }

    inner class TableWaiterHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private fun navigateToView(
            table: Table,
            it: View
        ) {
            val direction =
                TableWaiterListFragmentDirections.tableWaiterAction(tableId = table.id, title = "Hãy đặt món" )
            Log.d("test", "Bồi bàn đã bấm vào bàn "+table.name)
            it.findNavController().navigate(direction)
        }

        @SuppressLint("ResourceAsColor")
        fun bind(item: Table) {
            itemView.btn_item_table.text = item.name
            if (item.state == Table.State.FREE) {
                itemView.btn_item_table.setBackgroundColor(Color.GREEN)
                itemView.btn_item_table.setTextColor(Color.BLACK)
            } else if (item.state == Table.State.ORDERING) {
                itemView.btn_item_table.setBackgroundColor(Color.RED)
                itemView.btn_item_table.setTextColor(Color.BLACK)
            } else if (item.state == Table.State.BOOKED) {
                itemView.btn_item_table.setBackgroundColor(Color.YELLOW)
                itemView.btn_item_table.setTextColor(Color.BLACK)
            }else{
                itemView.btn_item_table.setBackgroundColor(Color.GRAY)
                itemView.btn_item_table.setTextColor(Color.WHITE)
            }
            itemView.setOnClickListener {
                val position = adapterPosition
                Log.d("test", "position: "+position)
                if (position != RecyclerView.NO_POSITION) {
                    navigateToView(item, it)
                }
            }
        }
    }
}
