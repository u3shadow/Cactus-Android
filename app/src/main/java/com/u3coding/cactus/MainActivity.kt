package com.u3coding.cactus

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.u3coding.cactus.login.LoginActivity
import android.app.Fragment
import android.view.View
import android.widget.Toast
import com.u3coding.cactus.game.FindGameFragment
import com.u3coding.cactus.game.ShowGameFragment


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    var pref:SharedPreferences? = null
    var findGameFragment: Fragment?=null
    var resultFragment: Fragment?=null
    var fab:FloatingActionButton?=null
    var toolbar:Toolbar?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.window.statusBarColor = resources.getColor(R.color.holo_blue_dark)
        setContentView(R.layout.activity_main)
        toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        fab = findViewById(R.id.fab) as FloatingActionButton
        fab!!.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        val toggle = ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.setDrawerListener(toggle)
        toggle.syncState()

        val navigationView = findViewById(R.id.nav_view) as NavigationView
        navigationView.setNavigationItemSelectedListener(this)
        navigationView.setCheckedItem(1)
        pref = getSharedPreferences("login",1)
        initFragment()
    }

    override fun onBackPressed() {
        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    private fun initFragment() {
        findGameFragment = FindGameFragment()
        resultFragment = ShowGameFragment()
        //setTimeFragment = SetTimeFragment()
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.main_layout, findGameFragment)
        fragmentTransaction.commit()

    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val id = item.itemId
        val fragmentTransaction = fragmentManager.beginTransaction()
        if (id == R.id.nav_findgame) {
            fragmentTransaction.replace(R.id.main_layout, findGameFragment)
            fragmentTransaction.commit()
        }else if (id == R.id.nav_result) {
            if(pref?.getString("sid","-1").equals("-1")){
                Toast.makeText(this,getString(R.string.noresultnow),Toast.LENGTH_LONG).show()
            }else{
            fragmentTransaction.replace(R.id.main_layout, resultFragment)
            fragmentTransaction.commit()
            }
        }else if (id == R.id.nav_logout) {
            pref?.edit()?.remove("userid")?.apply()
            pref?.edit()?.remove("sid")?.apply()
            var mIntent = Intent(this,LoginActivity::class.java)
            startActivity(mIntent)
        }
        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        drawer.closeDrawer(GravityCompat.START)
        return true
    }
}
