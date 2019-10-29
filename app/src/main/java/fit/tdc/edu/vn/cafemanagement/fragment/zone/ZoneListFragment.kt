package fit.tdc.edu.vn.cafemanagement.fragment.zone

import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.adapter.ZoneAdapter
import fit.tdc.edu.vn.cafemanagement.data.model.zone.Zone
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.zone_viewmodel.ZoneListViewModel
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.zone_viewmodel.ZoneViewModelFactory
import fit.tdc.edu.vn.cafemanagement.fragment.BaseListFragment
import fit.tdc.edu.vn.cafemanagement.fragment.BaseListViewModel

class ZoneListFragment : BaseListFragment<Zone>(
    R.layout.fragment_list,
    viewAdapter = ZoneAdapter()
) {
//    override val viewAdapter: ListAdapter<Zone, RecyclerView.ViewHolder>
//        get() = ZoneAdapter()
override val viewModel: BaseListViewModel<Zone>
    get() = ViewModelProvider(this, ZoneViewModelFactory()).get<ZoneListViewModel>()
    override val navController: NavController
        get() = findNavController()

    override fun showDeleteNotifySnackBar(item: Zone, view: View) {
        Snackbar.make(
            view,
            "Khu vực ${item.name} đã bị xóa!",
            Snackbar.LENGTH_LONG
        ).show()
    }
}

//class ZoneListFragment : Fragment(R.layout.fragment_list) {
//
//    var viewAdapter = ZoneAdapter()
//
//    private val viewModel by lazy {
//        ViewModelProvider(this, ZoneViewModelFactory()).get<ZoneListViewModel>()
//    }
//
//    companion object {
//        fun newInstance() = ZoneListFragment()
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        requireActivity().fab.setOnClickListener {
//            findNavController().navigate(ZoneListFragmentDirections.zoneViewAction(zoneId = null))
//        }
//
//        recycler_view.apply {
//            setHasFixedSize(true)
//            layoutManager = LinearLayoutManager(context)
//
//            adapter = viewAdapter
//        }
//
//        viewModel.getAllItems().observe(viewLifecycleOwner, Observer {
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
//                                viewModel.delete(this)
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
