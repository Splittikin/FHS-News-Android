package com.example.fhsnews.adapter

import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.fhsnews.R
import com.example.fhsnews.SearchMenuFragmentDirections
import com.example.fhsnews.model.Article

// This adapter uses the same data as the News Card Adapter, but filters it to a search request

class SearchMenuAdapter(searchQuery: String) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val newsList: List<Article> = com.example.fhsnews.data.articles.ArticlesList.newsList
    private var searchedNewsList: List<Article>
    private var noResults: Boolean = false

    init {
        searchedNewsList = searchArticles(searchQuery)
        Log.d(TAG, "SearchMenu: initted")
    }

    private fun searchArticles(query: String): List<Article> {
        // TODO: Filter on separate thread
        val result = newsList.filter {
            it.headline.contains(query, true)
                    || it.subtitle.contains(query, true)
                    || it.author.contains(query, true)
                    || it.topperText.contains(query, true)
                    || it.text.contains(query, true)
        }
        if (result.isEmpty()) {
            noResults = true
        }
        Log.d(TAG, "searchArticles: ${result.size} articles found for \"$query\"")
        return result
    }

    inner class NewsCardViewHolder(val view: View?) : RecyclerView.ViewHolder(view!!) {
        var topperIcon: ImageView = view!!.findViewById(R.id.topperIcon)
        var topperText: TextView = view!!.findViewById(R.id.topperText)
        var articleThumbnail: ImageView = view!!.findViewById(R.id.articleThumbnail)
        var articleHeadline: TextView = view!!.findViewById(R.id.articleHeadline)
        var postedTime: TextView = view!!.findViewById(R.id.articlePostedTime)
        var authorName: TextView = view!!.findViewById(R.id.authorName)
        var articleSubtitle: TextView = view!!.findViewById(R.id.articleSubtitle)
        var articlePreview: TextView = view!!.findViewById(R.id.articlePreview)
        var newsCardConstraintLayout: ConstraintLayout =
            view!!.findViewById(R.id.newsCardConstraintLayout)
    }

    inner class NoResultsCardViewHolder(val view: View?) : RecyclerView.ViewHolder(view!!) {
        // No vars here...
    }

    override fun getItemCount(): Int {
        return searchedNewsList.size
    }

    override fun getItemViewType(position: Int): Int {
        return searchedNewsList[position].cardType
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        Log.d(TAG, "onCreateViewHolder: searchmenuadapter")
        val adapterLayout =
            LayoutInflater.from(parent.context).inflate(R.layout.news_card, parent, false)
        return NewsCardViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.d(TAG, "SeachMenu onBindViewHolder: ran")

        val thisArticle = searchedNewsList[position]
        (holder as NewsCardViewHolder).topperIcon.setImageResource(thisArticle.topperIcon)
        holder.topperText.text = thisArticle.topperText
        holder.articleThumbnail.setImageResource(thisArticle.articleThumbnail)
        holder.articleHeadline.text = thisArticle.headline
        holder.articleSubtitle.text = thisArticle.subtitle
        holder.postedTime.text = thisArticle.postedTime.toString()
        holder.authorName.text = thisArticle.author
        holder.articlePreview.text = thisArticle.text
        holder.newsCardConstraintLayout.setOnClickListener {
            val action =
                SearchMenuFragmentDirections.actionSearchMenuFragmentToOpenArticleFragment(
                    articleId = thisArticle.articleId
                )
            holder.view!!.findNavController().navigate(action)
        }

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
    }
}