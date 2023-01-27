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
import com.fhs.fhsnews.model.Article

// Adapter to find the correct card type to use for an article and inflate it

class NewsCardAdapter : ListAdapter<Article, NewsCardAdapter.NewsCardViewHolder>(DiffCallback) {

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
                    NewsScrollerFragmentDirections.actionNewsScrollerFragmentToOpenArticleFragment(
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