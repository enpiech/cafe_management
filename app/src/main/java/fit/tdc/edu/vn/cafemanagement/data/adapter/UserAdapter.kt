package fit.tdc.edu.vn.cafemanagement.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.model.user.User
import kotlinx.android.synthetic.main.item_user.view.*

class UserAdapter  : ListAdapter<User, UserHolder>(User.DIFF_CALLBACK) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserHolder {
        val itemView: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UserHolder(itemView)
    }

    override fun onBindViewHolder(
        holder: UserHolder,
        position: Int
    ) {
        val currentUser: User = getItem(position)
        holder.bind(currentUser)
    }

    fun getUserAt(
        position: Int
    ): User {
        return getItem(position)
    }
}

class UserHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {
    private fun navigateToView(
        user: User,
        it: View
    ) {
//        val direction =
//            UserListFragmentDirections.viewUserAction(
//                zoneId = zone.id
//            )
//        it.findNavController().navigate(direction)
    }

    fun bind(item: User) {
        itemView.txtUserName.text = item.name
        itemView.txtUserRole.text = item.role?.name
        itemView.setOnClickListener {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                navigateToView(item, it)
            }
        }
    }
}