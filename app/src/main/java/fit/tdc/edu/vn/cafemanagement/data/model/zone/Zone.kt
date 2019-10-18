package fit.tdc.edu.vn.cafemanagement.data.model.zone

import androidx.recyclerview.widget.DiffUtil
import fit.tdc.edu.vn.cafemanagement.data.model.FirestoreModel
import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.Table

data class Zone(
    var name: String? = null,
    var tablesInZone: List<Table>? = null
) : FirestoreModel() {
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Zone>() {
            override fun areItemsTheSame(oldItem: Zone, newItem: Zone): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Zone, newItem: Zone): Boolean {
                return oldItem.name == newItem.name
            }
        }
    }
}