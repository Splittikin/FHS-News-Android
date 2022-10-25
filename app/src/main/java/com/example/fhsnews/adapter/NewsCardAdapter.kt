package com.example.fhsnews.adapter

import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fhsnews.R
import com.example.fhsnews.model.Article

// Adapter to find the correct card type to use for an article and inflate it

class NewsCardAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val newsList: List<Article> = com.example.fhsnews.data.DataSource.newsList

    inner class NewsCardViewHolder(view: View?) : RecyclerView.ViewHolder(view!!) {
        var topperIcon: ImageView = view!!.findViewById(R.id.topperIcon)
        var topperText: TextView = view!!.findViewById(R.id.topperText)
        var articleThumbnail: ImageView = view!!.findViewById(R.id.articleThumbnail)
        var articleHeadline: TextView = view!!.findViewById(R.id.articleHeadline)
        var postedTime: TextView = view!!.findViewById(R.id.postedTime)
        var authorName: TextView = view!!.findViewById(R.id.authorName)
        var articleSubtitle: TextView = view!!.findViewById(R.id.articleSubtitle)
        var articlePreview: TextView = view!!.findViewById(R.id.articlePreview)
    }

    inner class WeatherCardViewHolder(view: View?) : RecyclerView.ViewHolder(view!!) {
        var temperatureNumber: TextView = view!!.findViewById(R.id.temperatureNumber)
        var weatherSubtitle: TextView = view!!.findViewById(R.id.weatherSubtitle)
        var weatherIcon: ImageView = view!!.findViewById(R.id.weatherIcon)
    }

    inner class RedDayCardViewHolder(view: View?) : RecyclerView.ViewHolder(view!!) {
        // No vars here...
    }

    inner class SilverDayCardViewHolder(view: View?) : RecyclerView.ViewHolder(view!!) {
        // No vars here...
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun getItemViewType(position: Int): Int {
        return newsList[position].cardType
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        Log.d(TAG, "onCreateViewHolder: ran, $viewType")
        when (viewType) {
            1 -> { // Weather

                val adapterLayout =
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.weather_card, parent, false)
                return WeatherCardViewHolder(adapterLayout)

            }
            2 -> { // Red/Silver Day Card
                return if (System.currentTimeMillis() % 172800000 > 86400000) { // 86400000 milliseconds in a day
                    val adapterLayout =
                        LayoutInflater.from(parent.context)
                            .inflate(R.layout.red_day_card, parent, false)
                    RedDayCardViewHolder(adapterLayout)
                } else {
                    val adapterLayout =
                        LayoutInflater.from(parent.context)
                            .inflate(R.layout.silver_day_card, parent, false)
                    RedDayCardViewHolder(adapterLayout)
                }
                // TODO: Make this account for weekends (currently changes every other day even on weekends)
            }
            else -> { // Article

                val adapterLayout =
                    LayoutInflater.from(parent.context).inflate(R.layout.news_card, parent, false)
                return NewsCardViewHolder(adapterLayout)

            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val thisArticle = newsList[position]
        Log.d(TAG, "onBindViewHolder: ran, $thisArticle")
        when (thisArticle.cardType) {
            1 -> {
                return
            }
            2 -> {
                return
            }
            else -> {
                (holder as NewsCardViewHolder).topperIcon.setImageResource(thisArticle.topperIcon)
                holder.topperText.text = thisArticle.topperText
                holder.articleThumbnail.setImageResource(thisArticle.articleThumbnail)
                holder.articleHeadline.text = thisArticle.headline
                holder.articleSubtitle.text = thisArticle.subtitle
                holder.postedTime.text = thisArticle.postedTime.toString()
                holder.authorName.text = thisArticle.author
                holder.articlePreview.text = thisArticle.text
            }
        }
    }
}