package fit.tdc.edu.vn.cafemanagement.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.Zone
import kotlinx.android.synthetic.main.unit_list_item.view.*

class ZoneAdapter : ListAdapter<Zone, ZoneAdapter.ZoneHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Zone>() {
            override fun areItemsTheSame(oldItem: Zone, newItem: Zone): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Zone, newItem: Zone): Boolean {
                return oldItem.name == newItem.name
            }
        }
    }

    private var listener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ZoneHolder {
        val itemView: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_zone, parent, false)
        return ZoneHolder(itemView)
    }

    override fun onBindViewHolder(holder: ZoneHolder, position: Int) {
        val currentUnit: Zone = getItem(position)
        holder.textUnit.text = currentUnit.name
    }

    fun getUnitAt(position: Int): Zone {
        return getItem(position)
    }

    inner class ZoneHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
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
        fun onItemClick(zone: Zone)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
}