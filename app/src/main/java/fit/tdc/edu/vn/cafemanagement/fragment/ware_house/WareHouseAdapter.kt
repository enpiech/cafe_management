package fit.tdc.edu.vn.cafemanagement.fragment.ware_house

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import fit.tdc.edu.vn.cafemanagement.data.model.ware_house.WareHouse

class WareHouseAdapter: ListAdapter<WareHouse, RecyclerView.ViewHolder>(DIFF_CALLBACK){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<WareHouse>() {
            override fun areItemsTheSame(oldItem: WareHouse, newItem: WareHouse): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: WareHouse, newItem: WareHouse): Boolean {
                return oldItem.name == newItem.name
            }
        }
    }

}