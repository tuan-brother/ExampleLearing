package me.threebecause.learning.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import me.threebecause.learning.R
import me.threebecause.learning.databinding.FragmentHomeBinding
import me.threebecause.learning.ui.screen.recycleview.Item
import me.threebecause.learning.ui.screen.recycleview.MainRecycleAdapter
import java.util.*

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private var mainAdapter: MainRecycleAdapter? = null
    private var layoutManager: LinearLayoutManager? = null
    private var listItem: ArrayList<Item>? = null

    companion object{
        val TAG = "HomeFragment"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
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

    //endregion
}