package com.example.fhsnews.adapter

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.fhsnews.model.Article

// Adapter to find the correct card type to use for an article and inflate it

class NewsCardAdapter(
    private val context: Context?
): RecyclerView.Adapter<NewsCardAdapter.NewsCardViewHolder>() {

    private val newsList: List<Article> = com.example.fhsnews.data.DataSource.newsList

    class NewsCardViewHolder(view: View?): RecyclerView.ViewHolder(view!!) {
        // TODO: Set up the card layout and come back to this
    }

}