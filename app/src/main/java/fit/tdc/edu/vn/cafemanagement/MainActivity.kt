package fit.tdc.edu.vn.cafemanagement

import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.core.view.isVisible
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import fit.tdc.edu.vn.cafemanagement.data.model.user.User
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity :
    AppCompatActivity(),
    NavigationView.OnNavigationItemSelectedListener,
    NavController.OnDestinationChangedListener {
    private val appBarConfiguration by lazy {
        AppBarConfiguration(
            setOf(
                R.id.categoryListFragment,
                R.id.unitListFragment,
                R.id.zoneListFragment,
                R.id.userListFragment,
                R.id.tableListFragment,
                R.id.storeListFragment,
                R.id.materialListFragment,
                R.id.chefListFragment,
                R.id.tableListWaiterFragment
            ), drawer_layout
        )
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
        nav_view.setupWithNavController(navController)
        nav_view.setNavigationItemSelectedListener(this)
        navController.addOnDestinationChangedListener(this)
    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        // Toggle fab
        if (destination.id in setOf(
                R.id.chefListFragment,
                R.id.orderDetailFragment,
                R.id.orderListFragment,
                R.id.loginFragment
            )
        ) {
            fab.hide()
        } else {
            fab.show()
        }

        // Toggle supportActionBar
        if (destination.id == R.id.loginFragment) {
            supportActionBar?.hide()
        } else {
            supportActionBar?.show()
        }

        // Toggle drawer
        if (destination.id in setOf(
                R.id.loginFragment,
                R.id.zoneDetailFragment,
                R.id.unitDetailFragment,
                R.id.categoryDetailFragment,
                R.id.unitDetailFragment,
                R.id.tableDetailFragment,
                R.id.unitDetailFragment,
                R.id.materialDetailFragment,
                R.id.orderDetailFragment,
                R.id.orderListFragment
            )
        ) {
            drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        } else {
            fab.setImageDrawable(getDrawable(R.drawable.ic_add))
            drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
            val callback = onBackPressedDispatcher.addCallback(this) {
                if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
                    drawer_layout.closeDrawer(GravityCompat.START)
                }
            }
            callback.isEnabled = true
        }

        // Toggle bottom navigation
        bottom_navigation.isVisible = destination.id in setOf(
            R.id.tableListWaiterFragment
        )

        // Reset backPressed listener and toolbar navigation up
        if (destination.id in setOf(
                R.id.categoryListFragment,
                R.id.unitListFragment,
                R.id.zoneListFragment,
                R.id.userListFragment,
                R.id.tableListFragment,
                R.id.storeListFragment,
                R.id.materialListFragment,
                R.id.chefListFragment,
                R.id.tableListWaiterFragment,
                R.id.orderDetailFragment
            )) {
            toolbar.setNavigationOnClickListener {
                controller.navigateUp(appBarConfiguration)
                hideKeyboard()
            }
            val callback = onBackPressedDispatcher.addCallback(this) {
                if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
                    drawer_layout.closeDrawer(GravityCompat.START)
                }
                controller.navigateUp(appBarConfiguration)
                hideKeyboard()
            }
            callback.isEnabled = true
        }
        hideKeyboard()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        item.isChecked = true
        drawer_layout.closeDrawers()
        if (item.itemId == R.id.loginFragment) {
            logout()
        }
        navController.navigate(item.itemId)
        return true
    }

    private fun setupFab() {
        fab.backgroundTintList = ContextCompat.getColorStateList(applicationContext, R.color.fab)
    }

    private fun hideKeyboard() {
        currentFocus?.clearFocus()
    }

    private fun logout() {
        val sharedPref = getSharedPreferences(
            getString(R.string.session_user),
            Context.MODE_PRIVATE
        ) ?: return
        with(sharedPref.edit()) {
            putString(getString(R.string.user_name_key), null)
            putString(getString(R.string.user_password_key), null)
            putInt(getString(R.string.user_type_key), resources.getInteger(R.integer.no_user_type))
            commit()
        }
    }

    fun changeUserRole(type: User.Role) {
        nav_view.menu.clear()
        when (type) {
            User.Role.STORE_MANAGER -> {
                nav_view.inflateMenu(R.menu.store_manager_menu)
                setupActionBarWithNavController(navController, appBarConfiguration)
            }
            User.Role.MANAGER -> {
                nav_view.inflateMenu(R.menu.activity_main_drawer)
                setupActionBarWithNavController(navController, appBarConfiguration)
            }
            User.Role.WAITER -> {
                setupActionBarWithNavController(navController, appBarConfiguration)
                nav_view.inflateMenu(R.menu.waiter_drawer_menu)
                bottom_navigation.setupWithNavController(navController)
                bottom_navigation.menu.clear()
                bottom_navigation.inflateMenu(R.menu.waiter_menu)
            }
            User.Role.CHEF -> {
                setupActionBarWithNavController(navController, appBarConfiguration)
                nav_view.inflateMenu(R.menu.chef_menu)
            }
        }
    }
}
