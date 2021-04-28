package me.texy.treeviewdemo

import android.app.ProgressDialog.show
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import me.texy.treeview.TreeNode
import me.texy.treeview.TreeView
import me.texy.treeviewdemo.appchat.FriendFragment
import me.texy.treeviewdemo.appchat.SettingFragment
import me.texy.treeviewdemo.bottomsheet.OptionsBottomSheetFragment
import me.texy.treeviewdemo.databinding.ActivityMainBinding
import me.texy.treeviewdemo.navigation.HomeFragment
import me.texy.treeviewdemo.recycleview.Item
import me.texy.treeviewdemo.recycleview.MainRecycleAdapter
import me.texy.treeviewdemo.recycleviewtree.MyNodeViewFactory
import java.util.*

open class MainActivity : AppCompatActivity(), OptionsBottomSheetFragment.ItemClickListener, BottomNavigationView.OnNavigationItemSelectedListener {
    private lateinit var activityMainBinding: ActivityMainBinding
    var member: Int? = null
    val value by lazy {
        member = 6
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        test(value)
        val navHostFragment =
                supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        findViewById<BottomNavigationView>(R.id.bottom_nav)
                .setupWithNavController(navController)
        activityMainBinding.bottomNav.setOnNavigationItemSelectedListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

    override fun onItemClick(item: String) {
        TODO("Not yet implemented")
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.mn_friend -> {
                val homeFragment = FriendFragment()
                show(homeFragment)
                return true
            }
            R.id.mn_home -> {
                val homeFragment = HomeFragment()
                show(homeFragment)
                return true
            }
            R.id.mn_setting -> {
                val homeFragment = SettingFragment()
                show(homeFragment)
                return true
            }

        }
        return false
    }

    private fun show(fragment: Fragment) {

        val fragmentManager = supportFragmentManager

        fragmentManager
                .beginTransaction()
                .replace(R.id.nav_host_fragment, fragment)
                .commit()
    }

    fun test(value: Any) {
        Log.d("TAG999", "test:  " + member)
    }
}