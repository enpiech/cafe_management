package fit.tdc.edu.vn.cafemanagement.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.model.unit.Unit
import kotlinx.android.synthetic.main.unit_list_item.view.*

class UnitAdapter : ListAdapter<Unit, UnitAdapter.UnitHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Unit>() {
            override fun areItemsTheSame(oldItem: Unit, newItem: Unit): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Unit, newItem: Unit): Boolean {
                return oldItem.name == newItem.name
            }
        }
    }

    private var listener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UnitHolder {
        val itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.unit_list_item, parent, false)
        return UnitHolder(itemView)
    }

    override fun onBindViewHolder(holder: UnitHolder, position: Int) {
        val currentUnit: Unit = getItem(position)
        holder.textUnit.text = currentUnit.name
    }

    fun getUnitAt(position: Int): Unit {
        return getItem(position)
    }

    inner class UnitHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener?.onItemClick(getItem(position))
                }
            }
        }

        var textUnit: TextView = itemView.txt_unit
    }

    interface OnItemClickListener {
        fun onItemClick(unit: Unit)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
}
