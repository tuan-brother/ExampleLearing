package me.texy.treeviewdemo.recycleviewtree

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import me.texy.treeview.TreeNode
import me.texy.treeview.base.CheckableNodeViewBinder
import me.texy.treeviewdemo.R

/**
 * Created by zxy on 17/4/23.
 */
class SecondLevelNodeViewBinder(itemView: View) : CheckableNodeViewBinder(itemView) {
    var textView: TextView
    var imageView: ImageView
    override fun getCheckableViewId(): Int {
        return R.id.checkBox
    }

    override fun bindView(treeNode: TreeNode) {
        textView.text = treeNode.value.toString()
        imageView.rotation = if (treeNode.isExpanded) 90.toFloat() else 0.toFloat()
        imageView.visibility = if (treeNode.hasChild()) View.VISIBLE else View.INVISIBLE
    }

    override fun onNodeToggled(treeNode: TreeNode, expand: Boolean) {
        Log.d("TAG333", "onNodeToggled:  " + "item 2")
        if (expand) {
            imageView.animate().rotation(90f).setDuration(200).start()
        } else {
            imageView.animate().rotation(0f).setDuration(200).start()
        }
    }

    init {
        textView = itemView.findViewById<View>(R.id.node_name_view) as TextView
        imageView = itemView.findViewById<View>(R.id.arrow_img) as ImageView
    }
}