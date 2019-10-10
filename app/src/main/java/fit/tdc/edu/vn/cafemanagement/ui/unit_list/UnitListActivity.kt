package fit.tdc.edu.vn.cafemanagement.ui.unit_list

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.adapter.UnitAdapter
import fit.tdc.edu.vn.cafemanagement.data.model.kotlin.Unit
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.unit_viewmodel.UnitViewModel
import fit.tdc.edu.vn.cafemanagement.data.viewmodel.unit_viewmodel.UnitViewModelFactory
import fit.tdc.edu.vn.cafemanagement.ui.unit_create.UnitCreateActivity
import kotlinx.android.synthetic.main.activity_unit_list.*

class UnitListActivity : AppCompatActivity() {

    var adapter = UnitAdapter()

    companion object {
        const val ADD_UNIT_REQUEST = 1
        const val EDIT_UNIT_REQUEST = 2
    }

    private lateinit var unitViewModel: UnitViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_unit_list)
        setTitle(R.string.donVi)
        //add unit
        btnAddUnit.setOnClickListener {
            startActivityForResult(
                Intent(this, UnitCreateActivity::class.java),
                ADD_UNIT_REQUEST
            )
        }

        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(true)



        recycler_view.adapter = adapter

        unitViewModel = ViewModelProviders.of(this, UnitViewModelFactory()).get(UnitViewModel::class.java)

        unitViewModel.getAllUnits().observe(this, Observer {
            adapter.submitList(it.data)
        })




        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT.or(
            ItemTouchHelper.RIGHT)) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val builder = AlertDialog.Builder(this@UnitListActivity)
                with(builder)
                {
                    setTitle("Xóa")
                    setMessage("Bạn có muốn xóa thông tin này không?")
                    setPositiveButton("OK") { p0, p1 ->
                        unitViewModel.delete(adapter.getUnitAt(viewHolder.adapterPosition))
                        Toast.makeText(baseContext, "Unit bạn chọn đã bị xóa!", Toast.LENGTH_SHORT).show()
                    }
                    setNegativeButton("Hủy") { p0, p1 ->
                        adapter.notifyDataSetChanged()
                        Toast.makeText(baseContext, "Hủy", Toast.LENGTH_SHORT).show()
                    }
                    show()
                }
            }
        }
        ).attachToRecyclerView(recycler_view)

        adapter.setOnItemClickListener(object : UnitAdapter.OnItemClickListener {
            override fun onItemClick(unit: Unit) {
                val intent = Intent(baseContext, UnitCreateActivity::class.java)
                intent.putExtra(UnitCreateActivity.EXTRA_ID, unit.id)
                intent.putExtra(UnitCreateActivity.EXTRA_NAME, unit.name)
                startActivityForResult(intent, EDIT_UNIT_REQUEST)
            }


        })
    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.main_menu, menu)
//        return true
//    }

//    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
//        return when (item?.itemId) {
//            R.id.delete_all_notes -> {
//                unitViewModel.deleteAllUnits()
//                Toast.makeText(this, "All units deleted!", Toast.LENGTH_SHORT).show()
//                true
//            }
//            else -> {
//                super.onOptionsItemSelected(item)
//            }
//        }
//    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == ADD_UNIT_REQUEST && resultCode == Activity.RESULT_OK) {
            val newUnit = Unit(
                data!!.getStringExtra(UnitCreateActivity.EXTRA_NAME)
            )
            unitViewModel.insert(newUnit)
            Toast.makeText(this, "Unit saved!", Toast.LENGTH_SHORT).show()

        } else if (requestCode == EDIT_UNIT_REQUEST && resultCode == Activity.RESULT_OK) {
            val id = data?.getStringExtra(UnitCreateActivity.EXTRA_ID)

            if (id == "") {
                Toast.makeText(this, "Could not update! Error!", Toast.LENGTH_SHORT).show()
            }

            val updateUnit = Unit(
                data!!.getStringExtra(UnitCreateActivity.EXTRA_NAME)
            )
            updateUnit.id = data.getStringExtra(UnitCreateActivity.EXTRA_ID) ?: ""
            unitViewModel.update(updateUnit)

        } else {
            Toast.makeText(this, "Unit not saved!", Toast.LENGTH_SHORT).show()
        }


    }
}
