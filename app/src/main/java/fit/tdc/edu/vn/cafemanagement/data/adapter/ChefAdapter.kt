package fit.tdc.edu.vn.cafemanagement.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.model.order.Order
import kotlinx.android.synthetic.main.item_chef.view.*

class ChefAdapter(
    var onComplete: ((order: Order) -> Unit)? = null,
    val onItemClick: ((order: Order, itemView: View) -> Unit)? = null
) : ListAdapter<Order, RecyclerView.ViewHolder>(DIFF_CALLBACK){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_chef, parent, false)
        return ChefHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val current = getItem(position)
        (holder as ChefHolder).bind(current)
    }
    inner class ChefHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Order) {
            itemView.chef_name.text = item.name
            itemView.chef_amount.text = item.amount.toString()
//            if(chef.state!!.equals(Chef.State.DONE)){
//                Log.d("test", "Done " + chef.state)
//                itemView.chef_name.text = chef.name
//                itemView.chef_amount.text = chef.amount.toString()+"(Đã xong)"
//            }else if(chef.state!!.equals(Chef.State.DOING)){
//                itemView.chef_name.text = chef.name
//                itemView.chef_amount.text = chef.amount.toString()+"(Chưa xong)"
//            }
            itemView.btn_done.setOnClickListener {
                onComplete?.invoke(item)
                //TODO : Hiện thông báo: Bạn đã làm món này hay chưa ( Nguyên liệu của món)
            }
            itemView.setOnClickListener {
                onItemClick?.invoke(item, it)
                //val position = adapterPosition
                Snackbar.make(it, "Bạn đã bấm vào món " + item.name,
                    Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
                //TODO : Hiện thông báo: Bạn đã làm món này hay chưa ( Nguyên liệu của món)
            }
        }
    }
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Order>() {
            override fun areItemsTheSame(oldItem: Order, newItem: Order): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Order, newItem: Order): Boolean {
                return oldItem.name == newItem.name
            }
        }
    }
}