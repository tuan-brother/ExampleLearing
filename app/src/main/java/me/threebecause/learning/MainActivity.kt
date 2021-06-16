package me.threebecause.learning

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import me.threebecause.ShareViewModel
import me.threebecause.learning.databinding.ActivityMainBinding
import me.threebecause.learning.navigation.HomeFragment
import me.threebecause.learning.ui.screen.home.FragmentSetting
import me.threebecause.learning.ui.screen.home.competition.CompetitionFragment
import me.threebecause.learning.ui.screen.standings.StandingsFragment

@AndroidEntryPoint
open class MainActivity : AppCompatActivity(),
    BottomNavigationView.OnNavigationItemSelectedListener, CompetitionFragment.IOnClickListener {
    private lateinit var activityMainBinding: ActivityMainBinding
    private val viewModel : ShareViewModel by viewModels()
    var member: Int? = null
    val value by lazy {
        member = 6
    }

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        supportActionBar?.let {
            it.setDefaultDisplayHomeAsUpEnabled(true)
            it.setHomeButtonEnabled(true)
        }

        supportFragmentManager.beginTransaction()
            .add(R.id.nav_host_fragment, CompetitionFragment(), CompetitionFragment.TAG)
            .add(R.id.fr_constrain_fragment, StandingsFragment(), StandingsFragment.TAG)
            .commit()

        activityMainBinding.bnvMain.setOnNavigationItemSelectedListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.standings -> {
                val standing = StandingsFragment()
                show(FragmentSetting.TAG, StandingsFragment.TAG, standing)
                return true
            }
            R.id.match -> {
                val homeFragment = HomeFragment()
                show(FragmentSetting.TAG, HomeFragment.TAG, homeFragment)
                return true
            }
        }
        return false
    }

    private fun show(currentab: String, tag: String, fragment: Fragment) {

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val currentFragment = supportFragmentManager.findFragmentByTag(currentab)
        if (supportFragmentManager.fragments.contains(currentFragment)) {
            currentFragment?.let { fragmentTransaction.hide(it) }
        }
        val newFragment: Fragment? = supportFragmentManager.findFragmentByTag(tag)
        if (newFragment == null) {
            fragmentTransaction.add(R.id.fr_constrain_fragment, fragment)
        } else {
            fragmentTransaction.show(newFragment)
        }

        fragmentTransaction.commitNow()
    }

    override fun onClick(item: Int) {
        Log.d("TAG321", "onClick:  ")
    }
}