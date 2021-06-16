package me.threebecause.learning.ui.screen.standings

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import me.threebecause.learning.R
import me.threebecause.learning.data.model.TableEntryEntity
import me.threebecause.learning.data.model.TeamEntity
import me.threebecause.learning.data.remote.api.NetworkUtil
import me.threebecause.learning.databinding.TableItemBinding

class StandingsAdapter(
    val listener: (TeamEntity) -> Unit
) : ListAdapter<TableEntryEntity, StandingsAdapter.StandViewHolder>(object :
    DiffUtil.ItemCallback<TableEntryEntity>() {
    override fun areItemsTheSame(oldItem: TableEntryEntity, newItem: TableEntryEntity): Boolean {
        return oldItem.position == newItem.position
    }

    override fun areContentsTheSame(oldItem: TableEntryEntity, newItem: TableEntryEntity): Boolean {
        return oldItem == newItem
    }
}) {
    class StandViewHolder(binding: TableItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val itemBinding = binding
        fun onBind(item: TableEntryEntity) {
            itemBinding.item = item
            val networkUtil = NetworkUtil()
            val url = networkUtil.getCrestUrl(item.team.id, NetworkUtil.IMAGE_QUALITY_HD)
            Glide.with(itemBinding.teamCrest).load(url)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        itemBinding.teamCrest.setBackgroundResource(R.drawable.default_crest)
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }

                }).into(itemBinding.teamCrest)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StandViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        return StandViewHolder(
            DataBindingUtil.inflate(
                inflate,
                R.layout.table_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: StandViewHolder, position: Int) {
        holder.onBind(getItem(position))
        holder.itemBinding.ctlTeam.setOnClickListener {
            getItem(position).team?.let {
                listener.invoke(it)
            }
        }
    }
}