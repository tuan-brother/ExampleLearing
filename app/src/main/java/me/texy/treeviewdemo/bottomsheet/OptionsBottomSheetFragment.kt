package me.texy.treeviewdemo.bottomsheet

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import me.texy.treeview.TreeNode
import me.texy.treeview.TreeView
import me.texy.treeviewdemo.R
import me.texy.treeviewdemo.databinding.BottomSheetOptionsBinding
import me.texy.treeviewdemo.recycleview.Item
import me.texy.treeviewdemo.recycleviewtree.MyNodeViewFactory


class OptionsBottomSheetFragment : BottomSheetDialogFragment() {
    lateinit var bottomSheet: BottomSheetOptionsBinding
    private var root: TreeNode? = null
    private var treeView: TreeView? = null
    private var listItem: ArrayList<Item>? = null

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        bottomSheet = DataBindingUtil.inflate(inflater, R.layout.bottom_sheet_options, container, false)
        initListItem()
        root = TreeNode.root()
        buildTree()
        root?.let { node ->
            treeView = TreeView(node, requireContext(), MyNodeViewFactory())
        }
        val view = treeView!!.view
        view.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        bottomSheet.ctlFull.addView(view)
        return bottomSheet.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog: Dialog = super.onCreateDialog(savedInstanceState)
        dialog.setOnShowListener(DialogInterface.OnShowListener { dialogInterface ->
            val bottomSheetDialog = dialogInterface as BottomSheetDialog
            setupFullHeight(bottomSheetDialog)
        })
        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
    }

    private fun setUpViews() {
        // We can have cross button on the top right corner for providing elemnet to dismiss the bottom sheet
        //iv_close.setOnClickListener { dismissAllowingStateLoss() }
//        bottomSheet.txtDownload.setOnClickListener {
//            dismissAllowingStateLoss()
//            mListener?.onItemClick("Download")
//
//        }
//
//        bottomSheet.txtShare.setOnClickListener {
//            dismissAllowingStateLoss()
//            mListener?.onItemClick("Share")
//        }
    }

    private var mListener: ItemClickListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ItemClickListener) {
            mListener = context as ItemClickListener
        } else {
            throw RuntimeException(
                    context.toString()
                            .toString() + " must implement ItemClickListener"
            )
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    interface ItemClickListener {
        fun onItemClick(item: String)
    }

    companion object {
        @JvmStatic
        fun newInstance(bundle: Bundle): OptionsBottomSheetFragment {
            val fragment = OptionsBottomSheetFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    //region function
    private fun initListItem() {
        listItem = ArrayList()
        listItem?.add(Item("Element1", "HaNoi", R.drawable.americanfb))
        listItem?.add(Item("Element2", "HaNoi", R.drawable.snackefb))
        listItem?.add(Item("Element3", "HaNoi", R.drawable.soccerfb))
        listItem?.add(Item("Element4", "HaNoi", R.drawable.yourteamfb))
    }

    fun getWindowHeight(): Int {
        // Calculate window height for fullscreen use

        // Calculate window height for fullscreen use
        val displayMetrics = DisplayMetrics()
        (context as Activity?)!!.windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.heightPixels
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

    private fun setupFullHeight(bottomSheetDialog: BottomSheetDialog) {
        val bottomSheet = bottomSheetDialog.findViewById(R.id.design_bottom_sheet) as FrameLayout?
        val behavior: BottomSheetBehavior<*> = BottomSheetBehavior.from(bottomSheet)
        val layoutParams = bottomSheet!!!!.layoutParams

        val windowHeight: Int = getWindowHeight()
        if (layoutParams != null) {
            layoutParams.height = windowHeight
        }
        bottomSheet!!!!.layoutParams = layoutParams
        behavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    //endregion
}