package fit.tdc.edu.vn.cafemanagement.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.model.category.Category
import fit.tdc.edu.vn.cafemanagement.fragment.category.CategoryListFragmentDirections
import kotlinx.android.synthetic.main.item_zone.view.*

class CategoryAdapter : ListAdapter<Category, RecyclerView.ViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryHolder {
        val itemView: View =
                LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return CategoryHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentCategory: Category = getItem(position)
        (holder as CategoryHolder).bind(currentCategory)
    }
}

class CategoryHolder(
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
        itemView.txt_name.text = item.name

        itemView.setOnClickListener {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                navigateToView(item, it)
            }
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