package fit.tdc.edu.vn.cafemanagement.ui.category_list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fit.tdc.edu.vn.cafemanagement.R

class CategoryFragmentPlaceholder : AppCompatActivity() {

    private val manager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_holder)

        showFragmentCategory()
    }

    private fun showFragmentCategory() {
        val transaction = manager.beginTransaction()
        val fragment = CategoryListFragment()
        transaction.replace(R.id.fragment_holder, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}