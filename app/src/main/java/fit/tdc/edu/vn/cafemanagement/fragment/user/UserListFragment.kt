package fit.tdc.edu.vn.cafemanagement.fragment.user

import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.adapter.UserAdapter
import fit.tdc.edu.vn.cafemanagement.data.model.user.User
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.user_viewmodel.UserListViewModel
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.user_viewmodel.UserViewModelFactory
import fit.tdc.edu.vn.cafemanagement.fragment.BaseListFragment
import fit.tdc.edu.vn.cafemanagement.fragment.BaseListViewModel

class UserListFragment : BaseListFragment<User>(R.layout.fragment_list) {
    override val viewAdapter: ListAdapter<User, RecyclerView.ViewHolder>
        get() = UserAdapter()
    override val viewModel: BaseListViewModel<User>
        get() = ViewModelProvider(this, UserViewModelFactory()).get<UserListViewModel>()
    override val navController: NavController
        get() = findNavController()

    override fun showDeleteNotifySnackBar(item: User, view: View) {
        Snackbar.make(
            view,
            "Nhân viên ${item.name} đã bị xóa!",
            Snackbar.LENGTH_LONG
        ).show()
    }
}
