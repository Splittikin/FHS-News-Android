package com.example.fhsnews.adapter

import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.fhsnews.NewsScrollerFragmentDirections
import com.example.fhsnews.R
import com.example.fhsnews.model.Article

// Adapter to find the correct card type to use for an article and inflate it

class NewsCardAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    // TODO: Filter by tag

    private val newsList: List<Article>

    init {
        newsList = listOf(
            com.example.fhsnews.data.DataSource.extrasList,
            com.example.fhsnews.data.DataSource.newsList
        ).flatten()
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

    inner class WeatherCardViewHolder(view: View?) : RecyclerView.ViewHolder(view!!) {
        var temperatureNumber: TextView = view!!.findViewById(R.id.temperatureNumber)
        var weatherSubtitle: TextView = view!!.findViewById(R.id.weatherSubtitle)
        var weatherIcon: ImageView = view!!.findViewById(R.id.weatherIcon)
    }

    inner class RedDayCardViewHolder(view: View?) : RecyclerView.ViewHolder(view!!) {
        // No vars here...
    }

    inner class SilverDayCardViewHolder(view: View?) : RecyclerView.ViewHolder(view!!) {
        var cleverButton: Button = view!!.findViewById(R.id.clvr_button)
        var esButton: Button = view!!.findViewById(R.id.es_button)
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
                val adapterLayout = LayoutInflater.from(parent.context)
                    .inflate(R.layout.weather_card, parent, false)
                return WeatherCardViewHolder(adapterLayout)
            }
            2 -> { // Red/Silver Day Card
                return if (System.currentTimeMillis() % 172800000 > 86400000) { // 86400000 milliseconds in a day
                    val adapterLayout = LayoutInflater.from(parent.context)
                        .inflate(R.layout.red_day_card, parent, false)
                    RedDayCardViewHolder(adapterLayout)
                } else {
                    val adapterLayout = LayoutInflater.from(parent.context)
                        .inflate(R.layout.silver_day_card, parent, false)
                    SilverDayCardViewHolder(adapterLayout)
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
        when (thisArticle.cardType) {
            1 -> { // Weather
                return
            }
            2 -> { // Red/Silver Card
                /* if (holder is SilverDayCardViewHolder) {
                    var urlToOpen: Uri = Uri.parse("https://clever.com/in/hse/student/portal")
                    val intent = Intent(Intent.ACTION_VIEW, urlToOpen)
                    val context = holder.view.context
                } */
                // TODO: Enriching students/Clever button functionality
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
                holder.newsCardConstraintLayout.setOnClickListener {
                    Log.d(TAG, "onBindViewHolder: article click")
                    val action =
                        NewsScrollerFragmentDirections.actionNewsScrollerFragmentToOpenArticleFragment(
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
    }
}