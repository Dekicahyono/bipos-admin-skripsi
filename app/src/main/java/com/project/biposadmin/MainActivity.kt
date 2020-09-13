package com.project.biposadmin

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import com.project.biposadmin.presentation.add_product.AddProductFragment
import com.project.biposadmin.presentation.customer_orders_list.CustomerOrderListFragment
import kotlinx.android.synthetic.main.activity_add_product.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val addProductFragment: Fragment = AddProductFragment()
    private val orderListFragment: Fragment = CustomerOrderListFragment()

    val fm = supportFragmentManager
    var active = addProductFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product)
        toolbar.title = "Bipos Admin"

        val toggle = ActionBarDrawerToggle(
            this,
            drawer_layout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        fm.beginTransaction().add(R.id.main_container, orderListFragment, "2").hide(orderListFragment).commit()
        fm.beginTransaction().add(R.id.main_container, addProductFragment, "1").commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_addProduct -> {
                fm.beginTransaction().hide(active).show(addProductFragment).commit()
                active = addProductFragment
            }
            R.id.nav_orders -> {
                fm.beginTransaction().hide(active).show(orderListFragment).commit()
                active = orderListFragment
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

}