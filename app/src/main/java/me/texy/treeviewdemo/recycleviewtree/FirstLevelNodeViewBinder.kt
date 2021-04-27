package me.texy.treeviewdemo

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import me.texy.treeview.TreeNode
import me.texy.treeview.base.CheckableNodeViewBinder
import me.texy.treeviewdemo.recycleview.Item

/**
 * Created by zxy on 17/4/23.
 */
class FirstLevelNodeViewBinder(itemView: View) : CheckableNodeViewBinder(itemView) {
    var textView: TextView
    var imageView: ImageView
    override fun getCheckableViewId(): Int {
        return R.id.checkBox
    }

    override fun bindView(treeNode: TreeNode) {
        textView.text = (treeNode.value as Item).name
        (treeNode.value as Item).logo?.let { it ->
            imageView.setImageResource(it)
        }
        imageView.visibility = if (treeNode.hasChild()) View.VISIBLE else View.INVISIBLE
    }

    override fun onNodeToggled(treeNode: TreeNode, expand: Boolean) {
        if (expand) {
            imageView.animate().rotation(90f).setDuration(200).start()
        } else {
            imageView.animate().rotation(0f).setDuration(200).start()
        }
    }

    init {
        textView = itemView.findViewById(R.id.node_name_view)
        imageView = itemView.findViewById(R.id.arrow_img)
    }
}