package com.fhs.fhsnews.adapter

import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fhs.fhsnews.EventsViewFragmentDirections
import com.fhs.fhsnews.databinding.NewsCardBinding
import com.fhs.fhsnews.model.Article

// This adapter uses the same data as the News Card Adapter, but filters it to only posts made on a given day

class EventsViewAdapter() :  ListAdapter<Article, EventsViewAdapter.NewsCardViewHolder>(
    NewsCardAdapter
) {

    // TODO: Filter by tag

    class NewsCardViewHolder(private var binding: NewsCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article) {
            binding.article = article
            /* TODO: Format posted date relatively. Must be done manually, android's own system
                for this requires minimum API 24 (We are using minimum API 21) */
            binding.newsCardConstraintLayout.setOnClickListener {
                Log.d(TAG, "onBindViewbinding: article click")
                val action =
                    EventsViewFragmentDirections.actionEventsViewFragmentToOpenArticleFragment(
                        articleId = article.articleId
                    )
                binding.root.findNavController().navigate(action)
            }
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.articleId == newItem.articleId
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.text == newItem.text
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsCardViewHolder {
        return NewsCardViewHolder(
            NewsCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: NewsCardViewHolder, position: Int) {
        val thisArticle = getItem(position)
        holder.bind(thisArticle)
    }
}