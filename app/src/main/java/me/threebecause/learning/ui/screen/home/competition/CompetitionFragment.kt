package me.threebecause.learning.ui.screen.home.competition

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import me.threebecause.ShareViewModel
import me.threebecause.learning.R
import me.threebecause.learning.databinding.FragmentCompetitionBinding
import me.threebecause.learning.ui.screen.home.FragmentSetting
import me.threebecause.learning.ui.screen.standings.StandingsFragment

@AndroidEntryPoint
class CompetitionFragment : Fragment() {
    lateinit var binding: FragmentCompetitionBinding
    lateinit var layoutManager: LinearLayoutManager
    var onClickItem: IOnClickListener? = null
    private val shareViewModel: ShareViewModel by activityViewModels()
    private val viewModel by viewModels<CompetitionViewModel>()

    companion object {
        const val TAG = "CompetitionFragment"
    }

    fun setListener(listenter: IOnClickListener) {
        onClickItem = listenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_competition, container, false)
        val competitionAdapter = CompetitionAdapter(onclickItem = {
            val drawer = requireActivity().findViewById<DrawerLayout>(R.id.dl_main)
            drawer.close()
            shareViewModel.selectItem(it.id)
        })
        layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rcCompetition.adapter = competitionAdapter
        binding.rcCompetition.layoutManager = layoutManager
        viewModel.getCompetitions()
        viewModel.competitions.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                competitionAdapter.submitList(it)
            }
        }

        return binding.root
    }

    interface IOnClickListener {
        fun onClick(item: Int)
    }
}