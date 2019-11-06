package fit.tdc.edu.vn.cafemanagement.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.model.category.Category
import fit.tdc.edu.vn.cafemanagement.fragment.category.CategoryListFragmentDirections
import kotlinx.android.synthetic.main.item_table_order_waiter.view.*

class OrderAdapter : ListAdapter<Category, RecyclerView.ViewHolder>(DIFF_CALLBACK) {
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentCategory: Category = getItem(position)
        (holder as OrderHolder).bind(currentCategory)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OrderHolder {
        val itemView: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_table_order_waiter, parent, false)
        return OrderHolder(itemView)
    }
}

class OrderHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {
    private fun navigateToView(
        category: Category,
        it: View
    ) {
        val direction =
            CategoryListFragmentDirections.categoryViewAction(category.id)
        it.findNavController().navigate(direction)
    }

    fun bind(item: Category) {
        var soluong = 0
        if (soluong == 0){
            soluong = 0
            itemView.txt_so_luong.text = soluong.toString()
            itemView.btnMinus.isEnabled = false
        }else{
            itemView.txt_so_luong.text = soluong.toString()
            itemView.btnMinus.isEnabled = true
        }
        itemView.txt_name_table_order_waiter.text = item.name
        itemView.btnPlus.setOnClickListener {
            soluong+=1
            itemView.btnMinus.isEnabled = true
            itemView.txt_so_luong.text = soluong.toString()
        }
        itemView.btnMinus.setOnClickListener {
            soluong-=1
            if (soluong == 0){
                soluong = 0
                itemView.txt_so_luong.text = soluong.toString()
                itemView.btnMinus.isEnabled = false
            }else{
                itemView.txt_so_luong.text = soluong.toString()
                itemView.btnMinus.isEnabled = true
            }
        }
        itemView.setOnClickListener {
            Snackbar.make(it, "Bạn đang bấm vào món " + item.name,
                Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }
}

private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Category>() {
    override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem.name == newItem.name
    }
}