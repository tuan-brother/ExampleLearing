package me.threebecause.learning.ui.screen.standings

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import me.threebecause.ShareViewModel
import me.threebecause.learning.R
import me.threebecause.learning.databinding.FragmentStandingsBinding
import me.threebecause.learning.ui.screen.home.competition.CompetitionFragment
import me.threebecause.learning.ui.teamsdetail.TeamsDetailActivity

@AndroidEntryPoint
class StandingsFragment : Fragment(), CompetitionFragment.IOnClickListener {

    companion object {
        const val TAG = "StandingsFragment"
    }

    lateinit var viewBinding: FragmentStandingsBinding
    lateinit var adapter: StandingsAdapter
    private val shareViewModel: ShareViewModel by activityViewModels()
    val viewModel by viewModels<StandingsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_standings, container, false)
        val competitionFragment = CompetitionFragment()
        competitionFragment.setListener(this)
        if (shareViewModel.mutableSelectedItem.value == null) {
            viewModel.getStandingData(2015)
        }
        shareViewModel.mutableSelectedItem.observe(viewLifecycleOwner) {
            viewModel.getStandingData(it)
        }
        adapter = StandingsAdapter(listener = {
            val intent = Intent(requireContext(), TeamsDetailActivity::class.java)
            intent.putExtra("ID", it.id)
            startActivity(intent)
        })
        viewModel.standingList.observe(viewLifecycleOwner) {
            it.forEach { stages ->
                if (stages.type.equals("TOTAL")) {
                    viewBinding.header.mainText.text = stages.stage.toString()
                    adapter.submitList(stages.table)
                }
            }
        }
        viewBinding.rvTeams.adapter = adapter

        // Inflate the layout for this fragment
        return viewBinding.root
    }

    override fun onClick(item: Int) {
        viewModel.getStandingData(item)
    }
}