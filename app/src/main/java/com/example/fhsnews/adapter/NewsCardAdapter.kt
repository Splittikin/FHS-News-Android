package com.example.fhsnews.adapter

import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fhsnews.NewsScrollerFragmentDirections
import com.example.fhsnews.databinding.NewsCardBinding
import com.example.fhsnews.model.Article

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

        /*
        // Hide any empty article elements
        if (thisArticle.topperIcon == 0 && thisArticle.topperText != "") {
            val topperTextConstraintParam = ConstraintSet()
            topperTextConstraintParam.clone(holder.newsCardConstraintLayout)
            topperTextConstraintParam.connect(
                R.id.topperText,
                ConstraintSet.START,
                R.id.newsCardConstraintLayout,
                ConstraintSet.START
            )
            topperTextConstraintParam.connect(
                R.id.articleThumbnail,
                ConstraintSet.TOP,
                R.id.topperText,
                ConstraintSet.BOTTOM
            )
            topperTextConstraintParam.applyTo(holder.newsCardConstraintLayout)
        }
        if (thisArticle.topperText == "" && thisArticle.topperIcon == 0) {
            val imgMarginParam =
                holder.articleThumbnail.layoutParams as ViewGroup.MarginLayoutParams
            imgMarginParam.setMargins(0, 0, 0, 0)
            holder.articleThumbnail.layoutParams = imgMarginParam

            val icoMarginParam =
                holder.topperIcon.layoutParams as ViewGroup.MarginLayoutParams
            icoMarginParam.setMargins(0, 0, 0, 0)
            holder.topperIcon.layoutParams = icoMarginParam
        }
        if (thisArticle.subtitle == "") {
            val subMarginParam =
                holder.articleSubtitle.layoutParams as ViewGroup.MarginLayoutParams
            subMarginParam.setMargins(0, 0, 0, 0)
            holder.articleSubtitle.layoutParams = subMarginParam

            val previewMarginParam =
                holder.articlePreview.layoutParams as ViewGroup.MarginLayoutParams
            previewMarginParam.setMargins(8, 0, 8, 8)
            holder.articlePreview.layoutParams = previewMarginParam
        }
         */
    }
}