package fit.tdc.edu.vn.cafemanagement.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.Material
import kotlinx.android.synthetic.main.item_material.view.*

class MaterialAdapter : ListAdapter<Material, MaterialAdapter.MaterialHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Material>() {
            override fun areItemsTheSame(oldItem: Material, newItem: Material): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Material, newItem: Material): Boolean {
                return oldItem.name == newItem.name
            }
        }
    }

    private var listener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MaterialHolder {
        val itemView: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_material, parent, false)
        return MaterialHolder(itemView)
    }

    override fun onBindViewHolder(holder: MaterialHolder, position: Int) {
        val currentMaterial: Material = getItem(position)

        holder.textMaterial.text = currentMaterial.name
    }

    fun getMaterialAt(position: Int): Material {
        return getItem(position)
    }

    inner class MaterialHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener?.onItemClick(getItem(position))
                }
            }
        }

        val textMaterial: TextView = itemView.txt_material
    }

    interface OnItemClickListener {
        fun onItemClick(material: Material)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
}