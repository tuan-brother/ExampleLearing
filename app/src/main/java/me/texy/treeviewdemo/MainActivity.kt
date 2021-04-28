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
    private var toolbar: Toolbar? = null
    private var viewGroup: ViewGroup? = null
    private var root: TreeNode? = null
    private var treeView: TreeView? = null
    private var mainAdapter: MainRecycleAdapter? = null
    private var layoutManager: LinearLayoutManager? = null
    private var listItem: ArrayList<Item>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initView()
        initListItem()
        root = TreeNode.root()
        buildTree()

        Log.d("TAG233", "onCreate:  ")
        root?.let { node ->
            treeView = TreeView(node, this, MyNodeViewFactory())
        }
        treeView?.let { treeV ->
            val view = treeV.view
            view.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            viewGroup!!.addView(view)
        }

        //recycleView
        mainAdapter = MainRecycleAdapter(itemClickListener = { item, item2 ->
            Log.d("TAG222", "onCreate:  $item + $item2")
        }
        )
        layoutManager = LinearLayoutManager(this)
        activityMainBinding.rvMenu.layoutManager = layoutManager
        activityMainBinding.rvMenu.adapter = mainAdapter
        listItem?.let { it ->
            mainAdapter?.submitList(it)
            mainAdapter?.notifyDataSetChanged()
        }

        //bottomSheet
        activityMainBinding.btnShow.setOnClickListener {
            supportFragmentManager.let {
                OptionsBottomSheetFragment.newInstance(Bundle()).apply {
                    show(it, tag)
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.select_all -> treeView!!.selectAll()
            R.id.deselect_all -> treeView!!.deselectAll()
            R.id.expand_all -> treeView!!.expandAll()
            R.id.collapse_all -> treeView!!.collapseAll()
            R.id.expand_level -> treeView!!.expandLevel(1)
            R.id.collapse_level -> treeView!!.collapseLevel(1)
            R.id.show_select_node -> Toast.makeText(application, selectedNodes, Toast.LENGTH_LONG).show()
        }
        return super.onOptionsItemSelected(item)
    }

    private val selectedNodes: String
        private get() {
            val stringBuilder = StringBuilder("You have selected: ")
            val selectedNodes = treeView!!.selectedNodes
            for (i in selectedNodes.indices) {
                if (i < 5) {
                    stringBuilder.append(selectedNodes[i].value.toString() + ",")
                } else {
                    stringBuilder.append("...and " + (selectedNodes.size - 5) + " more.")
                    break
                }
            }
            return stringBuilder.toString()
        }

    //region function

    private fun setLightStatusBar(view: View) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            var flags = view.systemUiVisibility
            window.statusBarColor = Color.WHITE
            flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            view.systemUiVisibility = flags
        }
    }

    private fun initView() {
        toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        viewGroup = findViewById<View>(R.id.container) as RelativeLayout
        setSupportActionBar(toolbar)
        setLightStatusBar(viewGroup!!)
    }

    fun initListItem() {
        listItem = ArrayList()
        listItem?.add(Item("Element1", "HaNoi", R.drawable.americanfb))
        listItem?.add(Item("Element2", "HaNoi", R.drawable.snackefb))
        listItem?.add(Item("Element3", "HaNoi", R.drawable.soccerfb))
        listItem?.add(Item("Element4", "HaNoi", R.drawable.yourteamfb))
    }

    private fun buildTree() {
        for (i in 0..3) {
            listItem?.let { list ->
                val treeNode = TreeNode(list[i], 0)
                if (i != 3) { // avoids creating child nodes for "parent" 3 (which then is not a parent, so the semantic in the displayed text becomes incorrect)
                    for (j in 0..5) {
                        val treeNode1 = TreeNode("Child No.$j", 1)
                        if (j != 5) { // avoids creating grand child nodes for child node 5
                            // For the child node without grand children there should not be any arrow displayed.
                            // In the demo code this can be handled in method 'SecondLevelNodeViewBinder.bindView' like this:
                            // imageView.setVisibility(treeNode.hasChild() ? View.VISIBLE : View.INVISIBLE);
//                            for (k in 0..4) {
//                                val treeNode2 = TreeNode("Grand Child No.$k", 2)
//                                treeNode1.addChild(treeNode2)
//                            }
                        }
                        treeNode.addChild(treeNode1)
                    }
                }
                root!!.addChild(treeNode)
            }
        }
    }

    //endregion

    override fun onItemClick(item: String) {
        TODO("Not yet implemented")
    }
}