package fit.tdc.edu.vn.cafemanagement.fragment.zone

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.model.zone.Zone
import kotlinx.android.synthetic.main.item_zone.view.*

class ZoneAdapter : ListAdapter<Zone, RecyclerView.ViewHolder>(Zone.DIFF_CALLBACK) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ZoneHolder {
        val itemView: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_zone, parent, false)
        return ZoneHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentZone: Zone = getItem(position)
        (holder as ZoneHolder).bind(currentZone)
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
            ZoneListFragmentDirections.actionViewZone(zone.id, "Chỉnh sửa khu vực")
        it.findNavController().navigate(direction)
    }

    fun bind(item: Zone) {
        itemView.txt_zone_name.text = item.name

        itemView.setOnClickListener {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                navigateToView(item, it)
            }
        }
    }
}