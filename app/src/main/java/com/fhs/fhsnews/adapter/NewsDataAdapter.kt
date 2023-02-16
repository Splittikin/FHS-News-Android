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
import com.fhs.fhsnews.databinding.NewsCardBinding
import com.fhs.fhsnews.databinding.WeatherCardBinding
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

    companion object DiffCallback : DiffUtil.ItemCallback<FeedData>() {
        override fun areItemsTheSame(oldItem: FeedData, newItem: FeedData): Boolean {
            if (newItem.itemType == "Article") {
                return oldItem.article.articleId == newItem.article.articleId
            } else {
                return false
            }
        }

        override fun areContentsTheSame(oldItem: FeedData, newItem: FeedData): Boolean {
            if (newItem.itemType == "Article") {
                return oldItem.article.text == newItem.article.text
            } else {
                return false
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == 1) {
            return NewsCardViewHolder(
                NewsCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        } else if (viewType == 2) {
            return WeatherCardViewHolder(
                WeatherCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        } else {
            return NewsCardViewHolder(
                NewsCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        }
    }

    override fun getItemViewType(position: Int): Int {
        val thisItem = getItem(position)
        Log.d(TAG, "getItemViewType: getting item view type for $thisItem")
        return if (thisItem.itemType == "Article") {
            0 // Article
        } else if (thisItem.itemType == "Club") {
            1 // Club
        } else {
            -1 // I Don't Know What This Thing Is
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val thisItem = getItem(position)
        Log.d(TAG, "onBindViewHolder: position $position is ${getItemViewType(position)}")
        if (getItemViewType(position) == 0) { // If thisItem is an article

            Log.d(TAG, "onBindViewHolder: binding article $thisItem")
            (holder as NewsCardViewHolder).bind(thisItem.article)
        }
    }
}