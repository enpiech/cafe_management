package fit.tdc.edu.vn.cafemanagement.data.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.model.table.Table
import fit.tdc.edu.vn.cafemanagement.fragment.table.TableListFragmentDirections
import kotlinx.android.synthetic.main.item_table.view.*

class TableAdapter : ListAdapter<Table, TableAdapter.TableHolder>(DIFF_CALLBACK) {

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TableHolder {
        val itemView: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_table, parent, false)
        return TableHolder(itemView)
    }

    override fun onBindViewHolder(holder: TableHolder, position: Int) {
        val currentTable: Table = getItem(position)
        holder.bind(currentTable)
    }

    fun getTableAt(position: Int): Table {
        return getItem(position)
    }

    inner class TableHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private fun navigateToView(
            table: Table,
            it: View
        ) {
            val direction =
                TableListFragmentDirections.tableViewAction(tableId = table.id, title = "Chỉnh sửa" )
            it.findNavController().navigate(direction)
        }

        fun bind(item: Table) {
            itemView.btn_item_table.text = item.name

            itemView.setOnClickListener {
                Log.d("test", "ssss")
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    navigateToView(item, it)
                }
            }
        }
    }
}