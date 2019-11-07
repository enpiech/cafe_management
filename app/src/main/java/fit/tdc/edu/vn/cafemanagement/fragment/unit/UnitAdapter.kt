package fit.tdc.edu.vn.cafemanagement.fragment.unit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.model.unit.Unit
import kotlinx.android.synthetic.main.item_unit.view.*

class UnitAdapter : ListAdapter<Unit, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UnitHolder {
        val itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.item_unit, parent, false)
        return UnitHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentUnit: Unit = getItem(position)
        (holder as UnitHolder).bind(currentUnit)
    }

    inner class UnitHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private fun navigateToView(
            unit: Unit,
            it: View
        ) {
            val direction =
                UnitListFragmentDirections.actionViewUnit(unit.id, "Chỉnh sửa đơn vị")
            it.findNavController().navigate(direction)
        }

        fun bind(item: Unit) {
            itemView.txt_unit_name.text = item.name
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    navigateToView(item, it)
                }
            }
        }
    }
}
