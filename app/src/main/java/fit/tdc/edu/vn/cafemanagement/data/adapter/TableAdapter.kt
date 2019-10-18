package fit.tdc.edu.vn.cafemanagement.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.Table
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

    private var listener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TableHolder {
        val itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.item_table, parent, false)
        return TableHolder(itemView)
    }

    override fun onBindViewHolder(holder: TableHolder, position: Int) {
        val currentTable: Table = getItem(position)
        holder.nameTable.text = currentTable.name
    }

    fun getTableAt(position: Int): Table {
        return getItem(position)
    }

    inner class TableHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener?.onItemClick(getItem(position))
                }
            }
        }

        var nameTable: Button = itemView.btn_item_table
    }

    interface OnItemClickListener {
        fun onItemClick(table: Table)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
}