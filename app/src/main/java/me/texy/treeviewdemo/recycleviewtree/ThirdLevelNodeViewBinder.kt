package me.texy.treeviewdemo

import android.view.View
import android.widget.TextView
import me.texy.treeview.TreeNode
import me.texy.treeview.base.CheckableNodeViewBinder

/**
 * Created by zxy on 17/4/23.
 */
class ThirdLevelNodeViewBinder(itemView: View) : CheckableNodeViewBinder(itemView) {
    var textView: TextView
    override fun getCheckableViewId(): Int {
        return R.id.checkBox
    }

    override fun bindView(treeNode: TreeNode) {
        textView.text = treeNode.value.toString()
    }

    init {
        textView = itemView.findViewById<View>(R.id.node_name_view) as TextView
    }
}