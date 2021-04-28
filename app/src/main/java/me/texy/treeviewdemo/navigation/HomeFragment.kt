package me.texy.treeviewdemo.navigation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import me.texy.treeview.TreeNode
import me.texy.treeview.TreeView
import me.texy.treeviewdemo.R
import me.texy.treeviewdemo.bottomsheet.OptionsBottomSheetFragmentArgs
import me.texy.treeviewdemo.databinding.FragmentHomeBinding
import me.texy.treeviewdemo.recycleview.Item
import me.texy.treeviewdemo.recycleview.MainRecycleAdapter
import me.texy.treeviewdemo.recycleviewtree.MyNodeViewFactory
import java.util.*

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private var root: TreeNode? = null
    private var treeView: TreeView? = null
    private var mainAdapter: MainRecycleAdapter? = null
    private var layoutManager: LinearLayoutManager? = null
    private var listItem: ArrayList<Item>? = null
    val safeArgs: OptionsBottomSheetFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        initListItem()
        root = TreeNode.root()
        buildTree()

        Log.d("TAG233", "onCreate:  ")
        root?.let { node ->
            treeView = TreeView(node, requireContext(), MyNodeViewFactory())
        }
        treeView?.let { treeV ->
            val view = treeV.view
            view.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            binding.viewGroup.addView(view)
        }

        //recycleView
        mainAdapter = MainRecycleAdapter(itemClickListener = { item, item2 ->
            Log.d("TAG222", "onCreate:  $item + $item2")
        }
        )
        layoutManager = LinearLayoutManager(requireContext())
        binding.rvMenu.layoutManager = layoutManager
        binding.rvMenu.adapter = mainAdapter
        listItem?.let { it ->
            mainAdapter?.submitList(it)
            mainAdapter?.notifyDataSetChanged()
        }

        binding.btnShow.setOnClickListener {
            val arg = safeArgs.data
            findNavController().navigate(R.id.toBottomSheet)
        }
        // Inflate the layout for this fragment
        return binding.root
    }

    //region function

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
}