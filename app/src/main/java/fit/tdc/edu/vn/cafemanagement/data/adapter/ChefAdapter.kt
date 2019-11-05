package fit.tdc.edu.vn.cafemanagement.data.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.model.chef.Chef
import kotlinx.android.synthetic.main.item_chef.view.*

class ChefAdapter : ListAdapter<Chef, RecyclerView.ViewHolder>(DIFF_CALLBACK){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_chef, parent, false)
        return ChefHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentChef: Chef = getItem(position)
        (holder as ChefHolder).bind(currentChef)
    }
    inner class ChefHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(chef: Chef) {
            itemView.chef_name.text = chef.name
            itemView.chef_amount.text = chef.amount.toString()
//            if(chef.state!!.equals(Chef.State.DONE)){
//                Log.d("test", "Done " + chef.state)
//                itemView.chef_name.text = chef.name
//                itemView.chef_amount.text = chef.amount.toString()+"(Đã xong)"
//            }else if(chef.state!!.equals(Chef.State.DOING)){
//                itemView.chef_name.text = chef.name
//                itemView.chef_amount.text = chef.amount.toString()+"(Chưa xong)"
//            }
            itemView.btn_done.setOnClickListener {
                Snackbar.make(it, "Bạn đã bấm vào btn món " + chef.state,
                    Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
                //TODO : Hiện thông báo: Bạn đã làm món này hay chưa ( Nguyên liệu của món)
            }
            itemView.setOnClickListener {
                //val position = adapterPosition
                Snackbar.make(it, "Bạn đã bấm vào món " + chef.name,
                    Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
                //TODO : Hiện thông báo: Bạn đã làm món này hay chưa ( Nguyên liệu của món)
            }
        }
    }
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Chef>() {
            override fun areItemsTheSame(oldItem: Chef, newItem: Chef): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Chef, newItem: Chef): Boolean {
                return oldItem.name == newItem.name
            }
        }
    }
}