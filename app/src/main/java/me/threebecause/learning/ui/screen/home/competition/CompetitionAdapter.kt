package me.threebecause.learning.ui.screen.home.competition

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import me.threebecause.learning.R
import me.threebecause.learning.data.model.CompetitionX
import me.threebecause.learning.databinding.ItemCompetitionBinding
import kotlin.random.Random

class CompetitionAdapter(
    val onclickItem: ((item: CompetitionX) -> Unit)? = null
) : ListAdapter<CompetitionX, CompetitionAdapter.ComViewHolder>(object :
    DiffUtil.ItemCallback<CompetitionX>() {
    override fun areItemsTheSame(oldItem: CompetitionX, newItem: CompetitionX): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: CompetitionX,
        newItem: CompetitionX
    ): Boolean {
        return oldItem == newItem
    }
}) {

    open class ComViewHolder(binding: ItemCompetitionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val listColor = arrayOf("#5E97F6", "#9CCC65", "#FF8A65", "#9E9E9E", "#9FA8DA", "#90A4AE")
        val itemBinding = binding
        val random = Random(5)
        fun onBind(item: CompetitionX) {
            itemBinding.competitionName.setBackgroundColor(
                Color.parseColor(
                    listColor[random.nextInt(
                        5
                    )]
                )
            )
            itemBinding.competitionName.text = item.name
            itemBinding.competitionCountry.text = item.area.name
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ComViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        return ComViewHolder(
            DataBindingUtil.inflate(
                inflate,
                R.layout.item_competition,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ComViewHolder, position: Int) {
        holder.onBind(getItem(position))
        holder.itemBinding.llItemCompetition.setOnClickListener {
            onclickItem?.invoke(getItem(position))
        }
    }

}