package fit.tdc.edu.vn.cafemanagement.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.model.user.User
import fit.tdc.edu.vn.cafemanagement.fragment.user.UserListFragmentDirections
import kotlinx.android.synthetic.main.item_user.view.*

class UserAdapter  : ListAdapter<User, RecyclerView.ViewHolder>(User.DIFF_CALLBACK) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserHolder {
        val itemView: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UserHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentUser: User = getItem(position)
        (holder as UserHolder).bind(currentUser)
    }
}

class UserHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {
    private fun navigateToView(
        user: User,
        it: View
    ) {
        val direction =
            UserListFragmentDirections.userViewAction(
                userId = user.id
            )
        it.findNavController().navigate(direction)
    }

    fun bind(item: User) {
        itemView.txtUserName.text = item.name
        if (item.role?.nameResId != null) {
            itemView.txtUserRole.setText(item.role?.nameResId!!)
        }
        itemView.setOnClickListener {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                navigateToView(item, it)
            }
        }
    }
}