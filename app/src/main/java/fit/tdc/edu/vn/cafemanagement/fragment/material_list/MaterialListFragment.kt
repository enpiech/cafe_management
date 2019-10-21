package fit.tdc.edu.vn.cafemanagement.fragment.material_list

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.adapter.MaterialAdapter
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.material_viewmodel.MaterialViewModel
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.material_viewmodel.MaterialViewModelFactory
import kotlinx.android.synthetic.main.fragment_list_material.*

class MaterialListFragment : Fragment(R.layout.fragment_list_material) {

    var adapter = MaterialAdapter()

    companion object {

        fun newInstance() = MaterialListFragment()

        const val ADD_MATERIAL_REQUEST = 1
        const val EDIT_MATERIAL_REQUEST = 2
    }

    private lateinit var viewModel: MaterialViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // TODO setEvent when click on button add (change to material view)
        /*btnAddMaterial.setOnClickListener {
            activity?.let {

            }
        }*/

        recycler_view.layoutManager = LinearLayoutManager(context)
        recycler_view.setHasFixedSize(true)

        recycler_view.adapter = adapter

        viewModel = ViewModelProvider(this, MaterialViewModelFactory())
            .get(MaterialViewModel::class.java)

        viewModel.getAllMaterials().observe(this, Observer {
            adapter.submitList(it.data)
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
                with(builder) {
                    setTitle("Xóa")
                    setMessage("Bạn có muốn xóa thông tin này không?")
                    setPositiveButton("OK") { p0, p1 ->
                        viewModel.delete(adapter.getMaterialAt(viewHolder.adapterPosition))
                        Toast.makeText(
                            context,
                            "Nguyên liệu bạn chọn đã bị xóa!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    setNegativeButton("Hủy") { p0, p1 ->
                        adapter.notifyDataSetChanged()
                        Toast.makeText(context, "Hủy", Toast.LENGTH_SHORT).show()
                    }
                    show()
                }
            }
        }
        ).attachToRecyclerView(recycler_view)

        // TODO setEvent when click on list item (send data to material view)
        /*viewAdapter.setOnItemClickListener(object : MaterialAdapter.OnItemClickListener {
            override fun onItemClick(material: Material) {

            }
        })*/
    }
}
