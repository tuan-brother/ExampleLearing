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
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import me.texy.treeview.TreeNode
import me.texy.treeview.TreeView
import me.texy.treeviewdemo.bottomsheet.OptionsBottomSheetFragment
import me.texy.treeviewdemo.databinding.ActivityMainBinding
import me.texy.treeviewdemo.recycleview.Item
import me.texy.treeviewdemo.recycleview.MainRecycleAdapter
import me.texy.treeviewdemo.recycleviewtree.MyNodeViewFactory
import java.util.*

open class MainActivity : AppCompatActivity(), OptionsBottomSheetFragment.ItemClickListener {
    private lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
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
}