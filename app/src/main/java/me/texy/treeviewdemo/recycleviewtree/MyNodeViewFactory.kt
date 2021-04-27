package me.texy.treeviewdemo.recycleviewtree

import android.view.View
import me.texy.treeview.base.BaseNodeViewBinder
import me.texy.treeview.base.BaseNodeViewFactory
import me.texy.treeviewdemo.R

/**
 * Created by zxy on 17/4/23.
 */
class MyNodeViewFactory : BaseNodeViewFactory() {
    override fun getNodeViewBinder(view: View, level: Int): BaseNodeViewBinder {
        return when (level) {
            0 -> FirstLevelNodeViewBinder(view)
            1 -> SecondLevelNodeViewBinder(view)
            2 -> ThirdLevelNodeViewBinder(view)
            else -> FirstLevelNodeViewBinder(view)
        }
    }

    override fun getNodeLayoutId(level: Int): Int {
        return when (level) {
            0 -> R.layout.item_first_level
            1 -> R.layout.item_second_level
            2 -> R.layout.item_third_level
            else -> 0
        }
    }
}