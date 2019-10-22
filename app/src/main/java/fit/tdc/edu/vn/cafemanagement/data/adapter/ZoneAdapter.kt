package fit.tdc.edu.vn.cafemanagement.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.model.zone.Zone
import fit.tdc.edu.vn.cafemanagement.fragment.zone.ZoneListFragmentDirections
import kotlinx.android.synthetic.main.item_zone.view.*

class ZoneAdapter : ListAdapter<Zone, ZoneHolder>(Zone.DIFF_CALLBACK) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ZoneHolder {
        val itemView: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_zone, parent, false)
        return ZoneHolder(itemView)
    }

    override fun onBindViewHolder(
        holder: ZoneHolder,
        position: Int
    ) {
        val currentZone: Zone = getItem(position)
        holder.bind(currentZone)
    }

    fun getZoneAt(
        position: Int
    ): Zone {
        return getItem(position)
    }
}

class ZoneHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {
    private fun navigateToView(
        zone: Zone,
        it: View
    ) {
        val direction =
            ZoneListFragmentDirections.zoneViewAction(zone.id)
        it.findNavController().navigate(direction)
    }

    fun bind(item: Zone) {
        itemView.txt_name.text = item.name

        itemView.setOnClickListener {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                navigateToView(item, it)
            }
        }
    }
}