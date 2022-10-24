package com.example.fhsnews.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fhsnews.R
import com.example.fhsnews.model.Article

// Adapter to find the correct card type to use for an article and inflate it

class NewsCardAdapter(
    private val context: Context?
) : RecyclerView.Adapter<NewsCardAdapter.NewsCardViewHolder>() {

    private val newsList: List<Article> = com.example.fhsnews.data.DataSource.newsList

    class NewsCardViewHolder(view: View?) : RecyclerView.ViewHolder(view!!) {
        // TODO: Check if article is weather or red/silver card first and grab those elements instead accordingly
        var topperIcon: ImageView = view!!.findViewById(R.id.topperIcon)
        var topperText: TextView = view!!.findViewById(R.id.topperText)
        var articleThumbnail: ImageView = view!!.findViewById(R.id.articleThumbnail)
        var articleHeadline: TextView = view!!.findViewById(R.id.articleHeadline)
        var postedTime: TextView = view!!.findViewById(R.id.postedTime)
        var authorName: TextView = view!!.findViewById(R.id.authorName)
        var articleSubtitle: TextView = view!!.findViewById(R.id.articleSubtitle)
        var articlePreview: TextView = view!!.findViewById(R.id.articlePreview)
    }

    override fun getItemCount(): Int = newsList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsCardViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: NewsCardViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

}