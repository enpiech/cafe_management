package fit.tdc.edu.vn.cafemanagement.fragment.user

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.adapter.UserAdapter
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.user_viewmodel.UserListViewModel
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.user_viewmodel.UserViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_list.*

//class UserListFragment : BaseListFragment<User>(R.layout.fragment_list) {
//    override val viewAdapter: ListAdapter<User, RecyclerView.ViewHolder>
//        get() = UserAdapter()
//    override val viewModel: BaseListViewModel<User>
//        get() = ViewModelProvider(this, UserViewModelFactory()).get<UserListViewModel>()
//    override val navController: NavController
//        get() = findNavController()
//
//    override fun showDeleteNotifySnackBar(item: User, view: View) {
//        Snackbar.make(
//            view,
//            "Nhân viên ${item.name} đã bị xóa!",
//            Snackbar.LENGTH_LONG
//        ).show()
//    }
//}

class UserListFragment : Fragment(R.layout.fragment_list) {

    var viewAdapter = UserAdapter()

    private val viewModel by lazy {
        ViewModelProvider(this, UserViewModelFactory()).get<UserListViewModel>()
    }

    companion object {
        fun newInstance() = UserListFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().fab.setOnClickListener {
            findNavController().navigate(UserListFragmentDirections.userViewAction(userId = null))
        }

        recycler_view.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)

            adapter = viewAdapter
        }

        viewModel.getAllItems().observe(this, Observer {
            viewAdapter.submitList(it.data)
            //Log.d("test", "cao"+it.data.toString())
        })

        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            0, ItemTouchHelper.LEFT.or(
                ItemTouchHelper.RIGHT
            )
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }


            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                MaterialAlertDialogBuilder(context)
                    .setTitle(R.string.dialog_title_delete)
                    .setMessage(R.string.warning_message_delete)
                    .setPositiveButton(R.string.btnOK) { _, _ ->
                        run {
                            viewAdapter.currentList[viewHolder.adapterPosition].apply {
                                viewModel.delete(this)
                                Snackbar.make(
                                    viewHolder.itemView,
                                    "${this.name} đã bị xóa!",
                                    Snackbar.LENGTH_LONG
                                ).show()
                            }
                        }
                    }
                    .setNegativeButton(R.string.btnCancel) { _, _ ->
                        viewAdapter.notifyItemChanged(viewHolder.adapterPosition)
                    }
                    .show()
            }
        }
        ).attachToRecyclerView(recycler_view)
    }
}
