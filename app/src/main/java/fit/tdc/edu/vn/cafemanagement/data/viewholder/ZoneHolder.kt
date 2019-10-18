package fit.tdc.edu.vn.cafemanagement.data.viewholder

import android.view.View
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import fit.tdc.edu.vn.cafemanagement.data.model.zone.Zone
import fit.tdc.edu.vn.cafemanagement.ui.zone.ZoneListFragmentDirections
import kotlinx.android.synthetic.main.item_zone.view.*

class ZoneHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {
    private fun navigateToView(
        zone: Zone,
        it: View
    ) {
        val direction =
            ZoneListFragmentDirections.viewZoneAction(
                zoneId = zone.id
            )
        it.findNavController().navigate(direction)
    }

    fun bind(item: Zone) {
        itemView.txtZoneName.text = item.name

        itemView.setOnClickListener {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                navigateToView(item, it)
            }
        }
    }
}