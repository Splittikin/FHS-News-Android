package com.fhs.fhsnews.adapter

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.constraintlayout.helper.widget.Flow
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fhs.fhsnews.EventsViewFragmentDirections
import com.fhs.fhsnews.NewsScrollerFragmentDirections
import com.fhs.fhsnews.R
import com.fhs.fhsnews.databinding.CardAlertBinding
import com.fhs.fhsnews.databinding.CardClubBinding
import com.fhs.fhsnews.databinding.CardNewsBinding
import com.fhs.fhsnews.databinding.CardWeatherBinding
import com.fhs.fhsnews.model.Alert
import com.fhs.fhsnews.model.Article
import com.fhs.fhsnews.model.FeedData
import com.fhs.fhsnews.model.WeatherData

// Adapter to find the correct card type to use for an item and inflate it

class NewsDataAdapter : ListAdapter<FeedData, RecyclerView.ViewHolder>(DiffCallback) {

    // TODO: Filter by tag

    class CardNewsViewHolder(private var binding: CardNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(thisArticle: Article) {
            binding.article = thisArticle

            Log.d(TAG, "bind: binding article $thisArticle")
            /* TODO: Format posted date relatively. Must be done manually, android's own system
                for this requires minimum API 24 (We are using minimum API 21) */
            binding.newsCardConstraintLayout.setOnClickListener {
                var action: NavDirections
                val currentFragment = binding.root.findNavController().currentDestination!!.id
                Log.d(TAG, "bind: current destination is $currentFragment")
                when (currentFragment) {
                    R.id.newsScrollerFragment -> {
                        action =
                            NewsScrollerFragmentDirections.actionNewsScrollerFragmentToOpenArticleFragment(
                                articleId = thisArticle.articleId
                            )
                    }
                    R.id.eventsViewFragment -> {
                        action =
                            EventsViewFragmentDirections.actionEventsViewFragmentToOpenArticleFragment(
                                articleId = thisArticle.articleId
                            )
                    }
                    else -> {
                        error("Can't figure out which directions to use")
                    }
                }
                binding.root.findNavController().navigate(action)
            }
            binding.executePendingBindings()
        }
    }

    class CardWeatherViewHolder(private var binding: CardWeatherBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(weather: WeatherData) {
            binding.weatherData = weather
            binding.executePendingBindings()
        }
    }

    class CardAlertViewHolder(private var context: Context, private var binding: CardAlertBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(alert: Alert) {
            binding.alert = alert

            val cardConstraint: ConstraintLayout = binding.alertCardConstraintLayout
            val cardFlow: Flow = binding.buttonsFlow
            for (button in alert.links) {
                var linkButton = Button(context)
                linkButton.text = button.key
                linkButton.id = View.generateViewId()
                cardConstraint.addView(linkButton)
                cardFlow.addView(linkButton)
                linkButton.setOnClickListener {
                    val queryUrl: Uri = Uri.parse(button.value)
                    val intent = Intent(Intent.ACTION_VIEW, queryUrl)
                    context.startActivity(intent)
                }
            }

            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<FeedData>() {
        override fun areItemsTheSame(oldItem: FeedData, newItem: FeedData): Boolean {
            return when (newItem.itemType) {
                "Article" -> {
                    oldItem.article.articleId == newItem.article.articleId
                }
                "WeatherData" -> {
                    oldItem.weatherData.time == newItem.weatherData.time
                }
                "Alert" -> {
                    oldItem.alert.text == newItem.alert.text
                }
                else -> {
                    false
                }
            }
        }

        override fun areContentsTheSame(oldItem: FeedData, newItem: FeedData): Boolean {
            return when (newItem.itemType) {
                "Article" -> {
                    oldItem.article.text == newItem.article.text
                }
                "WeatherData" -> {
                    oldItem.weatherData.time == newItem.weatherData.time
                }
                "Alert" -> {
                    oldItem.alert.text == newItem.alert.text
                }
                else -> {
                    false
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            0 -> {
                CardNewsViewHolder(
                    CardNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                )
            }
            1 -> {
                ClubDataAdapter.CardClubViewHolder(
                    CardClubBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                )
            }
            2 -> {
                CardWeatherViewHolder(
                    CardWeatherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                )
            }
            3 -> {
                CardAlertViewHolder(
                    parent.context,
                    CardAlertBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                )
            }
            else -> {
                CardNewsViewHolder(
                    CardNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                )
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val thisItem = getItem(position)
        Log.d(TAG, "getItemViewType: getting item view type for $thisItem")
        return when (thisItem.itemType) {
            "Article" -> {
                0 // Article
            }
            "Club" -> {
                1 // Club
            }
            "WeatherData" -> {
                2 // Weather Data
            }
            "Alert" -> {
                3 // Alert
            }
            else -> {
                -1 // I Don't Know What This Thing Is
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val thisItem = getItem(position)
        val thisItemViewType = getItemViewType(position)
        Log.d(TAG, "onBindViewHolder: position $position is ${getItemViewType(position)}")
        when (thisItemViewType) {
            0 -> {
                Log.d(TAG, "onBindViewHolder: binding article $thisItem")
                (holder as CardNewsViewHolder).bind(thisItem.article)
            }
            2 -> {
                Log.d(TAG, "onBindViewHolder: binding weather $thisItem")
                (holder as CardWeatherViewHolder).bind(thisItem.weatherData)
            }
            3 -> {
                Log.d(TAG, "onBindViewHolder: binding alert $thisItem")
                (holder as CardAlertViewHolder).bind(thisItem.alert)
            }
        }
    }
}