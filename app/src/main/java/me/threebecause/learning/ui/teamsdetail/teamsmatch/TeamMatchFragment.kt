package me.threebecause.learning.ui.teamsdetail.teamsmatch

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import me.threebecause.learning.R
import me.threebecause.learning.databinding.FragmentTeamMatchBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class TeamMatchFragment : Fragment() {

    val viewModel: TeamMatchViewModel by viewModels()
    lateinit var viewBinding: FragmentTeamMatchBinding

    companion object {
        fun newInstance(id: Int): TeamMatchFragment {
            val fragment = TeamMatchFragment()
            val bundle = Bundle()
            bundle.putInt("ID", id)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_team_match, container, false)
        val to: LocalDateTime = LocalDateTime.now().plusDays(7)
        val from: LocalDateTime = LocalDateTime.now().minusMonths(3)
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        if (arguments != null) {
            Log.d(
                "TAG444",
                "onCreateView:  ${requireArguments().getInt("ID")} \n ${to.format(formatter)} \n ${
                    from.format(formatter)
                }"
            )
            viewModel.getMatches(
                requireArguments().getInt("ID"),
                from.format(formatter),
                to.format(formatter)
            )
        }
        val adapter = TeamMatchAdapter()
        viewBinding.rcTeam.adapter = adapter
        viewModel.matches.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
        return viewBinding.root
    }
}