package fit.tdc.edu.vn.cafemanagement

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.*
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val appBarConfiguration by lazy {
        AppBarConfiguration(
            setOf(
                R.id.categoryListFragment,
                R.id.unitListFragment,
                R.id.zoneListFragment
            ), drawer_layout)
    }
    private val navController by lazy {
        findNavController(R.id.nav_host_fragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        setupNavigation()
        setupViews()
        setupFab()
    }


    private fun setupNavigation() {
        setupActionBarWithNavController(navController, drawer_layout)
        toolbar.setupWithNavController(navController, appBarConfiguration)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                in arrayOf(
                    R.id.loginFragment
                ) -> {
                    fab.hide()
                    drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
                }
                in arrayOf(
                    R.id.zoneViewFragment
                ) -> {

                }
                else -> {
                    supportActionBar?.show()
                    fab.setImageDrawable(getDrawable(R.drawable.ic_add))
                    fab.show()
                }
            }
            hideKeyboard()
        }

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.logout -> {
                navController.navigate(R.id.loginFragment)
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun setupViews() {
        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
    }

    private fun setupFab() {
        fab.backgroundTintList = ContextCompat.getColorStateList(applicationContext, R.color.fab)
    }

    private fun hideKeyboard() {
        currentFocus?.clearFocus()
    }
}
