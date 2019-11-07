package fit.tdc.edu.vn.cafemanagement.fragment.user

import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.data_source.firebase.FireBaseDataSource
import fit.tdc.edu.vn.cafemanagement.data.model.user.User
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.user.UserListViewModel
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.user.UserViewModelFactory
import fit.tdc.edu.vn.cafemanagement.fragment.BaseListFragment
import fit.tdc.edu.vn.cafemanagement.fragment.BaseListViewModel

class UserListFragment : BaseListFragment<User>(
    R.layout.fragment_list,
    UserAdapter()
) {
    override fun setupFab(fab: FloatingActionButton) {
        fab.setOnClickListener {
            navController.navigate(UserListFragmentDirections.actionViewUser(userId = null, title = getString(R.string.title_user_create)))
        }
    }

    override val viewModel: BaseListViewModel<User>
        get() = ViewModelProvider(
            this,
            UserViewModelFactory(FireBaseDataSource(), this)
        ).get<UserListViewModel>()
    override val navController: NavController
        get() = findNavController()

    override fun showDeleteNotifySnackBar(item: User, view: View) {
        Snackbar.make(
            view,
            "Nhân viên ${item.name} đã bị xóa!",
            Snackbar.LENGTH_LONG
        ).show()
    }

    override fun onDeleteItem(item: User, view: View) {
        if (item.storeId.isNullOrBlank()) {
            super.onDeleteItem(item, view)
        } else {
            MaterialAlertDialogBuilder(context)
                .setTitle(R.string.dialog_title_delete)
                .setMessage("${getString(R.string.warning_message_delete)}. ${getString(R.string.warning_message_delete_user_in_store)}")
                .setPositiveButton(R.string.btnOK) { _, _ ->
                    viewModel.delete(item)
                    showDeleteNotifySnackBar(item, view)
                }
                .setNegativeButton(R.string.btnCancel) { _, _ ->
                    viewAdapter.notifyDataSetChanged()
                }
                .show()
        }
    }
}

//class UserListFragment : Fragment(R.layout.fragment_list) {
//
//    var viewAdapter = UserAdapter()
//
//    private val viewModel by lazy {
//        ViewModelProvider(this, UserViewModelFactory()).get<UserListViewModel>()
//    }
//
//    companion object {
//        fun newInstance() = UserListFragment()
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        requireActivity().fab.setOnClickListener {
//            findNavController().navigate(UserListFragmentDirections.userViewAction(userId = null))
//        }
//
//        recycler_view.apply {
//            setHasFixedSize(true)
//            layoutManager = LinearLayoutManager(context)
//
//            adapter = viewAdapter
//        }
//
//        viewModel.getAllItems().observe(this, Observer {
//            viewAdapter.submitList(it.data)
//            //Log.d("test", "cao"+it.data.toString())
//        })
//
//        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
//            0, ItemTouchHelper.LEFT.or(
//                ItemTouchHelper.RIGHT
//            )
//        ) {
//            override fun onMove(
//                recyclerView: RecyclerView,
//                viewHolder: RecyclerView.ViewHolder,
//                target: RecyclerView.ViewHolder
//            ): Boolean {
//                return false
//            }
//
//
//            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
//                MaterialAlertDialogBuilder(context)
//                    .setTitle(R.string.dialog_title_delete)
//                    .setMessage(R.string.warning_message_delete)
//                    .setPositiveButton(R.string.btnOK) { _, _ ->
//                        run {
//                            viewAdapter.currentList[viewHolder.adapterPosition].apply {
//                                viewModel.complete(this)
//                                Snackbar.make(
//                                    viewHolder.itemView,
//                                    "${this.name} đã bị xóa!",
//                                    Snackbar.LENGTH_LONG
//                                ).show()
//                            }
//                        }
//                    }
//                    .setNegativeButton(R.string.btnCancel) { _, _ ->
//                        viewAdapter.notifyItemChanged(viewHolder.adapterPosition)
//                    }
//                    .show()
//            }
//        }
//        ).attachToRecyclerView(recycler_view)
//    }
//}
