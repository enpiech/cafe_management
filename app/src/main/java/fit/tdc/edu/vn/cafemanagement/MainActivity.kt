package fit.tdc.edu.vn.cafemanagement

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val appBarConfiguration by lazy {
        AppBarConfiguration(
            setOf(
                R.id.categoryListFragment,
                R.id.unitListFragment,
                R.id.zoneListFragment,
                R.id.userListFragment
            ), drawer_layout)
    }

    private val navController by lazy {
        findNavController(R.id.nav_host_fragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupNavigation()
        setupFab()
    }


    private fun setupNavigation() {
        setSupportActionBar(toolbar)
        setupActionBarWithNavController(navController, appBarConfiguration)
        nav_view.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { controller, destination, _ ->
            when (destination.id) {
                in setOf(
                    R.id.loginFragment
                ) -> {
                    drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
                    supportActionBar?.hide()
                    fab.hide()
                }
                in setOf(
                    R.id.zoneViewFragment,
                    R.id.unitViewFragment,
                    R.id.categoryViewFragment,
                    R.id.userViewFragment
                ) -> {
                    drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
                }
                else -> {
                    drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
                    fab.setImageDrawable(getDrawable(R.drawable.ic_add))
                    fab.show()
                    val callback = onBackPressedDispatcher.addCallback(this) {
                        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
                            drawer_layout.closeDrawer(GravityCompat.START)
                        }
                    }
                    toolbar.setNavigationOnClickListener {
                        controller.navigateUp(appBarConfiguration)
                    }
                    callback.isEnabled = true
                }
            }
            hideKeyboard()
        }

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        item.isChecked = true
        drawer_layout.closeDrawers()
        navController.navigate(item.itemId)
        return true
    }

    private fun setupFab() {
        fab.backgroundTintList = ContextCompat.getColorStateList(applicationContext, R.color.fab)
    }

    private fun hideKeyboard() {
        currentFocus?.clearFocus()
    }
}
