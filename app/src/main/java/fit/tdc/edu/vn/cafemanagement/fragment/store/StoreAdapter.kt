package fit.tdc.edu.vn.cafemanagement.fragment.store

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.model.store.Store
import kotlinx.android.synthetic.main.item_store.view.*

class StoreAdapter : ListAdapter<Store, RecyclerView.ViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Store>() {
            override fun areItemsTheSame(oldItem: Store, newItem: Store): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Store, newItem: Store): Boolean {
                return oldItem.name == newItem.name
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StoreHolder {
        val itemView: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_store, parent, false)
        return StoreHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentStore: Store = getItem(position)
        (holder as StoreHolder).bind(currentStore)
    }
}

class StoreHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {
    private fun navigateToView(
        store: Store,
        it: View
    ) {
        val direction =
            StoreListFragmentDirections.actionViewStore(storeId = store.id, title = "Chỉnh sửa cửa hàng")
        it.findNavController().navigate(direction)
    }

    fun bind(item: Store) {
        itemView.txt_store_name.text = item.name
        itemView.txt_store_address.text = item.address

        itemView.setOnClickListener {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                navigateToView(item, it)
            }
        }
    }
}