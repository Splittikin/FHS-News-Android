package com.fhs.fhsnews.adapter

import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fhs.fhsnews.NewsScrollerFragmentDirections
import com.fhs.fhsnews.databinding.AlertCardBinding
import com.fhs.fhsnews.databinding.ClubCardBinding
import com.fhs.fhsnews.databinding.NewsCardBinding
import com.fhs.fhsnews.databinding.WeatherCardBinding
import com.fhs.fhsnews.model.Alert
import com.fhs.fhsnews.model.Article
import com.fhs.fhsnews.model.FeedData
import com.fhs.fhsnews.model.WeatherData

// Adapter to find the correct card type to use for an article and inflate it

class NewsDataAdapter : ListAdapter<FeedData, RecyclerView.ViewHolder>(DiffCallback) {

    // TODO: Filter by tag

    class NewsCardViewHolder(private var binding: NewsCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(thisArticle: Article) {
            binding.article = thisArticle
            Log.d(TAG, "bind: binding article $thisArticle")
            /* TODO: Format posted date relatively. Must be done manually, android's own system
                for this requires minimum API 24 (We are using minimum API 21) */
            binding.newsCardConstraintLayout.setOnClickListener {
                val action =
                    NewsScrollerFragmentDirections.actionNewsScrollerFragmentToOpenArticleFragment(
                        articleId = thisArticle.articleId
                    )
                binding.root.findNavController().navigate(action)
            }
            binding.executePendingBindings()
        }
    }

    class WeatherCardViewHolder(private var binding: WeatherCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(weather: WeatherData) {
            binding.weatherData = weather
            binding.executePendingBindings()
        }
    }

    class AlertCardViewHolder(private var binding: AlertCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(alert: Alert) {
            binding.alert = alert
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
                NewsCardViewHolder(
                    NewsCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                )
            }
            1 -> {
                ClubDataAdapter.ClubCardViewHolder(
                    ClubCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                )
            }
            2 -> {
                WeatherCardViewHolder(
                    WeatherCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                )
            }
            3 -> {
                AlertCardViewHolder(
                    AlertCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                )
            }
            else -> {
                NewsCardViewHolder(
                    NewsCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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
                (holder as NewsCardViewHolder).bind(thisItem.article)
            }
            2 -> {
                Log.d(TAG, "onBindViewHolder: binding weather $thisItem")
                (holder as WeatherCardViewHolder).bind(thisItem.weatherData)
            }
            3 -> {
                Log.d(TAG, "onBindViewHolder: binding alert $thisItem")
                (holder as AlertCardViewHolder).bind(thisItem.alert)
            }
        }
    }
}