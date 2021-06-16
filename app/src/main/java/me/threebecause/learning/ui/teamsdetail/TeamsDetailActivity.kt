package me.threebecause.learning.ui.teamsdetail

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import dagger.hilt.android.AndroidEntryPoint
import me.threebecause.learning.R
import me.threebecause.learning.data.remote.api.NetworkUtil
import me.threebecause.learning.databinding.ActivityTeamsDetailBinding
import me.threebecause.learning.ui.teamsdetail.teamsmatch.TeamMatchFragment

@AndroidEntryPoint
class TeamsDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTeamsDetailBinding
    val viewModel: TeamsDetailViewModel by viewModels()
    var id: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_teams_detail)
        id = intent.getIntExtra("ID", 0)
        val networkUtil = NetworkUtil()
        if (id != null) {
            viewModel.getTeamById(id!!)
            viewModel.team.observe(this) {
                binding.valueTeam = it
                Glide.with(binding.crestImageView)
                    .load(networkUtil.getCrestUrl(it.id, NetworkUtil.IMAGE_QUALITY_HD))
                    .listener(object : RequestListener<Drawable> {
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>?,
                            isFirstResource: Boolean
                        ): Boolean {
                            binding.crestImageView.setImageResource(R.drawable.default_crest)
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

                    })
                    .into(binding.crestImageView)
            }
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, TeamMatchFragment.newInstance(id!!))
                .commit()
        }
    }
}
