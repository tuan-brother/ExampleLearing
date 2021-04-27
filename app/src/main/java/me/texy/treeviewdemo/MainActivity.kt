package me.texy.treeviewdemo

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import me.texy.treeview.TreeNode
import me.texy.treeview.TreeView

class MainActivity : AppCompatActivity() {
    protected var toolbar: Toolbar? = null
    private var viewGroup: ViewGroup? = null
    private var root: TreeNode? = null
    private var treeView: TreeView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        super.setContentView(R.layout.activity_main)
        initView()
        root = TreeNode.root()
        buildTree()
        treeView = TreeView(root, this, MyNodeViewFactory())
        val view = treeView!!.view
        view.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        viewGroup!!.addView(view)
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

    private fun buildTree() {
        for (i in 0..3) {
            val treeNode = TreeNode("Parent  No.$i", 0)
            if (i != 3) { // avoids creating child nodes for "parent" 3 (which then is not a parent, so the semantic in the displayed text becomes incorrect)
                for (j in 0..5) {
                    val treeNode1 = TreeNode("Child No.$j", 1)
                    if (j != 5) { // avoids creating grand child nodes for child node 5
                        // For the child node without grand children there should not be any arrow displayed.
                        // In the demo code this can be handled in method 'SecondLevelNodeViewBinder.bindView' like this:
                        // imageView.setVisibility(treeNode.hasChild() ? View.VISIBLE : View.INVISIBLE);
                        for (k in 0..4) {
                            val treeNode2 = TreeNode("Grand Child No.$k", 2)
                            treeNode1.addChild(treeNode2)
                        }
                    }
                    treeNode.addChild(treeNode1)
                }
            }
            root!!.addChild(treeNode)
        }
    }

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
}