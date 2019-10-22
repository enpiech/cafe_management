package fit.tdc.edu.vn.cafemanagement.fragment.user

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.adapter.UserAdapter
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.user_viewmodel.UserViewModel
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.user_viewmodel.UserViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_list.*

class UserListFragment : Fragment(R.layout.fragment_list) {

    var viewAdapter = UserAdapter()
    private val viewModel by lazy {
        ViewModelProvider(this, UserViewModelFactory()).get<UserViewModel>()
    }

    companion object {
        fun newInstance() = UserListFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().fab.setOnClickListener {
            findNavController().navigate(UserListFragmentDirections.userViewAction(null))
        }

        recycler_view.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)

            adapter = viewAdapter
        }

        viewModel.getAllItems().observe(this, Observer {
            viewAdapter.submitList(it.data)
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
                val builder = AlertDialog.Builder(requireContext())
                with(builder)
                {
                    setTitle("Xóa")
                    setMessage("Bạn có muốn xóa thông tin này không?")
                    setPositiveButton("OK") { p0, p1 ->
                        viewModel.delete(viewAdapter.getUserAt(viewHolder.adapterPosition))
                        Snackbar.make(
                            viewHolder.itemView,
                            "Đơn vị bạn chọn đã bị xóa!",
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                    setNegativeButton("Hủy") { p0, p1 ->
                        viewAdapter.notifyDataSetChanged()
                        Snackbar.make(
                            viewHolder.itemView,
                            "Hủy",
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                    show()
                }
            }
        }
        ).attachToRecyclerView(recycler_view)
    }
}
