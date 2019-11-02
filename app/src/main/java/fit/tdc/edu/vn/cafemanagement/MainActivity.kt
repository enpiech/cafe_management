package fit.tdc.edu.vn.cafemanagement

import android.content.Context
import android.os.Bundle
import android.util.Log
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
import fit.tdc.edu.vn.cafemanagement.data.model.user.User
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val appBarConfiguration by lazy {
        AppBarConfiguration(
            setOf(
                R.id.categoryListFragment,
                R.id.unitListFragment,
                R.id.zoneListFragment,
                R.id.userListFragment,
                R.id.tableListFragment,
                R.id.tableListWaiterFragment,
                R.id.storeListFragment,
                R.id.materialListFragment
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

//        when (getUserType()) {
//            resources.getInteger(R.integer.no_user_type) -> {
//                logout()
//                navController.navigate(R.id.loginFragment)
//            }
//        }
    }

    private fun setupNavigation() {
        setSupportActionBar(toolbar)
        setupActionBarWithNavController(navController, appBarConfiguration)
        nav_view.setupWithNavController(navController)
        nav_view.setNavigationItemSelectedListener(this)
        navController.addOnDestinationChangedListener { controller, destination, _ ->
            when (destination.id) {
                in setOf(
                    R.id.loginFragment
                ) -> {
                    drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
                    supportActionBar?.hide()
                    fab.hide()
                    logout()
                }
                in setOf(
                    R.id.zoneViewFragment,
                    R.id.unitViewFragment,
                    R.id.categoryViewFragment,
                    R.id.userViewFragment,
                    R.id.tableViewFragment,
                    R.id.userViewFragment,
                    R.id.materialViewFragment
                ) -> {
                    drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
                    supportActionBar?.show()
                }
                R.id.tableListWaiterFragment -> {
                    fab.hide()
                }
                R.id.orderListFragment -> {
                    fab.setImageDrawable(getDrawable(R.drawable.ic_check))
                    fab.show()
                }
                else -> {
                    drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
                    supportActionBar?.show()
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
        if(item.itemId == R.id.loginFragment) {
            val sharedPreferences = getSharedPreferences("savedUser", Context.MODE_PRIVATE)
            Log.d("savedUsername:", sharedPreferences.getString("username", "lalalalaaalalalalalala"))
            Log.d("savedPassword:", sharedPreferences.getString("password", "lalalalaaalalalalalala"))

            val sharedPreferencesEditor = getSharedPreferences("savedUser", Context.MODE_PRIVATE).edit()
            sharedPreferencesEditor.putString("username", "aaa")
            sharedPreferencesEditor.putString("password", "aaa")
            sharedPreferencesEditor.apply()

            Log.d("savedUsername:", sharedPreferences.getString("username", "lalalalaaalalalalalala"))
            Log.d("savedPassword:", sharedPreferences.getString("password", "lalalalaaalalalalalala"))
            navController.navigate(item.itemId)
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
            getString(R.string.user_type_key), Context.MODE_PRIVATE
        ) ?: return
        with(sharedPref.edit()) {
            putInt(getString(R.string.user_type_key), resources.getInteger(R.integer.no_user_type))
            commit()
        }
    }

    private fun getUserType(): Int {
        return getPreferences(Context.MODE_PRIVATE).getInt(
            getString(R.string.user_type_key),
            resources.getInteger(R.integer.no_user_type)
        )
    }

    fun changeUserRole(type: User.Role) {
        nav_view.menu.clear()
        when (type) {
            User.Role.STORE_MANAGER -> {
                nav_view.inflateMenu(R.menu.store_manager_menu)
            }
            User.Role.MANAGER -> {
//                nav_view.inflateMenu(R.menu.manager_menu)
                nav_view.inflateMenu(R.menu.activity_main_drawer)
            }

            User.Role.WAITER -> {
                nav_view.inflateMenu(R.menu.waiter_menu)
            }
        }
    }
}
