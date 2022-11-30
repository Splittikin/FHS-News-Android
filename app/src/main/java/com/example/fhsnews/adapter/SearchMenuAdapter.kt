package com.example.fhsnews.adapter

import android.content.ContentValues
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.fhsnews.R
import com.example.fhsnews.SearchMenuFragmentDirections

// This adapter uses the same data as the News Card Adapter, but filters it to a search request

class SearchMenuAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val newsList = com.example.fhsnews.data.DataSource.newsList

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

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun getItemViewType(position: Int): Int {
        return newsList[position].cardType
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        Log.d(ContentValues.TAG, "onCreateViewHolder: ran, $viewType")
        val adapterLayout =
            LayoutInflater.from(parent.context).inflate(R.layout.news_card, parent, false)
        return NewsCardViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val thisArticle = newsList[position]
        (holder as NewsCardViewHolder).topperIcon.setImageResource(thisArticle.topperIcon)
        holder.topperText.text = thisArticle.topperText
        holder.articleThumbnail.setImageResource(thisArticle.articleThumbnail)
        holder.articleHeadline.text = thisArticle.headline
        holder.articleSubtitle.text = thisArticle.subtitle
        holder.postedTime.text = thisArticle.postedTime.toString()
        holder.authorName.text = thisArticle.author
        holder.articlePreview.text = thisArticle.text
        holder.newsCardConstraintLayout.setOnClickListener {
            Log.d(ContentValues.TAG, "onBindViewHolder: article click")
            val action =
                SearchMenuFragmentDirections.actionSearchMenuFragmentToOpenArticleFragment(
                    articleId = position
                )
            holder.view!!.findNavController().navigate(action)
        }

        // Hide any empty article elements
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