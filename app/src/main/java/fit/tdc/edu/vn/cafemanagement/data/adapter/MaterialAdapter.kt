package fit.tdc.edu.vn.cafemanagement.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.model.material.Material
import fit.tdc.edu.vn.cafemanagement.fragment.material_list.MaterialListFragmentDirections
import kotlinx.android.synthetic.main.item_material.view.*

class MaterialAdapter : ListAdapter<Material, RecyclerView.ViewHolder>(Material.DIFF_CALLBACK) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MaterialHolder {
        val itemView: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_material, parent, false)
        return MaterialHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentMaterial: Material = getItem(position)
        (holder as MaterialHolder).bind(currentMaterial)
    }

    inner class MaterialHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {
        private fun navigateToView(
            material: Material,
            it: View
        ) {
            val direction =
                MaterialListFragmentDirections.materialViewAction(material.id)
            it.findNavController().navigate(direction)
        }

        fun bind(item: Material) {
            itemView.txt_material.text = item.name

            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    navigateToView(item, it)
                }
            }
        }
    }
}