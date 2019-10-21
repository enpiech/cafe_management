package fit.tdc.edu.vn.cafemanagement.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.model.zone.Zone
import fit.tdc.edu.vn.cafemanagement.data.viewholder.ZoneHolder

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