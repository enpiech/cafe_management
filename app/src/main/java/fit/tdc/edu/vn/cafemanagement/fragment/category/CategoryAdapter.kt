package fit.tdc.edu.vn.cafemanagement.fragment.category

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.model.category.Category
import kotlinx.android.synthetic.main.item_category_filter.view.*

class CategoryAdapter(
    @LayoutRes private val resId: Int = R.layout.item_category,
    private val onItemClick: ((category: Category, it: View) -> (Unit))? = null
) : ListAdapter<Category, RecyclerView.ViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Category>() {
            override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
                return oldItem.name == newItem.name
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryHolder {
        val itemView: View =
            LayoutInflater.from(parent.context).inflate(resId, parent, false)
        return CategoryHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentCategory: Category = getItem(position)
        (holder as CategoryHolder).bind(currentCategory, onItemClick)
    }
}

class CategoryHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {
    fun bind(
        item: Category,
        onItemClick: ((category: Category, it: View) -> (Unit))? = null
    ) {
        itemView.txt_category_name.text = item.name

        itemView.setOnClickListener {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                onItemClick?.invoke(item, it)
//                navigateToView(item, it)
            }
        }
    }
}
