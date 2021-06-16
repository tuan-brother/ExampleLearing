package me.threebecause.learning.ui.teamsdetail.teamsmatch

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import me.threebecause.learning.R
import me.threebecause.learning.data.model.Matche
import me.threebecause.learning.data.remote.api.NetworkUtil
import me.threebecause.learning.databinding.MatchItemBinding

class TeamMatchAdapter : ListAdapter<Matche, TeamMatchAdapter.MatchViewHolder>(object :
    DiffUtil.ItemCallback<Matche>() {
    override fun areItemsTheSame(oldItem: Matche, newItem: Matche): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Matche, newItem: Matche): Boolean {
        return oldItem == newItem
    }
}) {
    class MatchViewHolder(viewBinding: MatchItemBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {
        val binding = viewBinding
        fun onBind(item: Matche) {
            binding.homeNameText.text = item.homeTeam.name
            binding.awayNameText.text = item.awayTeam.name
            binding.homeScoreText.text = item.score.fullTime.homeTeam.toString()
            binding.awayScoreTex.text = item.score.fullTime.awayTeam.toString()
            val networkUtil = NetworkUtil()
            val homeCrestUrl =
                networkUtil.getCrestUrl(item.homeTeam.id, NetworkUtil.IMAGE_QUALITY_HD)
            val awayCrestUrl =
                networkUtil.getCrestUrl(item.awayTeam.id, NetworkUtil.IMAGE_QUALITY_HD)
            Glide.with(binding.root).load(homeCrestUrl)
                .into(binding.crestHomeTeam)
            Glide.with(binding.root).load(awayCrestUrl)
                .into(binding.crestAwayTeam)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MatchViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        val binding: MatchItemBinding =
            DataBindingUtil.inflate(inflate, R.layout.match_item, parent, false)
        return MatchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }
}