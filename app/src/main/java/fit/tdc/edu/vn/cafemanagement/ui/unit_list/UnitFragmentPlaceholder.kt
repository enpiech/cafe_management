package fit.tdc.edu.vn.cafemanagement.ui.unit_list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fit.tdc.edu.vn.cafemanagement.R

class UnitFragmentPlaceholder : AppCompatActivity() {

    private val manager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.unit_fragment_placeholder)

        showFragmentCategory()
    }

    private fun showFragmentCategory() {
        val transaction = manager.beginTransaction()
        val fragment = UnitListFragment()
        transaction.replace(R.id.unit_fragment_placeholder, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
